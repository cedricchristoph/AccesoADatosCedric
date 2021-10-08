/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iespuertocruz.cc.modelo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;
import javafx.stage.FileChooser;

/**
 *
 * @author dama
 */
public class Gestor {
    
    /** Variables **/
    private FileChooser fileChooser;
    private File selectedFile;
    private Path pathFile;
    private boolean saved;

    /** Constructores **/
    public Gestor() {
        fileChooser = null;
        selectedFile = null;
        pathFile = null;
        saved = false;
    }

    /** Getters & Setters **/
    public FileChooser getFileChooser() {
        return fileChooser;
    }

    public File getSelectedFile() {
        return selectedFile;
    }

    public Path getPathFile() {
        return pathFile;
    }

    public boolean isSaved() {
        return saved;
    }
    
    public void setFileChooser(FileChooser fileChooser) {
        this.fileChooser = fileChooser;
    }

    public void setSelectedFile(File selectedFile) {
        this.selectedFile = selectedFile;
    }

    public void setPathFile(Path pathFile) {
        this.pathFile = pathFile;
    }

    public void setSaved(boolean saved) {
        this.saved = saved;
    }

    /**
     * Methods
     */
    
    /**
     * Método para iniciar una nueva estancia (un nuevo documento)
     */
    public void newArchivo() {
        fileChooser = null;
        selectedFile = null;
        pathFile = null;
        saved = false;
    }
    
    /**
     * Método para abrir el archivo
     * @throws Exception 
     */
    public void abrirArchivo() throws Exception {
        fileChooser = new FileChooser();
        selectedFile = fileChooser.showOpenDialog(null);
        pathFile = selectedFile.toPath();
        saved = false;
        if (selectedFile == null)
            throw new Exception("No se seleccionó ningún archivo");
    }
    
    /**
     * Función que devuelve el contenido del fichero que se ha abierto
     * @return String contenido
     * @throws Exception 
     */
    public String getContenidoArchivo() throws Exception {
        try {
            String contenido = "";
            Scanner scan = new Scanner(selectedFile);
            while (scan.hasNextLine()) {
                contenido += scan.nextLine() + "\n";
            }
            scan.close();
            return contenido;
        } catch (FileNotFoundException e) {
            throw new Exception("No se encontró el archivo");
        }
    }
    
    /**
     * Método para guardar el archivo de texto
     * @param contenido Contenido del fichero a guardar
     * @throws Exception 
     */
    public void guardarArchivo(String contenido) throws Exception {
        if (selectedFile == null)
            selectedFile = useSaveFileChooser();
        try {
            FileWriter writer = new FileWriter(selectedFile);
            writer.write(contenido);
            writer.close();
            setSaved(true);
        } catch (IOException ex) {
            throw new Exception("Error al guardar archivo");
        } 
    }
    
    public void guardarArchivo(String contenido, boolean cleanUpHtml) throws Exception {
        if (cleanUpHtml) 
            guardarArchivo(cleanHtml(contenido));
        else 
            guardarArchivo(contenido);
        
    }
    
    public String cleanHtml(String contenido) {
        contenido = contenido.replaceAll("<[^>]*>", " ");
        contenido = contenido.trim().replaceAll(" +", " ");
        return contenido;
    }
    
    /**
     * Función que devuelve FILE seleccionado por FileChooser
     * @return 
     */
    public File useSaveFileChooser() {
        fileChooser = new FileChooser();
        return fileChooser.showSaveDialog(null);
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iespuertodelacruz.cc.controlador;

import es.iespuertodelacruz.cc.modelo.GestorPersonas;
import es.iespuertodelacruz.cc.modelo.ManejarFichero;
import es.iespuertodelacruz.cc.modelo.Persona;
import es.iespuertodelacruz.cc.registropersona;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author cedric
 */
public class FXMLPrincipalController implements Initializable {

    GestorPersonas gestorPersonas;
    FileChooser chooser;
    
    @FXML
    private TextArea txtPersonas;
    @FXML
    private Button btnCrear;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnExportar;
    @FXML
    private Button btnImportar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        switchButtons(false);
        gestorPersonas = registropersona.getGestor();
    }    

    @FXML
    private void crearPersona(ActionEvent event) {
        try {
            
            Parent root = FXMLLoader.load(getClass().getResource("/es/iespuertodelacruz/cc/vista/FXMLCrearPersona.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.showAndWait();
            load();
        } catch (IOException ex) {
            
        }
    }

    @FXML
    private void eliminarPersona(ActionEvent event) {
    }

    @FXML
    private void exportar(ActionEvent event) {
        try {
            ManejarFichero newManager = new ManejarFichero(chooser.showSaveDialog(null).getAbsolutePath());
            newManager.guardarPersonas(gestorPersonas.getPersonas());
        } catch (Exception e) {
            
        }
    }

    @FXML
    private void importar(ActionEvent event) {
        chooser = new FileChooser();
        try {
            gestorPersonas.setFichero(chooser.showOpenDialog(null).getAbsolutePath());
            load();
            switchButtons(true);
        } catch (Exception e) {
            switchButtons(false);
        }
    }
    
    private void load() {
        String str = "";
        for (Persona persona : gestorPersonas.getPersonas()) {
            str += persona.getDni() + " " + persona.getNombre() + " " + persona.getApellido() + "\n";
        }
        txtPersonas.setText(str);
    }
    
    private void switchButtons(boolean valor) {
        btnCrear.setDisable(!valor);
        btnEliminar.setDisable(!valor);
        btnExportar.setDisable(!valor);
    }
    
}

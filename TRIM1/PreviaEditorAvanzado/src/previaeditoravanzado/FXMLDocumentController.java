/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package previaeditoravanzado;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.web.HTMLEditor;
import javafx.stage.FileChooser;

/**
 *
 * @author dama
 */
public class FXMLDocumentController implements Initializable {
    
    private Label label;
    @FXML
    private MenuItem menuNew;
    @FXML
    private MenuItem menuOpen;
    @FXML
    private MenuItem menuCerrar;
    @FXML
    private MenuItem guardar;
    @FXML
    private MenuItem menuGuardarComo;
    @FXML
    private MenuItem menuSalir;
    @FXML
    private HTMLEditor htmlEditor;
    
    FileChooser fileChooser;
    File selectedFile;
    Path pathFile;
    @FXML
    private Label labelStatus;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void newPressed(ActionEvent event) {
    }

    @FXML
    private void openPressed(ActionEvent event) {
    }

    @FXML
    private void cerrarPressed(ActionEvent event) {
    }

    @FXML
    private void guardarPressed(ActionEvent event) {
    }

    @FXML
    private void guardarComoPressed(ActionEvent event) {
        System.out.println(
            htmlEditor.getHtmlText()
        );
        fileChooser = new FileChooser();
        fileChooser.setTitle("Abrir fichero");
        selectedFile = fileChooser.showOpenDialog(null);
        pathFile = null;
        if (selectedFile != null) {
            pathFile = selectedFile.toPath();
            labelStatus.setText("Guardado: " + pathFile.toString());
            
        }
    }

    @FXML
    private void salirPressed(ActionEvent event) {
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iespuertodelacruz.cc.controlador;

import es.iespuertodelacruz.cc.modelo.GestorPersonas;
import es.iespuertodelacruz.cc.modelo.Persona;
import es.iespuertodelacruz.cc.registropersona;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author cedric
 */
public class FXMLCrearPersonaController implements Initializable {
    
    @FXML
    private TextField txtDni;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtApellidos;
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnCrear;

    private GestorPersonas gestor;
    @FXML
    private TextField txtEdad;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gestor = registropersona.getGestor();
    }    

    @FXML
    private void cancelar(ActionEvent event) {
        close();
    }

    @FXML
    private void crear(ActionEvent event) {
        if (!(txtDni.getText().isEmpty()) && !(txtNombre.getText().isEmpty()) && !(txtApellidos.getText().isEmpty()))
            gestor.addPersona(
                    new Persona(txtDni.getText(), txtNombre.getText(), txtApellidos.getText(), Integer.parseInt(txtEdad.getText()))
            );
        close();
        
    }
    
    private void close() {
        Stage stage = (Stage) btnCrear.getParent().getScene().getWindow();
        stage.close();
    }
    
}

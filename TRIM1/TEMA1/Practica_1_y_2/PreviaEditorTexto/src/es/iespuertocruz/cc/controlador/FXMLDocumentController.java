package es.iespuertocruz.cc.controlador;

import es.iespuertocruz.cc.modelo.Gestor;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;

/**
 *
 * @author dama
 */
public class FXMLDocumentController implements Initializable {
    
    private Label label;
    @FXML
    private Menu menuArchivo;
    @FXML
    private MenuItem menuNuevo;
    @FXML
    private MenuItem menuAbrir;
    @FXML
    private MenuItem menuCerrar;
    @FXML
    private MenuItem menuGuardar;
    @FXML
    private MenuItem menuGuardarComo;
    @FXML
    private MenuItem menuSalir;
    @FXML
    private TextArea textArea;
    @FXML
    private Label labelStatus;
    
    private Gestor gestor;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gestor = new Gestor();
    }    

    @FXML
    private void nuevoPressed(ActionEvent event) {
        gestor.newArchivo();
        textArea.setText("");
        labelStatus.setText("Not saved yet");
    }

    @FXML
    private void abrirPressed(ActionEvent event) {
        try {
            gestor.abrirArchivo();
            textArea.setText(gestor.getContenidoArchivo());
            labelStatus.setText(gestor.getPathFile().toString());
        } catch (Exception ex) {
            labelStatus.setText(ex.getMessage());
        }
    }

    @FXML
    private void cerrarPressed(ActionEvent event) {
        if (gestor.isSaved()) {
            close();
        } else {
            if (guardarAntes())
                try {
                    gestor.guardarArchivo(textArea.getText());
                    labelStatus.setText(gestor.getPathFile().toString() + " | Saved");
                    close();
                } catch (Exception ex) {
                    labelStatus.setText(ex.getMessage());
                }
            else
                close();
        }
    }

    @FXML
    private void guardarPressed(ActionEvent event) {
        try {
            gestor.guardarArchivo(textArea.getText());
            labelStatus.setText(gestor.getPathFile().toString() + " | Saved");
        } catch (Exception ex) {
            labelStatus.setText(ex.getMessage());
        }
    }

    @FXML
    private void guardarComoPressed(ActionEvent event) {
        try {
            gestor.setSelectedFile(gestor.useSaveFileChooser());
            gestor.guardarArchivo(textArea.getText());
            labelStatus.setText(gestor.getPathFile().toString() + " | Saved");
        } catch (Exception ex) {
            labelStatus.setText(ex.getMessage());
        }
    }

    @FXML
    private void salirPressed(ActionEvent event) {
        if (gestor.isSaved()) {
            System.exit(0);
        } else {
            if (guardarAntes()) {
                try {
                    gestor.guardarArchivo(textArea.getText());
                } catch (Exception ex) {
                    labelStatus.setText(ex.getMessage());
                }
            } else {
                System.exit(0);
            }
        }
    }

    @FXML
    private void archivoPressed(ActionEvent event) {
    }
   
    /**
     * Cierra el fichero
     */
    private void close() {
        textArea.setText("");
        labelStatus.setText("Not saved yet");
        gestor.newArchivo();
    }

    @FXML
    private void keyTyped(KeyEvent event) {
        gestor.setSaved(false);
        labelStatus.setText("Not saved yet");
    }
    
    /**
     * Función que devuelve el resultado de una ventana emergente
     * @return Booleano
     */
    private boolean guardarAntes() {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("No ha guardado su archivo");
        alert.setHeaderText("¿Quiere guardar su archivo antes?");
        ButtonType buttonSi = new ButtonType("Si");
        ButtonType buttonNo = new ButtonType("No");
        alert.getButtonTypes().setAll(buttonSi, buttonNo);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonSi)
            return true;
        return false;
    }

}

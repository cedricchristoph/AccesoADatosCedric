/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iespuertodelacruz.cc;

import es.iespuertodelacruz.cc.modelo.GestorPersonas;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author dama
 */
public class registropersona extends Application {
    
    static GestorPersonas gestor;
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/es/iespuertodelacruz/cc/vista/FXMLPrincipal.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        
    }

    public static GestorPersonas getGestor() {
        return gestor;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        gestor = new GestorPersonas();
        launch(args);
    }
    
}

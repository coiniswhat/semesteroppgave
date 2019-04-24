/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semesterppgave_me;

import Filebehandler.Lagring;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FXMLDocumentController implements Initializable {

    Lagring f = new Lagring();
    @FXML
    public Label Registerng;

    @FXML
    void Registering_lokal_Action(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader8 = new FXMLLoader(getClass().getResource("Lokal.fxml"));
        Parent root8 = (Parent) fxmlLoader8.load();
        Stage stage8 = new Stage();
        stage8.setScene(new Scene(root8));
       stage8.initOwner(Registerng.getScene().getWindow());
        stage8.initModality(Modality.WINDOW_MODAL);
        stage8.showAndWait();
    }

    @FXML
    void Registering_Arrangement_Action(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader7 = new FXMLLoader(getClass().getResource("Registrering_Arrangement.fxml"));
        Parent root7 = (Parent) fxmlLoader7.load();
        Stage stage7 = new Stage();
        stage7.setScene(new Scene(root7));
        stage7.initOwner(Registerng.getScene().getWindow());
        stage7.initModality(Modality.WINDOW_MODAL);
        stage7.showAndWait();
    }




    @FXML
    void Regstering_Kontakt_personer_Action(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader2 = new FXMLLoader(getClass().getResource("Regstering_Kontakt_Person.fxml"));
        Parent root2 = (Parent) fxmlLoader2.load();
        Stage stage2 = new Stage();
        stage2.setScene(new Scene(root2));
        stage2.initOwner(Registerng.getScene().getWindow());
        stage2.initModality(Modality.WINDOW_MODAL);
        stage2.show();
    }
    @FXML
    private Label billetter;

    @FXML
    void Registering_billetter_Action(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("billetter.fxml"));
        Parent root1 = (Parent) fxmlLoader1.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.initOwner(Registerng.getScene().getWindow());
        stage.initModality(Modality.WINDOW_MODAL);
        stage.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}

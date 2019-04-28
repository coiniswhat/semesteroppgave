/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semesterppgave_me;

import Klasser_semesteroppgave.Arrangement;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

abstract class lagring_til_file {

    abstract public void lagre_csv(String m);

    abstract public void lagre_jobj(Object b);
}

class Lagring_csv extends lagring_til_file {
   
    // metode for å skrive til file .csv
    @Override
    public void lagre_csv(String skriv) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Lagre til File");
        fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("Text Files", "*.csv"));
        Stage stage = new Stage();
        File file = fileChooser.showSaveDialog(stage);
        if (file != null) {

            String filePath = file.getPath();
            try {

                FileOutputStream fos = new FileOutputStream(filePath);

                OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");

                BufferedWriter bw = new BufferedWriter(osw);

                bw.write(skriv);
                bw.close();

            } catch (IOException ioe) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("FEIL");
                alert.setHeaderText("Opps.. FEIL");
                alert.setContentText(ioe.getMessage());
                alert.show();
            }
        }

    }

    // metode for å skrive til file .jobj
    @Override
    public void lagre_jobj(Object e) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Lagre til File");
        fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("Text Files", "*.jobj"));
        Stage stage = new Stage();
        File file = fileChooser.showSaveDialog(stage);
        if (file != null) {

            String filePath = file.getPath();
            try {

                FileOutputStream fos = new FileOutputStream(filePath);

                OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");

                BufferedWriter bw = new BufferedWriter(osw);

                bw.write(e.toString());

                bw.close();

            } catch (IOException ioe) {

                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("FEIL");
                alert.setHeaderText("Opps.. FEIL");
                alert.setContentText(ioe.getMessage());
                alert.show();
            }
        }

    }

}


public class FXMLDocumentController implements Initializable {

     Lagring_csv f= new Lagring_csv();
    @FXML
    private TextArea txtArea;
//       metode for å lese file(.csv, .jobj)

    public void lese_file() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Åpen File");
        fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter(".csv file", "*.csv"),
                new ExtensionFilter(".jobj File", "*.jobj"));

        Stage stage = new Stage();
        File selectedFile = fileChooser.showOpenDialog(stage);

        if (selectedFile != null) {

            String filePath = selectedFile.getPath();
            try {

                BufferedReader br = new BufferedReader(new FileReader(filePath));
                String line = "";
                String text = "";
                while ((line = br.readLine()) != null) {
                    text += line + "\n";
                }

                txtArea.setText(text);
                br.close();
            } catch (IOException ioe) {

                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("FEIL");
                alert.setHeaderText("Opps.. Det er et problem");
                alert.setContentText(ioe.getMessage());
                alert.show();
            }
        }
    }

    @FXML
    private Label Regstering;

    @FXML
    void Regstering_Kontakt_personer_Action(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader2 = new FXMLLoader(getClass().getResource("Regstering_Kontakt_Person.fxml"));
        Parent root2 = (Parent) fxmlLoader2.load();
        Stage stage2 = new Stage();
        stage2.setScene(new Scene(root2));
        stage2.show();
    }

    @FXML
    private Label arrangement;

    @FXML
    void Registering_Arrangement_Action(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader7 = new FXMLLoader(getClass().getResource("Registrering_Arrangement.fxml"));
        Parent root7 = (Parent) fxmlLoader7.load();
        Stage stage7 = new Stage();
        stage7.setScene(new Scene(root7));
        stage7.show();
    }
    @FXML
    private TextField navn_på_arrangement;
    @FXML
    private TextField Sted_På_Arrangment;
    @FXML
    private TextField Tids_punkt;
    @FXML
    private TextField Billett_pris;
    @FXML
    private TextField Billett_salg;
    @FXML
    private TextField Liste_på_Arrtister;
    @FXML
    private TextField Program;
    @FXML
    private TextField Sted;

    @FXML // ikke ferdig med dette metode.. mangler andre atributter til class Arrangement. og skrive til skjerm hvis datane blir lagret.
            // her også vi må lagre mtoder for å kontrollere inputer.
    void Arrangment_Action(ActionEvent event) throws IOException {
         Arrangement e = new Arrangement();
         e.setNavn_på_arrangement(navn_på_arrangement.getText());
         e.setKontakt_person_navn("");// ikke ferdig
         e.setArtister(Liste_på_Arrtister.getText());
         e.setProgram_info(Program.getText());
         
         int pris=Integer.parseInt(Billett_pris.getText());
         e.setBillett_pris(pris);
         e.setBillett_salg(Billett_salg.getText());
         e.setSted(Sted.getText());
         f.lagre_jobj(e);
    }
    @FXML  // for å slette data fra inputer
    void Slette_Data(ActionEvent event){
        
    }
    @FXML
    void Åpen_file_Action(ActionEvent event) {
        lese_file();
    }

    @FXML
    private Label billetter;

    @FXML
    void Registering_billetter_Action(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("billetter.fxml"));
        Parent root1 = (Parent) fxmlLoader1.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();
    }

    @FXML
    private Label Lese_arrangement;

    @FXML
    void Lese_arrangment_fra_fil_Action(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader4 = new FXMLLoader(getClass().getResource("Lese_arrangement.fxml"));
        Parent root11 = (Parent) fxmlLoader4.load();
        Stage stage44 = new Stage();
        stage44.setScene(new Scene(root11));
        stage44.show();

    }

    @FXML
    private Label Liste_Kontakt_personer;

    @FXML
    void Liste_på_kontakte_personer_Action(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader3 = new FXMLLoader(getClass().getResource("Liste_kontakt_personer.fxml"));
        Parent root3 = (Parent) fxmlLoader3.load();
        Stage stage3 = new Stage();
        stage3.setScene(new Scene(root3));
        stage3.show();

    }

    @FXML
    private Label Lese_Billettr;

    @FXML
    void Lese_Billetter_File_Action(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader6 = new FXMLLoader(getClass().getResource("Lese_billetter.fxml"));
        Parent root6 = (Parent) fxmlLoader6.load();
        Stage stage6 = new Stage();
        stage6.setScene(new Scene(root6));
        stage6.show();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}

package controller;

import Filebehandler.Lagring;
import Filebehandler.Lese_file;
import Klasser_semesteroppgave.Arrangement;
import Klasser_semesteroppgave.Kontakt_person;
import Klasser_semesteroppgave.Lokal;
import java.io.File;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.converter.DateStringConverter;
import javafx.util.converter.IntegerStringConverter;

public class Fxcontroler_kontakt_person implements Initializable {

    Lese_file lese = new Lese_file();
    Lagring f = new Lagring();
    Kontakt_person e;
    ObservableList<Kontakt_person> liste = FXCollections.observableArrayList();
    @FXML
    private Label Registerng;
    @FXML
    private Label Kontakt_personlbl;

    @FXML
    public TextField Kontakt_Person_navn;
    @FXML
    public TextField Telefonnr;
    @FXML
    public TextField E_posttxt;
    @FXML
    public TextField nettside;
    @FXML
    public TextField Firmatxt;
    @FXML
    public TextField mer_opplysningertxt;

    @FXML
    public Button Registrer_id;
    @FXML
    public Button Avbryte_id;
    @FXML
    public Button Legge_personId;
    @FXML
    public Button oppdater_id;
    @FXML
    public Button slette_persId;
    @FXML
    public RadioButton Radio_Csv;
    @FXML
    public RadioButton Radio_jobj;
    @FXML
    public TableView<Kontakt_person> Person_view;
    @FXML
    public TableColumn<Kontakt_person, String> Navn_v;
    @FXML
    public TableColumn<Kontakt_person, Integer> Telefonner;
    @FXML
    public TableColumn<Kontakt_person, String> E_post;
    @FXML
    public TableColumn<Kontakt_person, String> nettside_v;
    @FXML
    public TableColumn<Kontakt_person, String> Firma;
    @FXML
    public TableColumn<Kontakt_person, String> opplysninger;

    @FXML //ferdig
    private void radioController() {
        if (Radio_Csv.isSelected()) {
            Radio_Csv.requestFocus();
            Radio_jobj.setSelected(false);
            Legge_personId.setDisable(false);
            oppdater_id.setDisable(false);
            Kontakt_personlbl.setText("");
        } else {
            Radio_jobj.requestFocus();
            Radio_Csv.setSelected(false);
            Legge_personId.setDisable(false);
            oppdater_id.setDisable(false);
            Kontakt_personlbl.setText("");
        }
    }

    @FXML
    void Legge_person_ACTION(ActionEvent event) {
        if (!Kontakt_Person_navn.getText().isEmpty() && !Telefonnr.getText().isEmpty() && !E_posttxt.getText().isEmpty()
                && !nettside.getText().isEmpty() && !Firmatxt.getText().isEmpty() && !mer_opplysningertxt.getText().isEmpty()) {
            if (Radio_jobj.isSelected() || Radio_Csv.isSelected()) {
                int tele = Integer.parseInt(Telefonnr.getText());
                e = new Kontakt_person(nettside.getText(), Firmatxt.getText(),
                        mer_opplysningertxt.getText(), Kontakt_Person_navn.getText(), tele,
                        E_posttxt.getText());
                liste.add(e);
                nettside.setText("");
                Firmatxt.setText("");
                mer_opplysningertxt.setText("");
                Kontakt_Person_navn.setText("");
                E_posttxt.setText("");
                Telefonnr.setText("");

            } else {
                Kontakt_personlbl.setText("Velge file dataformat");
                
            }
        } else {
            Kontakt_personlbl.setText("controllere at du har fyllt alle datafeltene");
          

        }
    }

    @FXML
    void Register_PERSON_ACTION(ActionEvent event) {
        if (!Person_view.getSelectionModel().isEmpty()) {
            String path = f.åpenfile();
            if (Radio_Csv.isSelected()) {
                f.lagre_csv(liste, path);
                Radio_jobj.setSelected(false);
                Stage stage = (Stage) Registrer_id.getScene().getWindow();
                stage.close();
                Kontakt_personlbl.setText("");
            } else if (Radio_jobj.isSelected()) {
                f.lagre_jobj(liste, path);
                Radio_Csv.setSelected(false);
                Stage stage = (Stage) Registrer_id.getScene().getWindow();
                stage.close();
                Kontakt_personlbl.setText("");
            } else {
                Kontakt_personlbl.setText("Velge hvor du skal lagre data ");

            }
        } else {
            Kontakt_personlbl.setText("Tableview er tomt.. kunne ikke registere noe til file");

        }
    }

    @FXML //slette person fra tableview
    void slette_person_ACTION(ActionEvent event) {
        if (!Person_view.getSelectionModel().isEmpty()) {
            Person_view.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            Person_view.getItems().remove(Person_view.getSelectionModel().getSelectedIndex());
            Kontakt_personlbl.setText("");
        } //
        else {
            Kontakt_personlbl.setText("Textfield is empty");

        }
    }

    @FXML
    void Avbryte_ACTION(ActionEvent event) {
        Stage stage = (Stage) Avbryte_id.getScene().getWindow();
        stage.close();
    }

    @FXML
    void Åpenfile_ACTION(ActionEvent event) {
        String filepath = lese.åpe_file();
        //lese file som har 
        if (Radio_jobj.isSelected()) {
            lese.lese_file_csv(liste, filepath);
            Radio_jobj.setSelected(false);
            Navn_v.setCellValueFactory(new PropertyValueFactory<>("navn"));
            Telefonner.setCellValueFactory(new PropertyValueFactory<>("telfonnummer"));
            E_post.setCellValueFactory(new PropertyValueFactory<>("epost"));
            nettside_v.setCellValueFactory(new PropertyValueFactory<>("nettside"));
            Firma.setCellValueFactory(new PropertyValueFactory<>("firma"));
            opplysninger.setCellValueFactory(new PropertyValueFactory<>("mer_opplysning"));
            Person_view.setItems(liste);
            Kontakt_personlbl.setText("Du kan endre på data ved å trykke på linje i tableview");
            
        } else if (Radio_jobj.isSelected()) {
            ObservableList<Lokal> list = lese.lese_file(filepath);
            Person_view.setItems(liste);
            Navn_v.setCellValueFactory(new PropertyValueFactory<>("navn"));
            Telefonner.setCellValueFactory(new PropertyValueFactory<>("telfonnummer"));
            E_post.setCellValueFactory(new PropertyValueFactory<>("epost"));
            nettside_v.setCellValueFactory(new PropertyValueFactory<>("nettside"));
            Firma.setCellValueFactory(new PropertyValueFactory<>("firma"));
            opplysninger.setCellValueFactory(new PropertyValueFactory<>("mer_opplysning"));
            Radio_Csv.setSelected(false);
           
            Kontakt_personlbl.setText("Du kan endre på data ved å trykke på linje i tableview");
        }else{
             Kontakt_personlbl.setText("Velge datafelt til filen og prøv på nytt  "+"\n");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        radioController();
        if (!Person_view.getSelectionModel().isEmpty()) {
            Registrer_id.setDisable(false);
        }
        Navn_v.setCellValueFactory(new PropertyValueFactory<>("navn"));
        Telefonner.setCellValueFactory(new PropertyValueFactory<>("telfonnummer"));
        E_post.setCellValueFactory(new PropertyValueFactory<>("epost"));
        nettside_v.setCellValueFactory(new PropertyValueFactory<>("nettside"));
        Firma.setCellValueFactory(new PropertyValueFactory<>("firma"));
        opplysninger.setCellValueFactory(new PropertyValueFactory<>("mer_opplysning"));
        Person_view.setEditable(true);
        Navn_v.setCellFactory(TextFieldTableCell.forTableColumn());
        Navn_v.setOnEditCommit((TableColumn.CellEditEvent<Kontakt_person, String> t) -> {
            ((Kontakt_person) t.getTableView()
                    .getItems()
                    .get(t.getTablePosition().getRow()))
                    .setNavn(t.getNewValue());
        });

        E_post.setCellFactory(TextFieldTableCell.forTableColumn());
        E_post.setOnEditCommit((TableColumn.CellEditEvent<Kontakt_person, String> t) -> {
            ((Kontakt_person) t.getTableView()
                    .getItems()
                    .get(t.getTablePosition().getRow()))
                    .setEpost(t.getNewValue());
        });
        Firma.setCellFactory(TextFieldTableCell.forTableColumn());
        Firma.setOnEditCommit((TableColumn.CellEditEvent<Kontakt_person, String> t) -> {
            ((Kontakt_person) t.getTableView()
                    .getItems()
                    .get(t.getTablePosition().getRow()))
                    .setFirma(t.getNewValue());
        });
        opplysninger.setCellFactory(TextFieldTableCell.forTableColumn());
        opplysninger.setOnEditCommit((TableColumn.CellEditEvent<Kontakt_person, String> t) -> {
            ((Kontakt_person) t.getTableView()
                    .getItems()
                    .get(t.getTablePosition().getRow()))
                    .setMer_opplysning(t.getNewValue());
        });
        Telefonner.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        Telefonner.setOnEditCommit((TableColumn.CellEditEvent<Kontakt_person, Integer> t) -> {
            ((Kontakt_person) t.getTableView()
                    .getItems()
                    .get(t.getTablePosition().getRow()))
                    .setTelfonnummer(t.getNewValue());
        });

        Person_view.setItems(liste);
        Person_view.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

}

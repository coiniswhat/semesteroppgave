/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import exception.Exceptiontxt;
import Filebehandler.Lagring;
import Filebehandler.Lese_file;
import Klasser_semesteroppgave.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import validert.validation;

public class FXLokal_controller implements Initializable {

    // lage tooltip for alle knapper og textfield som gir informasjon
// hvordan vi aktiere button etter å sette button.setDisable(true);
    // hvordan skal vi lagre i samme file hvergang vi lagre
    // lage ny class for writ og read fraom file
    // bytte desig listview to tableview
    // brke observableliste for å lese fra file og skrive til file
    // lage hjelpe metode for alle if setninger
    Lagring f = new Lagring();
    Lokal e;
    Lese_file lese = new Lese_file();

    ObservableList<Lokal> liste = FXCollections.observableArrayList();

    @FXML
    public Label lblLokal;
    @FXML
    public Button Lagre_lokal;

    @FXML
    public Button legg_til_lokal;
    @FXML
    public Button slette_lokal;
    @FXML
    public Button closeButton;
    @FXML
    public Button open_id;

    @FXML
    public TextField Lokal_navn;

    @FXML
    public TextField Type_Lokal;

    @FXML
    public TextField Antall_plasser;

    @FXML
    public TableView<Lokal> lokal_view;
    @FXML
    public TableColumn<Lokal, String> navn;
    @FXML
    public TableColumn<Lokal, String> Type;
    @FXML
    public TableColumn<Lokal, Integer> antall;

    @FXML
    public RadioButton Radio_Csv;

    @FXML
    public RadioButton Radio_jobj;

    @FXML // METODE FOR Å CONTROLLERE RADIOBUTTON
    void radioControll() {
        if (Radio_Csv.isSelected()) {
            Radio_Csv.requestFocus();
            Radio_jobj.setSelected(false);
            legg_til_lokal.setDisable(false);
            open_id.setDisable(false);
            lblLokal.setText("");

        } else {
            Radio_jobj.requestFocus();
            Radio_Csv.setSelected(false);
            open_id.setDisable(false);
            legg_til_lokal.setDisable(false);
            lblLokal.setText("");
        }
    }
// her er det mtoder for å kontrollere TEXTFIELD.

    private Boolean ControllStringLokal() {
        return validation.textAlphabet(Lokal_navn, lblLokal, "Skriv bukstaver mellom a - z i lokal navn" + "\n");
    }

    private Boolean ControllStringType() {
        return validation.textAlphabet(Type_Lokal, lblLokal, "Skriv bukstaver mellom a - z i lokal type" + "\n");
    }

    private Boolean controllValid() {
        return validation.textNumeric(Antall_plasser, lblLokal, "Skriv tall mellom 0-9 i antall plasser" + "\n");
    }

//// her er det mtoder for å kontrollere colomn.
//
//    private Boolean ControllStringLokalcol() {
//        return validation.textAlphabet(navn, lblLokal, "Skriv bukstaver mellom a - z i colomn lokal navn" + "\n");
//    }
//
//    private Boolean ControllStringTypecol() {
//        return validation.textAlphabet(Type_Lokal, lblLokal, "Skriv bukstaver mellom a - z i colomn lokal type" + "\n");
//    }
//
//    private Boolean controllValidcol() {
//        return validation.textNumeric(antall, lblLokal, "Skriv tall mellom 0-9 i colomn antall plasser" + "\n");
//    }
    @FXML // Knapp for å legge lokal i tableview
    public void Legge_lokal_Action(ActionEvent event) throws IOException {
        if (ControllStringLokal() && ControllStringType() && controllValid()) {
            if (!Lokal_navn.getText().isEmpty() && !Type_Lokal.getText().isEmpty() && !Antall_plasser.getText().isEmpty()) {
                // brukern må velge data fileformat før lagring av datane til table
                if (Radio_jobj.isSelected() || Radio_Csv.isSelected()) {

                    int Antall = Integer.parseInt(Antall_plasser.getText());

                    assert Antall >= 0 : "må skriv positiv tall";
                    String name = Lokal_navn.getText();
                    String ty = Type_Lokal.getText();
                    liste.add(new Lokal(name, ty, Antall)); // her legger data til liste 

                    lokal_view.getSelectionModel().selectLast();
                    Lokal_navn.setText("");
                    Type_Lokal.setText("");
                    Antall_plasser.setText("");
                    lblLokal.setText("");
                } else { // hvis ikke ble velgs filedataformat gis feil melding
                    lblLokal.setText("Velge file dataformat" + "\n");

                }

            } else {// hvis textfield er tomt 

                lblLokal.setText("controllere at du har fyllt alle datafeltene med riktig data" + "\n");
            }
        }

    }

    @FXML // velge en linje eller flere linjer i table og slette den 
    public void Slette_lokal_Action(ActionEvent event) throws IOException {
        if (!lokal_view.getSelectionModel().isEmpty()) {
            lokal_view.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            lokal_view.getItems().remove(lokal_view.getSelectionModel().getSelectedIndex());
            lblLokal.setText("");
        } //
        else {
            lblLokal.setText("Tableview er tomt");

        }

    }

    @FXML // knapp for å lagre data fra tableview til file. 
    void Lagre_lokal_til_file_Action(ActionEvent event) throws IOException {

        if (liste.isEmpty()) { // hvis ikke tableview er tomt 
            lblLokal.setText("Tableview er tomt.. kunne ikke registere noe til file" + "\n");
        } else {
            String path = f.åpenfile();
            if (Radio_Csv.isSelected()) { // velg datafileformat for åpne file 
                f.lagre_csv(liste, path);
                Radio_jobj.setSelected(false);
                Stage stage = (Stage) Lagre_lokal.getScene().getWindow();
                stage.close();
                lblLokal.setText("");
            } else if (Radio_jobj.isSelected()) {
                f.lagre_jobj(liste, path);
                Radio_Csv.setSelected(false);
                Stage stage = (Stage) Lagre_lokal.getScene().getWindow();
                stage.close();
                lblLokal.setText("");
            } else {
                lblLokal.setText("Velge hvor du skal lagre data " + "\n");

            }

        }
    }

    @FXML // for å løkke vinduet til lokal
    void Avbrytte_lokal_Action(ActionEvent event) throws IOException {

        Stage stage = (Stage) closeButton.getScene().getWindow();

        stage.close();
    }

    @FXML  // åpne file for å vise data og oppdatere dem 
    void Åpne_file_Action(ActionEvent event) throws IOException {
        liste = FXCollections.observableArrayList();
        String filepath = lese.åpe_file();
        if (Radio_Csv.isSelected()) {
            lese.lese_file_csv(liste, filepath);// lese fra file
            Radio_jobj.setSelected(false);
            // legge data fra file i tabeview
            lokal_view.setItems(liste);
            navn.setCellValueFactory(new PropertyValueFactory<>("Lokal_navn"));
            Type.setCellValueFactory(new PropertyValueFactory<>("type"));
            antall.setCellValueFactory(new PropertyValueFactory<>("Antall_plasser"));
            lblLokal.setText("Du kan endre på data ved å trykke på linje i tableview" + "\n");
        } else if (Radio_jobj.isSelected()) {

            ObservableList<Lokal> list = lese.lese_file(filepath);
            lokal_view.setItems(list);
            navn.setCellValueFactory(new PropertyValueFactory<>("Lokal_navn"));
            Type.setCellValueFactory(new PropertyValueFactory<>("type"));
            antall.setCellValueFactory(new PropertyValueFactory<>("Antall_plasser"));

            Radio_Csv.setSelected(false);

            lblLokal.setText("Du kan endre på data ved å trykke på linje i tableview" + "\n");
        } else {
            lblLokal.setText("Velge datafelt til filen og prøv på nytt  " + "\n");

        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        radioControll();
        lokal_view.setEditable(true);

        navn.setCellValueFactory(new PropertyValueFactory<>("Lokal_navn"));
        Type.setCellValueFactory(new PropertyValueFactory<>("type"));
        antall.setCellValueFactory(new PropertyValueFactory<>("Antall_plasser"));
         // brukeren kan endre på data i tableview
        lokal_view.setEditable(true);
        navn.setCellFactory(TextFieldTableCell.forTableColumn());
        navn.setOnEditCommit((TableColumn.CellEditEvent<Lokal, String> t) -> {
            ((Lokal) t.getTableView()
                    .getItems()
                    .get(t.getTablePosition().getRow()))
                    .setLokal_navn(t.getNewValue());
        });
        Type.setCellFactory(TextFieldTableCell.forTableColumn());
        Type.setOnEditCommit((TableColumn.CellEditEvent<Lokal, String> t) -> {
            ((Lokal) t.getTableView()
                    .getItems()
                    .get(t.getTablePosition().getRow()))
                    .setType(t.getNewValue());
        });
        antall.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        antall.setOnEditCommit((TableColumn.CellEditEvent<Lokal, Integer> t) -> {
            ((Lokal) t.getTableView()
                    .getItems()
                    .get(t.getTablePosition().getRow()))
                    .setAntall_plasser(t.getNewValue());
        });

        lokal_view.setItems(liste);
        lokal_view.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }
}

//}

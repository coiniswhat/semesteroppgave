/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Filebehandler.Lagring;
import Filebehandler.Lese_file;
import Klasser_semesteroppgave.*;
import java.io.File;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

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

    @FXML
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

// her også vi må lagre mtoder for å kontrollere inputer.
    @FXML
    public void Legge_lokal_Action(ActionEvent event) throws IOException {

        if (!Lokal_navn.getText().isEmpty() && !Type_Lokal.getText().isEmpty() && !Antall_plasser.getText().isEmpty()) {

            if (Radio_jobj.isSelected() || Radio_Csv.isSelected()) {
                int Antall = Integer.parseInt(Antall_plasser.getText());

                String name = Lokal_navn.getText();
                String ty = Type_Lokal.getText();
                liste.add(new Lokal(name, ty, Antall));

                lokal_view.getSelectionModel().selectLast();
                Lokal_navn.setText("");
                Type_Lokal.setText("");
                Antall_plasser.setText("");
                lblLokal.setText("");
            } // lage exception når textfiled er empty 
            else {
                lblLokal.setText("Velge file dataformat");
                legg_til_lokal.setDisable(true);
            }

        } else {
            lblLokal.setText("controllere at du har fyllt alle datafeltene");
            legg_til_lokal.setDisable(true);

        }
    }

    @FXML
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

    @FXML
    void Lagre_lokal_til_file_Action(ActionEvent event) throws IOException {

        if (!lokal_view.getSelectionModel().isEmpty()) {
            
            String path = f.åpenfile();
            if (Radio_Csv.isSelected()) {
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
                lblLokal.setText("Velge hvor du skal lagre data ");

            }
        } else {
            lblLokal.setText("Tableview er tomt.. kunne ikke registere noe til file");

        }
    }

    @FXML
    void Avbrytte_lokal_Action(ActionEvent event) throws IOException {

        Stage stage = (Stage) closeButton.getScene().getWindow();

        stage.close();
    }

    @FXML
    void Åpne_file_Action(ActionEvent event) throws IOException {

        String filepath = lese.åpe_file();
        if (Radio_Csv.isSelected()) {
            lese.lese_file_csv(liste, filepath);
            Radio_jobj.setSelected(false);
            navn.setCellValueFactory(new PropertyValueFactory<>("Lokal_navn"));
            Type.setCellValueFactory(new PropertyValueFactory<>("type"));
            antall.setCellValueFactory(new PropertyValueFactory<>("Antall_plasser"));
            lokal_view.setItems(liste);
            lblLokal.setText("Du kan endre på data ved å trykke på linje i tableview");
        } else if (Radio_jobj.isSelected()) {

            ObservableList<Lokal> list = lese.lese_file(filepath);
            lokal_view.setItems(liste);
            navn.setCellValueFactory(new PropertyValueFactory<>("Lokal_navn"));
            Type.setCellValueFactory(new PropertyValueFactory<>("type"));
            antall.setCellValueFactory(new PropertyValueFactory<>("Antall_plasser"));
        
            Radio_Csv.setSelected(false);
            
            lblLokal.setText("Du kan endre på data ved å trykke på linje i tableview");
        } else {
            lblLokal.setText("Velge datafelt til filen og prøv på nytt  "+"\n");
            
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        radioControll();
        navn.setCellValueFactory(new PropertyValueFactory<>("Lokal_navn"));
        Type.setCellValueFactory(new PropertyValueFactory<>("type"));
        antall.setCellValueFactory(new PropertyValueFactory<>("Antall_plasser"));

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

        lokal_view.setEditable(true);

        lokal_view.setItems(liste);
        lokal_view.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

    }

}

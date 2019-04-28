/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Filebehandler.Lagring;
import Filebehandler.Lese_file;
import Klasser_semesteroppgave.Billett;
import Klasser_semesteroppgave.Lokal;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.DateStringConverter;
import javafx.util.converter.IntegerStringConverter;

public class fx_contoller_billett implements Initializable {

    Lagring f = new Lagring();
    Billett e;
    Lese_file lese = new Lese_file();
    ObservableList<Billett> liste = FXCollections.observableArrayList();
    @FXML
    private Label Registerng;
    @FXML
    private Label Billettlbl;

    @FXML
    private Button Registere_id;

    @FXML
    private Button Avbrytte_id;
    @FXML
    private Button Legg_Billett_id;
    @FXML
    private Button Slette_id;
    @FXML
    private Button Åpen_id;

    @FXML
    private TextField navntxt;
    @FXML
    private TextField Ettnavntxt;

    @FXML
    private TextField teletxt;

    @FXML
    private TextField eposttxt;
    @FXML
    private TextField datotxt;

    @FXML
    private TextField kloktxt;

    @FXML
    private TextField pristxt;
    @FXML
    private TextField passtxt;

    @FXML
    private TextField lokaltxt;

    @FXML
    private TextField arrantxt;
    @FXML
    private Tooltip Register_tool;

    @FXML
    private TableView<Billett> Bllett_view;
    @FXML
    private TableColumn<Billett, String> Navn_col;
    @FXML
    private TableColumn<Billett, Integer> Tele_col;
    @FXML
    private TableColumn<Billett, String> epost_col;
    @FXML
    private TableColumn<Billett, Date> Dato_col;
    @FXML
    private TableColumn<Billett, Date> klokk_col;
    @FXML
    private TableColumn<Billett, Integer> Pris_col;
    @FXML
    private TableColumn<Billett, Integer> plass_col;
    @FXML
    private TableColumn<Billett, String> lokal_col;
    @FXML
    private TableColumn<Billett, String> arrang_col;
    @FXML
    private TableColumn<Billett, String> Etter_col;

    @FXML
    private RadioButton Radio_Csv;

    @FXML
    private RadioButton Radio_jobj;

    @FXML
    void RadioController() {
        if (Radio_Csv.isSelected()) {
            Radio_Csv.requestFocus();
            Radio_jobj.setSelected(false);
            Legg_Billett_id.setDisable(false);
            Åpen_id.setDisable(false);
            Billettlbl.setText("");

        } else {
            Radio_jobj.requestFocus();
            Radio_Csv.setSelected(false);
            Åpen_id.setDisable(false);
            Legg_Billett_id.setDisable(false);
            Billettlbl.setText("");
        }
    }

    @FXML
    void Legge_billett_Action(ActionEvent event) throws ParseException {
        
        if (!navntxt.getText().isEmpty() && !teletxt.getText().isEmpty() && !eposttxt.getText().isEmpty()
                && !kloktxt.getText().isEmpty() && !pristxt.getText().isEmpty() && !passtxt.getText().isEmpty()
                && !datotxt.getText().isEmpty() && !lokaltxt.getText().isEmpty() && !arrantxt.getText().isEmpty() && !Ettnavntxt.getText().isEmpty()) {
            if (Radio_jobj.isSelected() || Radio_Csv.isSelected()) {
                int plass = Integer.parseInt(passtxt.getText());
                int pr = Integer.parseInt(pristxt.getText());
                int tel = Integer.parseInt(teletxt.getText());
                Date dato = new SimpleDateFormat("dd/MM/yyyy").parse(datotxt.getText());
                Date klokke = new SimpleDateFormat("HH:MM").parse(kloktxt.getText());
                e = new Billett(plass, navntxt.getText(), pr, tel, eposttxt.getText(), dato, klokke, lokaltxt.getText(), arrantxt.getText(), Ettnavntxt.getText());
                liste.add(e);
                Bllett_view.getSelectionModel().selectLast();
                navntxt.setText("");
                teletxt.setText("");
                eposttxt.setText("");
                kloktxt.setText("");
                pristxt.setText("");
                passtxt.setText("");
                datotxt.setText("");
                lokaltxt.setText("");
                arrantxt.setText("");
                Ettnavntxt.setText("");
                Billettlbl.setText("");

            }// lage exception når textfiled er empty 
            else {
                Billettlbl.setText("Velge file dataformat");
                Legg_Billett_id.setDisable(true);
            }
        } else {
            Billettlbl.setText("controllere at du har fyllt alle datafeltene");
            Legg_Billett_id.setDisable(true);
        }
    }

    @FXML
    void Slette_Billett_Action(ActionEvent event) {
        if (!Bllett_view.getSelectionModel().isEmpty()) {
            Bllett_view.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            Bllett_view.getItems().remove(Bllett_view.getSelectionModel().getSelectedIndex());
            Billettlbl.setText("");
        } //
        else {
            Billettlbl.setText("Velge en linje i table for å slette ");

        }
    }

    @FXML
    void Registering_billetter_Action(ActionEvent event) {

        if (liste.isEmpty()) {
            Billettlbl.setText("Tableview er tomt.. kunne ikke registere noe til file");
        } else {
            String path = f.åpenfile();
            if (Radio_Csv.isSelected()) {
                f.lagre_csv(liste, path);
                Radio_jobj.setSelected(false);
                Stage stage = (Stage) Registere_id.getScene().getWindow();
                stage.close();
                Billettlbl.setText("");
            } else if (Radio_jobj.isSelected()) {
                f.lagre_jobj(liste, path);
                Radio_Csv.setSelected(false);
                Stage stage = (Stage) Registere_id.getScene().getWindow();
                stage.close();
                Billettlbl.setText("");
            } else {
                Billettlbl.setText("Velge hvor du skal lagre data ");

            }
        }

    }


@FXML  // for å slette data fra inputer
    void Avbryte_Action(ActionEvent event) {
        Stage stage = (Stage) Avbrytte_id.getScene().getWindow();

        stage.close();
    }

    @FXML
    void open_File_Action(ActionEvent event) {
        String filepath = lese.åpe_file();
         liste = FXCollections.observableArrayList();
        if (Radio_Csv.isSelected()) {
            lese.lese_file_csv(liste, filepath);
            Radio_jobj.setSelected(false);

            Navn_col.setCellValueFactory(new PropertyValueFactory<>("navn"));
            Etter_col.setCellValueFactory(new PropertyValueFactory<>("etternavn"));
            epost_col.setCellValueFactory(new PropertyValueFactory<>("epost"));
            Dato_col.setCellValueFactory(new PropertyValueFactory<>("dato"));
            klokk_col.setCellValueFactory(new PropertyValueFactory<>("klokke"));
            Pris_col.setCellValueFactory(new PropertyValueFactory<>("pris"));
            plass_col.setCellValueFactory(new PropertyValueFactory<>("plass_nummer"));
            lokal_col.setCellValueFactory(new PropertyValueFactory<>("lokal"));
            arrang_col.setCellValueFactory(new PropertyValueFactory<>("arrangement"));
            Tele_col.setCellValueFactory(new PropertyValueFactory<>("telefonnummer_til_kjper"));
            Bllett_view.setItems(liste);
            Billettlbl.setText("Du kan endre på data ved å trykke på linje i tableview");
        } else if (Radio_jobj.isSelected()) {

            ObservableList<Billett> list = lese.lese_file(filepath);
            Bllett_view.setItems(list);
            Navn_col.setCellValueFactory(new PropertyValueFactory<>("navn"));
            Etter_col.setCellValueFactory(new PropertyValueFactory<>("etternavn"));
            epost_col.setCellValueFactory(new PropertyValueFactory<>("epost"));
            Dato_col.setCellValueFactory(new PropertyValueFactory<>("dato"));
            klokk_col.setCellValueFactory(new PropertyValueFactory<>("klokke"));
            Pris_col.setCellValueFactory(new PropertyValueFactory<>("pris"));
            plass_col.setCellValueFactory(new PropertyValueFactory<>("plass_nummer"));
            lokal_col.setCellValueFactory(new PropertyValueFactory<>("lokal"));
            arrang_col.setCellValueFactory(new PropertyValueFactory<>("arrangement"));
            Tele_col.setCellValueFactory(new PropertyValueFactory<>("telefonnummer_til_kjper"));

            Radio_Csv.setSelected(false);

            Billettlbl.setText("Du kan endre på data ved å trykke på linje i tableview");
        } else {
            Billettlbl.setText("Velge datafelt til filen og prøv på nytt  " + "\n");

        }

    }

    @Override
        public void initialize(URL url, ResourceBundle rb) {
        RadioController();
      
        Navn_col.setCellValueFactory(new PropertyValueFactory<>("navn"));
        Etter_col.setCellValueFactory(new PropertyValueFactory<>("etternavn"));
        epost_col.setCellValueFactory(new PropertyValueFactory<>("epost"));
        Dato_col.setCellValueFactory(new PropertyValueFactory<>("dato"));
        klokk_col.setCellValueFactory(new PropertyValueFactory<>("klokke"));
        Pris_col.setCellValueFactory(new PropertyValueFactory<>("pris"));
        plass_col.setCellValueFactory(new PropertyValueFactory<>("plass_nummer"));
        lokal_col.setCellValueFactory(new PropertyValueFactory<>("lokal"));
        arrang_col.setCellValueFactory(new PropertyValueFactory<>("arrangement"));
        Tele_col.setCellValueFactory(new PropertyValueFactory<>("telefonnummer_til_kjper"));

        Navn_col.setCellFactory(TextFieldTableCell.forTableColumn());
        Navn_col.setOnEditCommit((TableColumn.CellEditEvent<Billett, String> t) -> {
            ((Billett) t.getTableView()
                    .getItems()
                    .get(t.getTablePosition().getRow()))
                    .setNavn(t.getNewValue());
        });
        Etter_col.setCellFactory(TextFieldTableCell.forTableColumn());
        Etter_col.setOnEditCommit((TableColumn.CellEditEvent<Billett, String> t) -> {
            ((Billett) t.getTableView()
                    .getItems()
                    .get(t.getTablePosition().getRow()))
                    .setEtternavn(t.getNewValue());
        });
        epost_col.setCellFactory(TextFieldTableCell.forTableColumn());
        epost_col.setOnEditCommit((TableColumn.CellEditEvent<Billett, String> t) -> {
            ((Billett) t.getTableView()
                    .getItems()
                    .get(t.getTablePosition().getRow()))
                    .setEtternavn(t.getNewValue());
        });
        Dato_col.setCellFactory(TextFieldTableCell.forTableColumn(new DateStringConverter()));
        Dato_col.setOnEditCommit((TableColumn.CellEditEvent<Billett, Date> t) -> {
            ((Billett) t.getTableView()
                    .getItems()
                    .get(t.getTablePosition().getRow()))
                    .setDato(t.getNewValue());
        });
        klokk_col.setCellFactory(TextFieldTableCell.forTableColumn(new DateStringConverter()));
        klokk_col.setOnEditCommit((TableColumn.CellEditEvent<Billett, Date> t) -> {
            ((Billett) t.getTableView()
                    .getItems()
                    .get(t.getTablePosition().getRow()))
                    .setKlokke(t.getNewValue());
        });
        Pris_col.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        Pris_col.setOnEditCommit((TableColumn.CellEditEvent<Billett, Integer> t) -> {
            ((Billett) t.getTableView()
                    .getItems()
                    .get(t.getTablePosition().getRow()))
                    .setPris(t.getNewValue());
        });
        plass_col.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        plass_col.setOnEditCommit((TableColumn.CellEditEvent<Billett, Integer> t) -> {
            ((Billett) t.getTableView()
                    .getItems()
                    .get(t.getTablePosition().getRow()))
                    .setPlass_nummer(t.getNewValue());
        });
        Tele_col.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        Tele_col.setOnEditCommit((TableColumn.CellEditEvent<Billett, Integer> t) -> {
            ((Billett) t.getTableView()
                    .getItems()
                    .get(t.getTablePosition().getRow()))
                    .setTelefonnummer_til_kjper(t.getNewValue());
        });
        lokal_col.setCellFactory(TextFieldTableCell.forTableColumn());
        lokal_col.setOnEditCommit((TableColumn.CellEditEvent<Billett, String> t) -> {
            ((Billett) t.getTableView()
                    .getItems()
                    .get(t.getTablePosition().getRow()))
                    .setLokal(t.getNewValue());
        });
        arrang_col.setCellFactory(TextFieldTableCell.forTableColumn());
        arrang_col.setOnEditCommit((TableColumn.CellEditEvent<Billett, String> t) -> {
            ((Billett) t.getTableView()
                    .getItems()
                    .get(t.getTablePosition().getRow()))
                    .setArrangement(t.getNewValue());
        });
        Bllett_view.setEditable(true);

        Bllett_view.setItems(liste);
        Bllett_view.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

}

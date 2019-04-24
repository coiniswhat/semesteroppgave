/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Filebehandler.Lagring;
import Filebehandler.Lese_file;
import Klasser_semesteroppgave.Arrangement;
import Klasser_semesteroppgave.Lokal;
import java.io.File;
import java.io.IOException;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.DateStringConverter;
import javafx.util.converter.IntegerStringConverter;

public class FX_Arrangment_contoller implements Initializable {
// lese file for å legge type,  kontakt person og sted i combobox

    Lese_file lese = new Lese_file();
    Lagring f = new Lagring();
    Arrangement e;
    ObservableList<Arrangement> liste = FXCollections.observableArrayList();
    @FXML
    public Label lblTxt_Arrangment;

    @FXML
    public TextField navn_på_arrangement;
    @FXML
    public TextField Tids_punkt;
    @FXML
    public TextField Billett_pris;
    @FXML
    public TextField ty_txt;
    @FXML
    public TextField Billett_salg;
    @FXML
    public TextField ArrtisterField;
    @FXML
    public TextField Program;
    @FXML
    public TextField sted_txt;
    @FXML
    public TextField kontaktPersontxt;
    @FXML
    public TextField arte;
    @FXML
    public Button Registrer_id;
    @FXML
    public Button Avbryte_id;
    @FXML
    public Button legg_id;
    @FXML
    public Button oppdater_id;
    @FXML
    public Button Artist_id;
    @FXML
    public Button slette_v;
    @FXML
    public Button slett_artist_id;
    @FXML
    public RadioButton Radio_Csv;

    @FXML
    public RadioButton Radio_jobj;

    @FXML
    public TableView<Arrangement> Arrang_view;
    @FXML
    public TableColumn<Arrangement, String> Arrangement;
    @FXML
    public TableColumn<Arrangement, String> Sted_v;
    @FXML
    public TableColumn<Arrangement, Date> Tid;
    @FXML
    public TableColumn<Arrangement, String> Artist;
    @FXML
    public TableColumn<Arrangement, String> salg;
    @FXML
    public TableColumn<Arrangement, String> personcol;
    @FXML
    public TableColumn<Arrangement, String> typecol;
    @FXML
    public TableColumn<Arrangement, String> pro;
    @FXML
    public TableColumn<Arrangement, Integer> pris;

    @FXML //ferdig
    public void radioController() {
        if (Radio_Csv.isSelected()) {
            Radio_Csv.requestFocus();
            Radio_jobj.setSelected(false);
            legg_id.setDisable(false);
            lblTxt_Arrangment.setText("");
            // lage tooltip
        } else if (Radio_jobj.isSelected()) {
            Radio_jobj.requestFocus();
            Radio_Csv.setSelected(false);
            legg_id.setDisable(false);
            lblTxt_Arrangment.setText("");
            oppdater_id.setDisable(false);
        }

    }
// her også vi må lagre mtoder for å kontrollere inputer.

    @FXML
    public void Legg_Arrangement_Action(ActionEvent event
    ) throws ParseException {
        if (!navn_på_arrangement.getText().isEmpty() && !sted_txt.getText().isEmpty() && !Tids_punkt.getText().isEmpty()
                && !Billett_pris.getText().isEmpty() && !Billett_salg.getText().isEmpty() && !kontaktPersontxt.getText().isEmpty()
                && !ty_txt.getText().isEmpty() && !ArrtisterField.getText().isEmpty() && !Program.getText().isEmpty()) {
            if (Radio_jobj.isSelected() || Radio_Csv.isSelected()) {
                int pris1 = Integer.parseInt(Billett_pris.getText());
                // lage exception hvis man skrive forskjellige format
                Date date = new SimpleDateFormat("dd.MM.yyyy").parse(Tids_punkt.getText());
                String sted = sted_txt.getText();
                String person = kontaktPersontxt.getText();
                String type = ty_txt.getText();

                e = new Arrangement(person, navn_på_arrangement.getText(),
                        date, type, ArrtisterField.getText(),
                        Program.getText(), sted, pris1, Billett_salg.getText());
                liste.add(e);
                ArrtisterField.setText("");
                Tids_punkt.setText("");
                navn_på_arrangement.setText("");
                Billett_pris.setText("");
                Billett_salg.setText("");
                Program.setText("");
                ty_txt.setText("");
                sted_txt.setText("");
                kontaktPersontxt.setText("");
            } else {
                lblTxt_Arrangment.setText("Velge file dataformat");
                legg_id.setDisable(true);
            }

        } else {
            lblTxt_Arrangment.setText("controller at du har fyllt alle datafeltene");
            legg_id.setDisable(true);

        }

    }

    @FXML // ferdig men trenges testing
    public void Arrangment_Action(ActionEvent event) throws IOException {
        if (!Arrang_view.getSelectionModel().isEmpty()) {
            String path = f.åpenfile();
            if (Radio_Csv.isSelected()) {
                f.lagre_csv(liste, path);
                Radio_jobj.setSelected(false);
                Stage stage = (Stage) Registrer_id.getScene().getWindow();
                stage.close();
                lblTxt_Arrangment.setText("");
            } else if (Radio_jobj.isSelected()) {
                f.lagre_jobj(liste, path);
                Radio_Csv.setSelected(false);
                Stage stage = (Stage) Registrer_id.getScene().getWindow();
                stage.close();
                lblTxt_Arrangment.setText("");
            } else {
                lblTxt_Arrangment.setText("Velge hvor du skal lagre data ");
                Registrer_id.setDisable(true);
            }
        } else {
            lblTxt_Arrangment.setText("Tableview er tomt.. kunne ikke registere noe til file");

        }
    }

    @FXML// slette data fra table view
    public void slette_v_Action(ActionEvent event) throws IOException {
        if (!Arrang_view.getSelectionModel().isEmpty()) {
            Arrang_view.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            Arrang_view.getItems().remove(Arrang_view.getSelectionModel().getSelectedIndex());
            lblTxt_Arrangment.setText("");
        } //
        else {
            lblTxt_Arrangment.setText("Tableview er tomt");

        }
    }

    @FXML  // avbryte ferdig
    public void Slette_Data(ActionEvent event
    ) {
        Stage stage = (Stage) Avbryte_id.getScene().getWindow();
        stage.close();
    }

    @FXML  // for å lese file og så endring på file
    public void Åpen_file_Action(ActionEvent event
    ) {
       
        String filepath = lese.åpe_file();
        if (Radio_Csv.isSelected()) {
            lese.lese_file_csv(liste, filepath);
            Radio_jobj.setSelected(false);
             Arrang_view.setItems(liste);
            Arrangement.setCellValueFactory(new PropertyValueFactory<>("navn_på_arrangement"));
            Sted_v.setCellValueFactory(new PropertyValueFactory<>("sted"));
            Tid.setCellValueFactory(new PropertyValueFactory<>("tidspunkt"));
            Artist.setCellValueFactory(new PropertyValueFactory<>("Artister"));
            salg.setCellValueFactory(new PropertyValueFactory<>("billett_salg"));
            personcol.setCellValueFactory(new PropertyValueFactory<>("kontakt_person_navn"));
            typecol.setCellValueFactory(new PropertyValueFactory<>("type"));
            pro.setCellValueFactory(new PropertyValueFactory<>("program_info"));
            pris.setCellValueFactory(new PropertyValueFactory<>("billett_pris"));
           
             lblTxt_Arrangment.setText("Du kan endre på data ved å trykke på linje i tableview");
        }else if (Radio_jobj.isSelected()) {
             ObservableList<Lokal> list = lese.lese_file(filepath);
            
               Arrang_view.setItems(liste);
               Arrangement.setCellValueFactory(new PropertyValueFactory<>("navn_på_arrangement"));
            Sted_v.setCellValueFactory(new PropertyValueFactory<>("sted"));
            Tid.setCellValueFactory(new PropertyValueFactory<>("tidspunkt"));
            Artist.setCellValueFactory(new PropertyValueFactory<>("Artister"));
            salg.setCellValueFactory(new PropertyValueFactory<>("billett_salg"));
            personcol.setCellValueFactory(new PropertyValueFactory<>("kontakt_person_navn"));
            typecol.setCellValueFactory(new PropertyValueFactory<>("type"));
            pro.setCellValueFactory(new PropertyValueFactory<>("program_info"));
            pris.setCellValueFactory(new PropertyValueFactory<>("billett_pris"));
            Radio_Csv.setSelected(false);
            Registrer_id.setDisable(false);
            lblTxt_Arrangment.setText("Du kan endre på data ved å trykke på linje i tableview");
        }else {
            lblTxt_Arrangment.setText("Velge datafelt til filen og prøv på nytt  ");
            oppdater_id.setDisable(true);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb
    ) {
      
        radioController();
      
        Arrangement.setCellValueFactory(new PropertyValueFactory<>("navn_på_arrangement"));
        Sted_v.setCellValueFactory(new PropertyValueFactory<>("sted"));
        Tid.setCellValueFactory(new PropertyValueFactory<>("tidspunkt"));
        Artist.setCellValueFactory(new PropertyValueFactory<>("Artister"));
        salg.setCellValueFactory(new PropertyValueFactory<>("billett_salg"));
        personcol.setCellValueFactory(new PropertyValueFactory<>("kontakt_person_navn"));
        typecol.setCellValueFactory(new PropertyValueFactory<>("type"));
        pro.setCellValueFactory(new PropertyValueFactory<>("program_info"));
        pris.setCellValueFactory(new PropertyValueFactory<>("billett_pris"));
        // brukern kan endre på data i tableview
        Arrang_view.setEditable(true);
        Arrangement.setCellFactory(TextFieldTableCell.forTableColumn());
        Arrangement.setOnEditCommit((TableColumn.CellEditEvent<Arrangement, String> t) -> {
            ((Arrangement) t.getTableView()
                    .getItems()
                    .get(t.getTablePosition().getRow()))
                    .setNavn_på_arrangement(t.getNewValue());
        });
        Sted_v.setCellFactory(TextFieldTableCell.forTableColumn());
        Sted_v.setOnEditCommit((TableColumn.CellEditEvent<Arrangement, String> t) -> {
            ((Arrangement) t.getTableView()
                    .getItems()
                    .get(t.getTablePosition().getRow()))
                    .setSted(t.getNewValue());
        });
        Tid.setCellFactory(TextFieldTableCell.forTableColumn(new DateStringConverter()));
        Tid.setOnEditCommit((TableColumn.CellEditEvent<Arrangement, Date> t) -> {
            ((Arrangement) t.getTableView()
                    .getItems()
                    .get(t.getTablePosition().getRow()))
                    .setTidspunkt(t.getNewValue());
        });
        Artist.setCellFactory(TextFieldTableCell.forTableColumn());
        Artist.setOnEditCommit((TableColumn.CellEditEvent<Arrangement, String> t) -> {
            ((Arrangement) t.getTableView()
                    .getItems()
                    .get(t.getTablePosition().getRow()))
                    .setArtister(t.getNewValue());
        });
        salg.setCellFactory(TextFieldTableCell.forTableColumn());
        salg.setOnEditCommit((TableColumn.CellEditEvent<Arrangement, String> t) -> {
            ((Arrangement) t.getTableView()
                    .getItems()
                    .get(t.getTablePosition().getRow()))
                    .setBillett_salg(t.getNewValue());
        });
        personcol.setCellFactory(TextFieldTableCell.forTableColumn());
        personcol.setOnEditCommit((TableColumn.CellEditEvent<Arrangement, String> t) -> {
            ((Arrangement) t.getTableView()
                    .getItems()
                    .get(t.getTablePosition().getRow()))
                    .setKontakt_person_navn(t.getNewValue());
        });
        typecol.setCellFactory(TextFieldTableCell.forTableColumn());
        typecol.setOnEditCommit((TableColumn.CellEditEvent<Arrangement, String> t) -> {
            ((Arrangement) t.getTableView()
                    .getItems()
                    .get(t.getTablePosition().getRow()))
                    .setType(t.getNewValue());
        });
        pro.setCellFactory(TextFieldTableCell.forTableColumn());
        pro.setOnEditCommit((TableColumn.CellEditEvent<Arrangement, String> t) -> {
            ((Arrangement) t.getTableView()
                    .getItems()
                    .get(t.getTablePosition().getRow()))
                    .setProgram_info(t.getNewValue());
        });
        pris.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        pris.setOnEditCommit((TableColumn.CellEditEvent<Arrangement, Integer> t) -> {
            ((Arrangement) t.getTableView()
                    .getItems()
                    .get(t.getTablePosition().getRow()))
                    .setBillett_pris(t.getNewValue());
        });
        Arrang_view.setItems(liste);
        Arrang_view.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }
}

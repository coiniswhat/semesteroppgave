/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Filebehandler;

import Klasser_semesteroppgave.Arrangement;
import Klasser_semesteroppgave.Billett;
import Klasser_semesteroppgave.Kontakt_person;
import Klasser_semesteroppgave.Lokal;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author Anas
 */
public class Lese_file<T> extends Lese {

    @Override
    public String åpe_file() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Åpen  File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Csv", "*.csv"),
                new FileChooser.ExtensionFilter("Jobj", "*.jobj"));
        Stage stage = new Stage();
        File file = fileChooser.showOpenDialog(stage);
        String path = file.getPath();
        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            Logger.getLogger(Lagring.class.getName()).log(Level.SEVERE, null, ex);
        }
        return path;
    }

    @Override
    public void lese_file_csv(ObservableList list, String path) {

        try {

            try (BufferedReader br = new BufferedReader(new FileReader(path))) {
                String line;

                while ((line = br.readLine()) != null) {
                    String[] read = line.split(";");
                    int tall;
                    if (read.length == 3) {
                        tall = Integer.parseInt(read[2]);
                        Lokal f = new Lokal(read[0], read[1], tall);
                        list.add(f);
                    }
                    if (read.length == 9) {
                            int g = Integer.parseInt(read[7]);
                            Arrangement e = new Arrangement(read[0], read[1], read[2], read[3], read[4], read[5], read[6], g, read[8]);
                            list.add(e);
                         
                      
                    }
                    if (read.length == 6) {
                        int s = Integer.parseInt(read[4]);
                        Kontakt_person h = new Kontakt_person(read[0], read[1], read[2], read[3], s, read[5]);
                        list.add(h);
                    }
                    if (read.length == 10) {

                        try {
                            Date dato = new SimpleDateFormat("dd.MM.yyyy").parse(read[5]);
                            Date klokke = new SimpleDateFormat("HH:MM").parse(read[6]);
                            int plass = Integer.parseInt(read[0]);
                            int pr = Integer.parseInt(read[2]);
                            int tel = Integer.parseInt(read[3]);
                            Billett e = new Billett(plass, read[1], pr, tel, read[4], dato, klokke, read[7], read[8], read[9]);
                            list.add(e);
                        } catch (ParseException ex) {
                            Logger.getLogger(Lese_file.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                }
            }
        } catch (IOException ioe) {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("FEIL");
            alert.setHeaderText("Opps.. Det er et problem");
            alert.setContentText(ioe.getMessage());
            alert.show();
        }
    }

    @Override// read object 
    public ObservableList lese_file(String path) {

        try (FileInputStream fin = new FileInputStream(path);
                ObjectInputStream oin = new ObjectInputStream(fin)) {

            List<T> list = (List<T>) oin.readObject();

            return FXCollections.observableArrayList(list);
        } catch (IOException | ClassNotFoundException e) {
        }

        return FXCollections.emptyObservableList();

    }

}

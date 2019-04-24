/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Filebehandler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Lagring <T> extends lagring_til_file {

    @Override
    public String åpenfile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Lagre til File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Csv", "*.csv"),
                new FileChooser.ExtensionFilter("Jobj", "*.jobj"));
        Stage stage = new Stage();
        File file = fileChooser.showSaveDialog(stage);
        String path = file.getPath();

        return path;
        }



    // metode som lagrer data til en .csv   
    @Override
    public void lagre_csv(ObservableList liste, String filepath) {

        try {
            String data ="";
               for (int i = 0; i < liste.size(); i++) {
                  
                    data += liste.get(i).toString() + ";";
                }
            FileOutputStream fos = new FileOutputStream(filepath, true);

            OutputStreamWriter osw = new OutputStreamWriter(fos);

            BufferedWriter bw = new BufferedWriter(osw);

            bw.write(data);

            bw.close();

        } catch (IOException ioe) {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("FEIL");
            alert.setHeaderText("Opps.. FEIL");
            alert.setContentText(ioe.getMessage());
            alert.show();
        }
    }



        @Override
        public void lagre_jobj ( ObservableList list,  String path ) {
       
        try (
          FileOutputStream fos = new FileOutputStream(path);
          ObjectOutputStream out = new ObjectOutputStream(fos);) {
            out.writeObject(new ArrayList<>(list));
        } catch (IOException e) {
            e.printStackTrace(); 
        }
    }

}


  


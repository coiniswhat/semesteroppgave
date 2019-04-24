/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validert;

import javafx.scene.control.Alert;

/**
 *
 * @author Anas
 */
public class validation {
       void kontroll_input(String innInput) {
        double innData;
        try {
            innData = Double.parseDouble(innInput);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("FEIL");
            alert.setHeaderText("Opps.. Må skriv inn tall");
            alert.setContentText(e.getMessage());
            alert.show();
        }
    }
 
}

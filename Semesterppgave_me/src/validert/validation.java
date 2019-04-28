/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validert;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class validation {

    public static boolean textAlphabet(TextField iputTxt, Label inputlbl, String invalidTxt) {
        boolean Alphabet = true;
        

        if (!iputTxt.getText().matches("[a-z A-Z]+")) {
            Alphabet = false;


        }
        inputlbl.setText(invalidTxt);

        return Alphabet;

    }

    public static boolean textNumeric(TextField inputTxt, Label inputlbl, String validTxt) {
        boolean nummer = true;
        String valid = null;

        if (!inputTxt.getText().matches("[0-9]+")) {
            nummer = false;
            valid = validTxt;

        }
        inputlbl.setText(valid);
        return nummer;

    }
    public static boolean textDate(TextField inputTxt, Label inputlbl, String validTxt) {
        boolean nummer = true;
        String valid = null;

        if (!inputTxt.getText().matches("^[0-3]?[0-9]/[0-3]?[0-9]/(?:[0-9]{2})?[0-9]{2}$")) {
            nummer = false;
            valid = validTxt;

        }
        inputlbl.setText(valid);
        return nummer;

    }
    public static boolean textNettside(TextField inputTxt, Label inputlbl, String validTxt) {
        boolean nummer = true;
        String valid = null;

        if (!inputTxt.getText().matches("<\\b(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]>")) {
            nummer = false;
            valid = validTxt;

        }
        inputlbl.setText(valid);
        return nummer;

    }
    public static boolean textTime(TextField inputTxt, Label inputlbl, String validTxt) {
        boolean nummer = true;
        String valid = null;

        if (!inputTxt.getText().matches(" \"([01]?[0-9]|2[0-3]):[0-5][0-9]\"")) {
            nummer = false;
            valid = validTxt;

        }
        inputlbl.setText(valid);
        return nummer;

    }

    public static boolean emailFormat(TextField inputtxt, Label inputlbl, String validtxt) {
        boolean Email = true;
        String valid = null;

        if (!inputtxt.getText().matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.com")) {
            Email = false;
            valid = validtxt;

        }
        inputlbl.setText(valid);
        return Email;

    }    
}

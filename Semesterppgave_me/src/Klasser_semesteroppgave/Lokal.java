/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Klasser_semesteroppgave;

import java.io.Serializable;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Lokal implements Serializable {

    String Lokal_navn;
    String type;
    int Antall_plasser;
    public ObservableList<Lokal> liste;
    public Lokal(String Lokal_navn, String type, int Antall_plasser) {
        this.Lokal_navn = Lokal_navn;
        this.type = type;
        this.Antall_plasser = Antall_plasser;
    }

    public String getLokal_navn() {
        return Lokal_navn;
    }

    public void setLokal_navn(String Lokal_navn) {
        this.Lokal_navn = Lokal_navn;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAntall_plasser() {
        return Antall_plasser;
    }

    public void setAntall_plasser(int Antall_plasser) {
        this.Antall_plasser = Antall_plasser;
    }

    @Override
    public String toString() {
        return Lokal_navn + " ; " + type + " ; " + Antall_plasser + "\n";
    }

}

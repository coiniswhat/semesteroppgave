
package Klasser_semesteroppgave;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class Arrangement {
 private  String kontakt_person_navn;
 private String navn_på_arrangement;
 private  String type;
   private String Artister;
 private  String program_info;
private   String sted;
   private Dato tidspunkt;
    private int billett_pris;
  private String billett_salg;

    public String getNavn_på_arrangement() {
        return navn_på_arrangement;
    }

//    public Arrangement(String kontakt_person_navn, String type, String Artister, String program_info, String sted, Dato tidspunkt, int billett_pris, String billett_salg) {
//        this.kontakt_person_navn = kontakt_person_navn;
//        this.type = type;
//        this.Artister = Artister;
//        this.program_info = program_info;
//        this.sted = sted;
//        this.tidspunkt = tidspunkt;
//        this.billett_pris = billett_pris;
//        this.billett_salg = billett_salg;
//    }
    public void setNavn_på_arrangement(String navn_på_arrangement) {
        this.navn_på_arrangement = navn_på_arrangement;
    }

    public String getKontakt_person_navn() {
        return kontakt_person_navn;
    }

    public void setKontakt_person_navn(String kontakt_person_navn) {
        this.kontakt_person_navn = kontakt_person_navn;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getArtister() {
        return Artister;
    }

    public void setArtister(String Artister) {
        this.Artister = Artister;
    }

    public String getProgram_info() {
        return program_info;
    }

    public void setProgram_info(String program_info) {
        this.program_info = program_info;
    }

    public String getSted() {
        return sted;
    }

    public void setSted(String sted) {
        this.sted = sted;
    }

    public Dato getTidspunkt() {
        return tidspunkt;
    }

    public void setTidspunkt(Dato tidspunkt) {
        this.tidspunkt = tidspunkt;
    }

    public int getBillett_pris() {
        return billett_pris;
    }

    public void setBillett_pris(int billett_pris) {
        this.billett_pris = billett_pris;
    }

    public String getBillett_salg() {
        return billett_salg;
    }

    public void setBillett_salg(String billett_salg) {
        this.billett_salg = billett_salg;
    }
  
     
    
}


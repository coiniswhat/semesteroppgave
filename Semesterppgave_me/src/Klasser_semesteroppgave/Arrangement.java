package Klasser_semesteroppgave;

import java.io.Serializable;

public class Arrangement implements Serializable{

    public String kontakt_person_navn;
    public String navn_på_arrangement;
    public String type;
    public String Artister;
    public String program_info;
    public String sted;
    public String tidspunkt;
    public int billett_pris;
    private String billett_salg;

    public Arrangement(String kontakt_person_navn, String navn_på_arrangement, String tidspunkt, String type, String Artister, String program_info, String sted, int billett_pris, String billett_salg) {
        this.kontakt_person_navn = kontakt_person_navn;
        this.navn_på_arrangement = navn_på_arrangement;
        this.type = type;
        this.Artister = Artister;
        this.program_info = program_info;
        this.sted = sted;
        this.billett_pris = billett_pris;
        this.tidspunkt = tidspunkt;
        this.billett_salg = billett_salg;
    }

    public String getKontakt_person_navn() {
        return kontakt_person_navn;
    }

    public void setKontakt_person_navn(String kontakt_person_navn) {
        this.kontakt_person_navn = kontakt_person_navn;
    }

    public String getNavn_på_arrangement() {
        return navn_på_arrangement;
    }

    public void setNavn_på_arrangement(String navn_på_arrangement) {
        this.navn_på_arrangement = navn_på_arrangement;
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

    public String getTidspunkt() {
        return tidspunkt;
    }

    public void setTidspunkt(String tidspunkt) {
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

    @Override
    public String toString() {
        return kontakt_person_navn + ";" + navn_på_arrangement + ";" + type + ";" + Artister + ";" + program_info + ";" + sted + ";" + tidspunkt + ";" + billett_pris+";"+ billett_salg+"\n";
    }

}

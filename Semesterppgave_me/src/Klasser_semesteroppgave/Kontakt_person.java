/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Klasser_semesteroppgave;

import java.io.Serializable;

public class Kontakt_person implements Serializable {

    String nettside;
    String firma;
    String mer_opplysning;
    String navn;
    int telfonnummer;
    String epost;

    public Kontakt_person(String nettside, String firma, String mer_opplysning, String navn, int telfonnummer, String epost) {
        this.nettside = nettside;
        this.firma = firma;
        this.mer_opplysning = mer_opplysning;
        this.navn = navn;
        this.telfonnummer = telfonnummer;
        this.epost = epost;
    }

    public String getNettside() {
        return nettside;
    }

    public void setNettside(String nettside) {
        this.nettside = nettside;
    }

    public String getFirma() {
        return firma;
    }

    public void setFirma(String firma) {
        this.firma = firma;
    }

    public String getMer_opplysning() {
        return mer_opplysning;
    }

    public void setMer_opplysning(String mer_opplysning) {
        this.mer_opplysning = mer_opplysning;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public int getTelfonnummer() {
        return telfonnummer;
    }

    public void setTelfonnummer(int telfonnummer) {
        this.telfonnummer = telfonnummer;
    }

    public String getEpost() {
        return epost;
    }

    public void setEpost(String epost) {
        this.epost = epost;
    }

    @Override
    public String toString() {
        return nettside + ";" + firma + ";" + mer_opplysning + ";" + navn + ";" + telfonnummer + ";" + epost + '\n';
    }
    

}

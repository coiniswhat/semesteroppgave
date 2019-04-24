/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Klasser_semesteroppgave;

import java.util.Date;


public class Billett {
  int plass_nummer;
  String navn;
  int pris;
  int telefonnummer_til_kjper;
  String epost;
  Date dato;
  Date klokke;
  String lokal;
  String arrangement;
  String etternavn;

    public Billett(int plass_nummer, String navn, int pris, int telefonnummer_til_kjper, String epost, Date dato, Date klokke, String lokal, String arrangement,String etternavn) {
        this.plass_nummer = plass_nummer;
        this.navn = navn;
        this.pris = pris;
        this.telefonnummer_til_kjper = telefonnummer_til_kjper;
        this.epost = epost;
        this.dato = dato;
        this.klokke = klokke;
        this.lokal = lokal;
        this.arrangement = arrangement;
        this.etternavn=etternavn;
    }

    public String getEtternavn() {
        return etternavn;
    }

    public void setEtternavn(String etternavn) {
        this.etternavn = etternavn;
    }

    public int getPlass_nummer() {
        return plass_nummer;
    }

    public void setPlass_nummer(int plass_nummer) {
        this.plass_nummer = plass_nummer;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public int getPris() {
        return pris;
    }

    public void setPris(int pris) {
        this.pris = pris;
    }

    public int getTelefonnummer_til_kjper() {
        return telefonnummer_til_kjper;
    }

    public void setTelefonnummer_til_kjper(int telefonnummer_til_kjper) {
        this.telefonnummer_til_kjper = telefonnummer_til_kjper;
    }

    public String getEpost() {
        return epost;
    }

    public void setEpost(String epost) {
        this.epost = epost;
    }

    public Date getDato() {
        return dato;
    }

    public void setDato(Date dato) {
        this.dato = dato;
    }

    public Date getKlokke() {
        return klokke;
    }

    public void setKlokke(Date klokke) {
        this.klokke = klokke;
    }

    public String getLokal() {
        return lokal;
    }

    public void setLokal(String lokal) {
        this.lokal = lokal;
    }

    public String getArrangement() {
        return arrangement;
    }

    public void setArrangement(String arrangement) {
        this.arrangement = arrangement;
    }

    @Override
    public String toString() {
        return  plass_nummer + ";" + navn + ";" + pris + ";" + telefonnummer_til_kjper + ";" + epost + ", " + dato +";" + klokke + ";" + lokal + ";" + arrangement +";"+etternavn+ '\n';
    }
  
   
  
  
  
}

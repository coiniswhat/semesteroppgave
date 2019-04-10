/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Klasser_semesteroppgave;


public class Billett {
  int plass_nummer;
  String lokal_navn;
  int pris;
  int telefonnummer_til_kjper;

    public Billett(int plass_nummer, String lokal_navn, int pris, int telefonnummer_til_kjper) {
        this.plass_nummer = plass_nummer;
        this.lokal_navn = lokal_navn;
        this.pris = pris;
        this.telefonnummer_til_kjper = telefonnummer_til_kjper;
    }
  
  
}

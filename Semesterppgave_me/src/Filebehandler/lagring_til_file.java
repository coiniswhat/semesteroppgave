/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Filebehandler;

import java.io.File;
import javafx.collections.ObservableList;

public abstract class lagring_til_file  {

    abstract public void lagre_csv(ObservableList liste, String e);//TODO
    abstract public void lagre_jobj(ObservableList list, String f);//TODO
    abstract public String åpenfile();


}


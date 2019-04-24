/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Filebehandler;

import javafx.collections.ObservableList;

/**
 *
 * @author Anas
 */
abstract class Lese {
    abstract public ObservableList<Object> lese_file(String path);

    abstract public String åpe_file();
    abstract public void lese_file_csv(ObservableList list, String path);
}

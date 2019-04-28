/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Klasser_semesteroppgave;

class Dato {
    public int dag;
    public int måned;
    public int år;

    public Dato(int dag, int måned, int år) {
        this.dag = dag;
        this.måned = måned;
        this.år = år;
    }
    @Override
    public String toString() {
        return "Dato{" + "dag=" + dag + ", m\u00e5ned=" + måned + ", \u00e5r=" + år + '}';
    }
    
    
}
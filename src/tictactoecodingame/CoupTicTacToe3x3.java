/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoecodingame;


/**
 *
 * @author franck.tempet
 */
public class CoupTicTacToe3x3 extends Coup {
    private int colonne, ligne;
    private Jeton jeton;

    public CoupTicTacToe3x3( int _colonne , int _ligne , Jeton _jeton ) {
        super();
        colonne = _colonne;
        ligne = _ligne;       
        jeton = _jeton;
    }
    
    public int getColonne() {
        return colonne;
    }

    public int getLigne() {
        return ligne;
    }
    
    public Jeton getJeton() {
        return jeton;
    }

    public String toString() {
        return "(" + colonne + "," + ligne + ")" ;
    }

    @Override
    public boolean equals(Object obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int hashCode() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

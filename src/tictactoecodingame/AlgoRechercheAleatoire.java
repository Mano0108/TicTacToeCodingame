/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoecodingame;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author franck.tempet
 */
public class AlgoRechercheAleatoire extends AlgoRecherche{
    Random rnd;

    public AlgoRechercheAleatoire() {
        rnd = new Random();       
    }
    
    
    
    @Override
    public Coup meilleurCoup(Plateau _plateau, Joueur _joueur, boolean _ponder) {
        
        ArrayList<Coup> coups = _plateau.getListeCoups(_joueur);

        /// affichage des listes de coup disponibles, du dernier coup et du jeton présent sur chaque case du plateau

        /*System.out.println("Liste des coups disponibles : ");
        System.out.print(coups.toString());
        System.out.println(coups.size());

        CoupTicTacToe dernierCoup= (CoupTicTacToe) _plateau.getDernierCoup();
        if(dernierCoup==null){System.out.println("null");}
        else{int x = dernierCoup.getColonne();
        int y = dernierCoup.getLigne();
        System.out.println(" dernier coup : ");
        System.out.println("("+ x +","+ y+")");}

        System.out.println();*/

        /*for(int i = 0; i<3; i++){
            for(int j = 0; j<3; j++){
                Case c =  new Case(i,j);
                System.out.println(" coup " + i + " " + j +" = " );
                System.out.println(_plateau.getPiece(c));

            }
        }*/

        return coups.get(rnd.nextInt( coups.size()));
    }

   
    
}

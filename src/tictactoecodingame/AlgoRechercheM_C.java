package tictactoecodingame;

import java.util.ArrayList;
import java.util.Random;

public class AlgoRechercheM_C extends AlgoRecherche{

    int nbSimulation;
    double C;
    Joueur joueurAdverse;
    ArbreMC arbreM;
    Coup dernierCoup;

    public AlgoRechercheM_C(int nbsimulation, double c, Joueur jA){
        nbSimulation=nbsimulation;
        C=c;
        joueurAdverse=jA;
    }

    //On développe l'arbre tant que l'on ne dépasse pas les 100 ms pour codinGame
    public Coup meilleurCoup(Plateau _plateau, Joueur _joueur, boolean _ponder) {
        long t0 = System.currentTimeMillis();
        long t1 = System.currentTimeMillis();
        ArbreMC arbre = new ArbreMC(_plateau,_joueur,joueurAdverse,C);
        int compteur = 0;
        while(t1-t0<98 && compteur<nbSimulation){
            arbre.developper(1);
            t1=System.currentTimeMillis();
            compteur++;
        }
        Coup meilleurCoup = arbre.getMeilleurCoup();
        return(meilleurCoup);
    }
}

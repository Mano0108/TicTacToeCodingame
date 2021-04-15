package tictactoecodingame;

import java.util.ArrayList;
import java.util.Random;

public class AlgoRechercheM_C {

    int nbSimulation;
    double C;
    Joueur enemie;
    ArbreMC arbreM;
    Coup dernierCoup;

    public Coup meilleur_coup(Plateau plateau, Joueur currentJoueur, Joueur joueur1, Joueur joueur2, int nombreiteration){
        int compteurdepartiejouée = 0;
        int gain=0;
        ArrayList<Coup> coups = plateau.getListeCoups(currentJoueur);
        int [] tableaucoup = new int [coups.size()];
        while (compteurdepartiejouée<nombreiteration){
            tableaucoup[]+=JeuxAleatoireMC.partie_rd(plateau, currentJoueur, joueur1, joueur2);
        }
        return ;

    }
    public NoeudMC selection(NoeudMC noeud){
        ArrayList<NoeudMC> noeuds = NoeudMC.getNoeudsEnfants();
        if (noeuds.size() == 0){
            return noeud;
        }
        else{
            Random generator = new Random();
            int randomIndex = generator.nextInt(noeuds.size());
            NoeudMC nv_noeud = noeuds.get(randomIndex);
            return selection(nv_noeud);
        }

    }
    public static void expansion(NoeudMC noeud_ori, Joueur currentJoueur){
       ArrayList<Coup> coups = noeud_ori.plateau.getListeCoups(currentJoueur);
       Plateau plateau = noeud_ori.plateau;
       for(int k=0;k<coups.size();k++){
           NoeudMC nv_noeud = new NoeudMC();
           plateau.joueCoup((Coup) coups.get(k));
           nv_noeud.plateau = plateau;
           plateau.annuleDernierCoup();
           ArbreMC.ajouterEnfant(noeud_ori, nv_noeud);
       }
    }
    public static int simulation(NoeudMC noeud, Plateau plateau, Joueur currentJoueur, Joueur joueur1, Joueur joueur2){
        ArrayList<NoeudMC> noeuds_enfants = noeud.getNoeudsEnfants();
        Random generator = new Random();
        int randomIndex = generator.nextInt(noeuds_enfants.size());
        NoeudMC noeud_simu = noeuds_enfants.get(randomIndex);
        int res = JeuxAleatoireMC.partie_rd(noeud_simu.plateau, currentJoueur, joueur1, joueur2);
        return res;
    }
    public static void backpro(NoeudMC noeud, int res, ArbreMC arbre){
        while(noeud != arbre.getRacine()){
            noeud.nbSimulations++;
            noeud.nbVictoire+= res;
            noeud=noeud.parent;
        }
    }
}

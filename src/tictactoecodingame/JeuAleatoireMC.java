package tictactoecodingame;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;

public class AlgoRechercheMC {
    //une methode , avec un while (nombre d'iterations) pour lancer des simulations (4 etapes; selection, expansion, simulation, update) pour trouver le prochain Coup
    //une méthode pour chaque étape
    //
    int nombreiteration;
    int nbSimulations;
    ArbreMC arbre = new ArbreMC();
    NoeudMC noeudRacine = ArbreMC.getRacine();

    public Coup meilleur_coup(Plateau plateau, Joueur currentJoueur, Joueur joueur1, Joueur joueur2){
        return ;

    }
    public static NoeudMC selection(NoeudMC noeud){
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
        int res = JeuAleatoireMC.partie_rd(noeud_simu.plateau, currentJoueur, joueur1, joueur2);
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

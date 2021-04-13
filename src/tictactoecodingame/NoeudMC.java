package tictactoecodingame;

import java.util.List;
import java.util.ArrayList;

public class NoeudMC {
    NoeudMC parent; //renvoie le noeud précédent
    List<NoeudMC> noeudsEnfants; // renvoie la liste des noeuds enfants
    int nbSimulations  ; // nombre de partie lancer sous le noeud
    int nbVictoire; // nombre de victoire sur le noeud


    public List<NoeudMC> getNoeudsEnfants() {
        return noeudsEnfants;
    }

    public Noeuds() {
        this.Plateau = new Plateau();
        noeudsEnfants = new ArrayList<>();
    }
}



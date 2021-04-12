package tictactoecodingame;

public class AlgoRechercheMC {
    //une methode , avec un while (nombre d'iterations) pour lancer des simulations (4 etapes; selection, expansion, simulation, update) pour trouver le prochain Coup
    //une méthode pour chaque étape
    //
    int nombreiteration;
    int nbSimulations;
    ArbreMC arbre = new ArbreMC();
    NoeudMC noeudRacine = ArbreMC.getRacine();

    public Coup prochaincoup(Plateau plateau, Joueur joueur1) {
        while (nbSimulations < nombreiteration) {

        }

        //à finir
        return prochaincoup(plateau, joueur1);
    }
}

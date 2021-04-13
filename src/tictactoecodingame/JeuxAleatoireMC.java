package tictactoecodingame;
import java.util.Random;
import java.util.ArrayList;


public class JeuxAleatoireMC {

    public int partie_rd(Plateau plateau, Joueur currentJoueur, Joueur joueur1, Joueur joueur2) {
        while (!plateau.partieTerminee()) {
            ArrayList<Coup> coups = plateau.getListeCoups(currentJoueur);
            Random generator = new Random();
            int randomIndex = generator.nextInt(coups.size());
            Coup coup = coups.get(randomIndex);
            plateau.joueCoup(coup);
            if (plateau.vainqueur() == joueur1) {
                return 1;
            } else if (plateau.vainqueur() == joueur2) {
                return -1;
            } else {
                return 0;
            }


        }
        return 0;
    }
}

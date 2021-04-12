package tictactoecodingame;
import java.util.Random;

public class JeuAleatoireMC {
  public int partie_rd(Plateau plateau, Joueur joueur){
        while (!plateau.partieTerminee()){
            ArrayList<Coup> coups = plateau.getListeCoups(joueur);
            Random generator = new Random();
            int randomIndex = generator.nextInt(coups.length);
            Coup coup = coups.get(randomIndex);
            plateau.joueCoup(coup);
        }
        return plateau.vainqueur();

    }
}

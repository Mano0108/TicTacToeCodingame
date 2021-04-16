package tictactoecodingame;

/**
 *
 * @author franck
 */

    /*--------------------------------------------------------*/
    /*                 Version jeu en local                   */
    /*--------------------------------------------------------*/
public class Player {

    public static void main(String args[]) {
        
        JoueurOrdi joueurOrdi1 = new JoueurOrdi("aléa");
        JoueurOrdi joueurOrdi2 = new JoueurOrdi("minimax");
       
        
        // Remplacer ici l'algorithme aléatoire par votre algorithme. 
        // Créer une nouvelle classe qui hérite de la class AlgoRecherche
        AlgoMinimax minimax  = new AlgoMinimax();
        AlgoRechercheAleatoire alea = new AlgoRechercheAleatoire();// L'ordinateur joue au hasard
        joueurOrdi2.setAlgoRecherche(minimax);
        joueurOrdi1.setAlgoRecherche(alea);
        GrilleTicTacToe9x9 grille = new GrilleTicTacToe9x9();
         
        Arbitre a = new Arbitre(grille, joueurOrdi1 , joueurOrdi2 );
       
        //a.startNewGame(true);    // Demarre une partie en affichant la grille du jeu
       
       // Pour lancer un tournooi de 100 parties en affichant la grille du jeu
        a.startTournament(1000 , false);
        
    }
}

    /*--------------------------------------------------------*/
    /*                 Version Codin game                     */
    /*--------------------------------------------------------*/

    /*
    import java.util.Scanner;

essaie github nouveau text
test2

    class Player {

       public static void main(String args[]) {

            Scanner in = new Scanner(System.in);

            CoupTicTacToe3x3 coup;
            JoueurHumain adversaire = new JoueurHumain("Adversaire");
            JoueurOrdi joueurOrdi = new JoueurOrdi("Ordi");

            AlgoRechercheAleatoire alea  = new AlgoRechercheAleatoire( );   // L'ordinateur joue au hasard
            joueurOrdi.setAlgoRecherche(alea);

            GrilleTicTacToe3x3 grille = new GrilleTicTacToe3x3();
            grille.init();


            while (true) {
                int opponentRow = in.nextInt();
                int opponentCol = in.nextInt();
                int validActionCount = in.nextInt();
                for (int i = 0; i < validActionCount; i++) {
                    int row = in.nextInt();
                    int col = in.nextInt();
                }
                if ( opponentCol != -1  ) {
                    coup = new CoupTicTacToe3x3(opponentCol, opponentRow, new Jeton(adversaire));
                    grille.joueCoup(coup);
                }

                coup = (CoupTicTacToe3x3) joueurOrdi.joue(grille);
                grille.joueCoup(coup);
                System.out.println(coup.getLigne() + " " + coup.getColonne() ); 
                System.out.flush();
            }
       }
    
}
*/
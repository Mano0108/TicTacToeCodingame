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
        
        JoueurOrdi joueurOrdi2 = new JoueurOrdi("Ordi1");
        JoueurOrdi joueurOrdi1= new JoueurOrdi("Ordi2");
        JoueurOrdi joueurOrdi3= new JoueurOrdi("Ordi3");
       
        
        // Remplacer ici l'algorithme aléatoire par votre algorithme. 
        // Créer une nouvelle classe qui hérite de la class AlgoRecherche
        //AlgoMinimax minimax  = new AlgoMinimax();   // L'ordinateur joue au hasard
        //joueurOrdi2.setAlgoRecherche(minimax);
        //joueurOrdi2.setAlgoRecherche(minimax);



        AlgoRecherche Aleatoire = new AlgoRechercheAleatoire();
        AlgoRechercheM_C MonteCarlo2 = new AlgoRechercheM_C(6000, 1.35, joueurOrdi2);
        AlgoMinimax Minmax = new AlgoMinimax();
        joueurOrdi1.setAlgoRecherche(Aleatoire);
        joueurOrdi3.setAlgoRecherche(Minmax);
        joueurOrdi2.setAlgoRecherche(MonteCarlo2);


        GrilleTicTacToe3x3 grille = new GrilleTicTacToe3x3();
         
        Arbitre a = new Arbitre(grille, joueurOrdi1 , joueurOrdi2 );
       
        a.startTournament(100, false);    // Demarre une partie en affichant la grille du jeu
       
       // Pour lancer un tournooi de 100 parties en affichant la grille du jeu
        //a.startTournament(1000 , false);
        
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
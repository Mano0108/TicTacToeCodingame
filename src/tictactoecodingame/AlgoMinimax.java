package tictactoecodingame;

import java.util.ArrayList;
import java.util.Random;


public class AlgoMinimax extends AlgoRecherche {


    public AlgoMinimax(){}


    public int evaluation3x3(Plateau _plateau, Joueur _joueur){

        if(_plateau.partieTerminee()){
            if(_plateau.partieNulle()){return 0;}
            if(_plateau.vainqueur()==_joueur){return 10;}
            else {return -10;}

        }

        return 0;
    }


    //teste pour une grille 9x9 si le joueur a gagné la case 3x3 de milieu (milieuI, milieuJ)
    public Boolean gagne3x3(int milieuI, int milieuJ, Joueur _joueur, Plateau _plateau){

        //teste si il a gagné une colone
        for (int c = milieuI - 1; c<milieuI + 2; c++){

            int compt = 0;
            for (int l = milieuJ - 1; l<milieuI + 2; l++){
                Case x = new Case(c,l);
                if(_plateau.getPiece(x)!=null){
                if(_plateau.getPiece(x).getJoueur()==_joueur){
                    compt+=1;
                }}
            }
            if (compt==3){return true;}
        }

        //teste si il a gagné une ligne
        for (int l = milieuJ - 1; l<milieuJ + 2; l++){

            int compt = 0;
            for (int c = milieuI - 1; c<milieuI + 2; c++){
                Case x = new Case(c,l);
                if(_plateau.getPiece(x)!=null){
                if(_plateau.getPiece(x).getJoueur()==_joueur){
                    compt+=1;
                }}
            }
            if (compt==3){return true;}
        }

        //teste si il a gagné la première diagonale
        int comptdiag1 = 0;
        for (int i = 0; i<3; i++){

            Case x = new Case(milieuI-1+i,milieuJ-1+i);
            if(_plateau.getPiece(x)!=null){
            if(_plateau.getPiece(x).getJoueur()==_joueur){
                comptdiag1+=1;
            }}
        }
        if (comptdiag1==3){return true;}

        //teste si il a gagné la deuxième diagonale
        int comptdiag2 = 0;
        for (int i = 0; i<3; i++){

            Case x = new Case(milieuI-1+i,milieuJ+1-i);
            if(_plateau.getPiece(x)!=null){
            if(_plateau.getPiece(x).getJoueur()==_joueur){
                comptdiag1+=1;
            }}
        }
        if (comptdiag2==3){return true;}

        return false;

    }

    //teste pour une grille 9x9 si le joueur a presque gagné la case 3x3 de milieu (milieuI, milieuJ) (c'est à dire si il
    // a deux jetons alignés et que la troisième case est vide.
    public Boolean presquegagne3x3(int milieuI, int milieuJ, Joueur _joueur, Plateau _plateau){

        //teste si il a presque gagné une colone
        for (int c = milieuI - 1; c<milieuI + 2; c++){

            int compt = 0;
            for (int l = milieuJ - 1; l<milieuI + 2; l++){
                Case x = new Case(c,l);
                if(_plateau.getPiece(x)!=null){
                    if(_plateau.getPiece(x).getJoueur()==_joueur){
                        compt+=1;
                    }
                    if(_plateau.getPiece(x).getJoueur()!=_joueur){compt = -100;}}
            }
            if (compt==2){return true;}
        }

        //teste si il a presque gagné une ligne
        for (int l = milieuJ - 1; l<milieuJ + 2; l++){

            int compt = 0;
            for (int c = milieuI - 1; c<milieuI + 2; c++){
                Case x = new Case(c,l);
                if(_plateau.getPiece(x)!=null){
                    if(_plateau.getPiece(x).getJoueur()==_joueur){
                        compt+=1;
                    }
                    if(_plateau.getPiece(x).getJoueur()!=_joueur){compt = -100;}}
            }
            if (compt==2){return true;}
        }

        //teste si il a presque gagné la première diagonale
        int comptdiag1 = 0;
        for (int i = 0; i<3; i++){

            Case x = new Case(milieuI-1+i,milieuJ-1+i);
            if(_plateau.getPiece(x)!=null){
                if(_plateau.getPiece(x).getJoueur()==_joueur){
                    comptdiag1+=1;
                }
                if(_plateau.getPiece(x).getJoueur()!=_joueur){comptdiag1 = -100;}}
        }
        if (comptdiag1==2){return true;}

        //teste si il a presque gagné la deuxième diagonale
        int comptdiag2 = 0;
        for (int i = 0; i<3; i++){

            Case x = new Case(milieuI-1+i,milieuJ+1-i);
            if(_plateau.getPiece(x)!=null){
                if(_plateau.getPiece(x).getJoueur()==_joueur){
                    comptdiag2+=1;
                }
                if(_plateau.getPiece(x).getJoueur()!=_joueur){comptdiag2 = -100;}}
        }
        if (comptdiag2==2){return true;}

        return false;

    }

    //teste pour une grille 9x9 si le joueur a presque perdu la case 3x3 de milieu (milieuI, milieuJ) (c'est à dire si le joueur adverse
    // a deux jetons alignés et que la troisième case est vide.
    public Boolean presqueperdu3x3(int milieuI, int milieuJ, Joueur _joueur, Plateau _plateau){

        //teste si il a presque perdu une colone
        for (int c = milieuI - 1; c<milieuI + 2; c++){

            int compt = 0;
            for (int l = milieuJ - 1; l<milieuI + 2; l++){
                Case x = new Case(c,l);
                if(_plateau.getPiece(x)!=null){
                    if(_plateau.getPiece(x).getJoueur()!=_joueur){
                        compt+=1;
                    }
                    if(_plateau.getPiece(x).getJoueur()==_joueur){compt = -100;}}
            }
            if (compt==2){return true;}
        }

        //teste si il a presque perdu une ligne
        for (int l = milieuJ - 1; l<milieuJ + 2; l++){

            int compt = 0;
            for (int c = milieuI - 1; c<milieuI + 2; c++){
                Case x = new Case(c,l);
                if(_plateau.getPiece(x)!=null){
                    if(_plateau.getPiece(x).getJoueur()!=_joueur){
                        compt+=1;
                    }
                    if(_plateau.getPiece(x).getJoueur()==_joueur){compt = -100;}}
            }
            if (compt==2){return true;}
        }

        //teste si il a presque perdu la première diagonale
        int comptdiag1 = 0;
        for (int i = 0; i<3; i++){

            Case x = new Case(milieuI-1+i,milieuJ-1+i);
            if(_plateau.getPiece(x)!=null){
                if(_plateau.getPiece(x).getJoueur()!=_joueur){
                    comptdiag1+=1;
                }
                if(_plateau.getPiece(x).getJoueur()==_joueur){comptdiag1 = -100;}}
        }
        if (comptdiag1==2){return true;}

        //teste si il a presque perdu la deuxième diagonale
        int comptdiag2 = 0;
        for (int i = 0; i<3; i++){

            Case x = new Case(milieuI-1+i,milieuJ+1-i);
            if(_plateau.getPiece(x)!=null){
                if(_plateau.getPiece(x).getJoueur()!=_joueur){
                    comptdiag2+=1;
                }
                if(_plateau.getPiece(x).getJoueur()==_joueur){comptdiag2 = -100;}}
        }
        if (comptdiag2==2){return true;}

        return false;

    }

    //teste pour une grille 9x9 si le joueur a perdu la case 3x3 de milieu (milieuI, milieuJ)
    public Boolean perd3x3(int milieuI, int milieuJ, Joueur _joueur, Plateau _plateau){

        //teste si il a perdu une colone
        for (int c = milieuI - 1; c<milieuI + 2; c++){

            int compt = 0;
            for (int l = milieuJ - 1; l<milieuI + 2; l++){
                Case x = new Case(c,l);
                if(_plateau.getPiece(x)!=null){
                    if(_plateau.getPiece(x).getJoueur()!=_joueur){
                        compt+=1;
                    }}
            }
            if (compt==3){return true;}
        }

        //teste si il a perdu une ligne
        for (int l = milieuJ - 1; l<milieuJ + 2; l++){

            int compt = 0;
            for (int c = milieuI - 1; c<milieuI + 2; c++){
                Case x = new Case(c,l);
                if(_plateau.getPiece(x)!=null){
                    if(_plateau.getPiece(x).getJoueur()!=_joueur){
                        compt+=1;
                    }}
            }
            if (compt==3){return true;}
        }

        //teste si il a perdu la première diagonale
        int comptdiag1 = 0;
        for (int i = 0; i<3; i++){

            Case x = new Case(milieuI-1+i,milieuJ-1+i);
            if(_plateau.getPiece(x)!=null){
                if(_plateau.getPiece(x).getJoueur()!=_joueur){
                    comptdiag1+=1;
                }}
        }
        if (comptdiag1==3){return true;}

        //teste si il a perdu la deuxième diagonale
        int comptdiag2 = 0;
        for (int i = 0; i<3; i++){

            Case x = new Case(milieuI-1+i,milieuJ+1-i);
            if(_plateau.getPiece(x)!=null){
                if(_plateau.getPiece(x).getJoueur()!=_joueur){
                    comptdiag1+=1;
                }}
        }
        if (comptdiag2==3){return true;}

        return false;

    }


//on attribue à une sous grille 3x3 gagnée un score de 50, et pour les sous grilles 3x3 non gagnée, on attribue un score
    //de 5 pour les cases du milieu, 3 pour les coins et 2 pour les côtés. Si jouer une case au milieu permet au joueur adverse de remposter la sous
    //grille 3x3 centrale au tour suivant, on diminue le score de ce coup.
    public int evaluation9x9(Plateau _plateau, Joueur joueurMax) {

        //si la partie est terminée
        if(_plateau.partieTerminee()){
            if (_plateau.partieNulle()) {
                return 0;
            }
            if (_plateau.vainqueur() == joueurMax) {
            return 1000;
            }
            else{return -1000;}
        }

        int score = 0;


        for (int i = 1; i < 9; i = i + 3) {
            for (int j = 1; j < 9; j = j + 3) {

                if (gagne3x3(i, j, joueurMax, _plateau)) {score+=50;}
                if (perd3x3(i, j, joueurMax, _plateau)) {score-=50;}

                Case a = new Case(i,j);
                if(_plateau.getPiece(a)!=null){
                    if(_plateau.getPiece(a).getJoueur()==joueurMax){
                        if(presqueperdu3x3(4,4,joueurMax,_plateau)){
                        score-=10;}
                        else{score+=5;}
                    }
                    if(_plateau.getPiece(a).getJoueur()!=joueurMax){
                        if(presquegagne3x3(4,4,joueurMax,_plateau)){
                            score+=10;}
                        else{score-=5;}
                    }
                }


                Case b = new Case(i-1,j-1);
                if(_plateau.getPiece(b)!=null){
                    if(_plateau.getPiece(b).getJoueur()==joueurMax){score+=3;}
                    if(_plateau.getPiece(b).getJoueur()!=joueurMax){score-=3;}
                }

                Case c = new Case(i-1,j+1);
                if(_plateau.getPiece(c)!=null){
                    if(_plateau.getPiece(c).getJoueur()==joueurMax){score+=3;}
                    if(_plateau.getPiece(c).getJoueur()!=joueurMax){score-=3;}
                }

                Case d = new Case(i+1,j+1);
                if(_plateau.getPiece(d)!=null){
                    if(_plateau.getPiece(d).getJoueur()==joueurMax){score+=3;}
                    if(_plateau.getPiece(d).getJoueur()!=joueurMax){score-=3;}
                }

                Case e = new Case(i+1,j-1);
                if(_plateau.getPiece(e)!=null){
                    if(_plateau.getPiece(e).getJoueur()==joueurMax){score+=3;}
                    if(_plateau.getPiece(e).getJoueur()!=joueurMax){score-=3;}
                }

                Case f = new Case(i,j-1);
                if(_plateau.getPiece(f)!=null){
                    if(_plateau.getPiece(f).getJoueur()==joueurMax){score+=2;}
                    if(_plateau.getPiece(f).getJoueur()!=joueurMax){score-=2;}
                }

                Case g = new Case(i,j+1);
                if(_plateau.getPiece(g)!=null){
                    if(_plateau.getPiece(g).getJoueur()==joueurMax){score+=2;}
                    if(_plateau.getPiece(g).getJoueur()!=joueurMax){score-=2;}
                }

                Case h = new Case(i-1,j);
                if(_plateau.getPiece(h)!=null){
                    if(_plateau.getPiece(h).getJoueur()==joueurMax){score+=2;}
                    if(_plateau.getPiece(h).getJoueur()!=joueurMax){score-=2;}
                }

                Case k = new Case(i+1,j);
                if(_plateau.getPiece(k)!=null){
                    if(_plateau.getPiece(k).getJoueur()==joueurMax){score+=2;}
                    if(_plateau.getPiece(k).getJoueur()!=joueurMax){score-=2;}
                }


            }
        }

        return score;


    }




    public int minimax(Plateau _plateau, Joueur _joueur, int depth, Boolean isMax, int alpha, int beta) {

        int score = evaluation9x9(_plateau, _joueur);

        if (score == 1000) return  score ;
        if (score == -1000) return score ;
        if (depth==0) return score;
        if (_plateau.partieNulle()) return 0;

        if (isMax) {
            //System.out.println("isMax");
            int best = -10000;
            ArrayList<Coup> coups = _plateau.getListeCoups(_joueur);
            for (int i = 0; i < coups.size(); i++) {

                _plateau.joueCoup(coups.get(i));
                best = Math.max(best, minimax(_plateau,_joueur, depth - 1, !isMax, alpha, beta));
                _plateau.annuleDernierCoup();
                alpha = Math.max(alpha, best);
                if(alpha>=beta){return best;}
                //System.out.println("best"+best);
            }

            return best;
        }

        else {
            //System.out.println("isMin");
            int best = 10000;
            ArrayList<Coup> coups = _plateau.getListeCoups(_joueur);
            for (int i = 0; i < coups.size(); i++) {

                _plateau.joueCoup(coups.get(i));
                best = Math.min(best, minimax(_plateau, _joueur, depth - 1, !isMax, alpha, beta));
                //System.out.println("best"+best);
                _plateau.annuleDernierCoup();
                beta = Math.min(beta, best);
                if(alpha>=beta){return best;}
            }

            return best;
        }
    }

    @Override
    public Coup meilleurCoup(Plateau _plateau, Joueur _joueur, boolean _ponder) {

        ArrayList<Coup> coups = _plateau.getListeCoups(_joueur);
        int bestVal = -1000;
        //System.out.println(bestVal);
        Coup bestMove = coups.get(0);

        for (int i = 0; i <coups.size(); i++) {

            _plateau.joueCoup(coups.get(i));
            int moveVal = minimax(_plateau, _joueur, 3, false, -10000, 10000);
            //System.out.println("moveVal"+moveVal);
            _plateau.annuleDernierCoup();


            if (moveVal > bestVal) {
                        bestMove = coups.get(i);
                        bestVal = moveVal;
            }

        }

        return bestMove;
    }

}

package tictactoecodingame;

import java.util.ArrayList;
import java.util.Random;


public class AlgoMinimax extends AlgoRecherche {


    public AlgoMinimax(){}

    public int nombrejetonligne(Plateau _plateau, int ligne,Joueur _joueurpositif){

        int nombre = 0;

        for(int i = 0; i<3; i++){
            Case c = new Case(i, ligne);
            if(_plateau.getPiece(c)!=null){
                if((_plateau.getPiece(c).getJoueur()==_joueurpositif) && (nombre>=0)){nombre+=1;}
                if((_plateau.getPiece(c).getJoueur()==_joueurpositif) && (nombre<0)){return 0;}
                if((_plateau.getPiece(c).getJoueur()!=_joueurpositif) && (nombre<=0)){nombre-=1;}
                if((_plateau.getPiece(c).getJoueur()!=_joueurpositif) && (nombre>0)){return 0;}
            }

        }
        return nombre;
    }
    public int nombrejetoncolone(Plateau _plateau, int colone,Joueur _joueurpositif){

        int nombre = 0;

        for(int i = 0; i<3; i++){
            Case c = new Case(colone, i);
            if(_plateau.getPiece(c)!=null){
                if((_plateau.getPiece(c).getJoueur()==_joueurpositif) && (nombre>=0)){nombre+=1;}
                if((_plateau.getPiece(c).getJoueur()==_joueurpositif) && (nombre<0)){return 0;}
                if((_plateau.getPiece(c).getJoueur()!=_joueurpositif) && (nombre<=0)){nombre-=1;}
                if((_plateau.getPiece(c).getJoueur()!=_joueurpositif) && (nombre>0)){return 0;}
            }

        }
        return nombre;


    }
    public int nombrejetondiag1(Plateau _plateau,Joueur _joueurpositif){

        int nombre = 0;

        for(int i = 0; i<3; i++){
            Case c = new Case(i, i);
            if(_plateau.getPiece(c)!=null){
                if((_plateau.getPiece(c).getJoueur()==_joueurpositif) && (nombre>=0)){nombre+=1;}
                if((_plateau.getPiece(c).getJoueur()==_joueurpositif) && (nombre<0)){return 0;}
                if((_plateau.getPiece(c).getJoueur()!=_joueurpositif) && (nombre<=0)){nombre-=1;}
                if((_plateau.getPiece(c).getJoueur()!=_joueurpositif) && (nombre>0)){return 0;}
            }

        }
        return nombre;
    }
    public int nombrejetondiag2(Plateau _plateau,Joueur _joueurpositif){

        int nombre = 0;

        for(int i = 0; i<3; i++){
            Case c = new Case(i, 2-i);
            if(_plateau.getPiece(c)!=null){
                if((_plateau.getPiece(c).getJoueur()==_joueurpositif) && (nombre>=0)){nombre+=1;}
                if((_plateau.getPiece(c).getJoueur()==_joueurpositif) && (nombre<0)){return 0;}
                if((_plateau.getPiece(c).getJoueur()!=_joueurpositif) && (nombre<=0)){nombre-=1;}
                if((_plateau.getPiece(c).getJoueur()!=_joueurpositif) && (nombre>0)){return 0;}
            }

        }
        return nombre;
    }



    public int evaluation2(Plateau _plateau, Joueur _joueurpositif){

        int eval = 0;

        for(int i = 0;i<2;i++){
            int ligne = i;
            if(nombrejetonligne(_plateau,i,_joueurpositif)==3){return 10;}
            if(nombrejetonligne(_plateau,i,_joueurpositif)==-3){return -10;}
            eval+=nombrejetonligne(_plateau,i,_joueurpositif);
        }

        for(int i = 0;i<2;i++){
            int colone = i;
            if(nombrejetoncolone(_plateau,i,_joueurpositif)==3){return 10;}
            if(nombrejetoncolone(_plateau,i,_joueurpositif)==-3){return -10;}
            eval+=nombrejetoncolone(_plateau,i,_joueurpositif);
        }

        if(nombrejetondiag1(_plateau,_joueurpositif)==3){return 10;}
        if(nombrejetondiag1(_plateau,_joueurpositif)==-3){return -10;}
        eval+=nombrejetondiag1(_plateau,_joueurpositif);

        if(nombrejetondiag2(_plateau,_joueurpositif)==3){return 10;}
        if(nombrejetondiag2(_plateau,_joueurpositif)==-3){return -10;}
        eval+=nombrejetondiag2(_plateau,_joueurpositif);

        return eval;

    }

    public int evaluation1(Plateau _plateau, Joueur _joueur){

        if(_plateau.partieTerminee()){
            if(_plateau.partieNulle()){return 0;}
            if(_plateau.vainqueur()==_joueur){return 10;}
            else {return -10;}

        }

        return 0;
    }




    public int minimax(Plateau _plateau, Joueur _joueur, int depth, Boolean isMax) {

        int score = evaluation2(_plateau, _joueur);

        if (score == 10) return  score - depth;
        if (score == -10) return score + depth;
        if (_plateau.partieTerminee()) return 0;

        if (isMax) {
            int best = -1000;
            ArrayList<Coup> coups = _plateau.getListeCoups(_joueur);
            for (int i = 0; i < coups.size(); i++) {

                _plateau.joueCoup(coups.get(i));
                best = Math.max(best, minimax(_plateau,_joueur, depth + 1, !isMax));
                _plateau.annuleDernierCoup();
            }

            return best;
        }

        else {
            int best = 1000;
            ArrayList<Coup> coups = _plateau.getListeCoups(_joueur);
            for (int i = 0; i < coups.size(); i++) {

                _plateau.joueCoup(coups.get(i));
                best = Math.min(best, minimax(_plateau, _joueur, depth + 1, !isMax));
                _plateau.annuleDernierCoup();
            }

            return best;
        }
    }

    @Override
    public Coup meilleurCoup(Plateau _plateau, Joueur _joueur, boolean _ponder) {

        ArrayList<Coup> coups = _plateau.getListeCoups(_joueur);
        int bestVal = -1000;
        Coup bestMove = coups.get(0);

        for (int i = 0; i <coups.size(); i++) {

            _plateau.joueCoup(coups.get(i));
            int moveVal = minimax(_plateau, _joueur, 0, false);
            _plateau.annuleDernierCoup();


            if (moveVal > bestVal) {
                        bestMove = coups.get(i);
                        bestVal = moveVal;
            }

        }

        return bestMove;
    }

}

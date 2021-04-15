package tictactoecodingame;

import java.util.ArrayList;
import java.util.Random;

public class ArbreMC {
    //Les paramètres propres à chaque noeud de l'arbre:
    // -le coup joué pour arriver au noeud
    // -le tour de jeu correspondant, true si le coup joué est celui de l'IA, false sinon
    // -la liste des sous-arbres du noeud
    // -le nombre de simulation et de victoire
    // -le pere du noeud (nul si c'est la racine)
    // -une liste des coups non visités

    private Coup coup;
    private ArrayList<ArbreMC> sousArbres;
    private boolean estMonTour;
    private int nbSimul;
    private int nbVictoire;
    private ArbreMC pere;
    private ArrayList<Coup> coupNonVisites;


    //Les paramètres communs à tous les noeuds de l'arbre (rentré dans le constructeur de la racine):
    // -Le plateau au tour de l'IA, qui sera manipulé de telle sorte à ce qu'il ne soit pas modifié après construction de l'arbre
    // -Les 2 joueurs, pour que les jetons soient différenciable par la fonction d'évaluation
    // -La constante d'exploration C de la fonction de sélection.


    private static Plateau plateau;
    private static Joueur monJoueur;
    private static Joueur joueurAdverse;
    private static double C;


    //On définit les constantes VICTOIRE, DEFAITE et NUL pour une meilleure compréhension du reour de simulation
    private static final int VICTOIRE = 0;
    private static final int DEFAITE = 1;
    private static final int NUL = 2;




    public ArbreMC(Plateau p,Joueur mJ,Joueur jA,double c){
        nbSimul = 0;
        nbVictoire = 0;
        estMonTour = true;
        plateau = p;
        monJoueur = mJ;
        joueurAdverse = jA;
        coupNonVisites = plateau.getListeCoups(monJoueur);
        C = c;
        sousArbres = new ArrayList();
    }

    public ArbreMC(Coup c,boolean monTour, ArbreMC papa){
        coup = c;
        estMonTour = monTour;
        coupNonVisites = plateau.getListeCoups(getJoueur(monTour));
        pere = papa;
        sousArbres = new ArrayList();
    }

    public Coup getCoup(){
        return(coup);
    }

    public ArbreMC getSousArbre(int position){
        return(sousArbres.get(position));
    }

    public Joueur getJoueur(){
        if(estMonTour)
            return(monJoueur);
        else{
            return(joueurAdverse);
        }
    }

    public Joueur getJoueur(boolean estMoi){
        if(estMoi)
            return(monJoueur);
        else{
            return(joueurAdverse);
        }
    }

    public int getNbSimulation(){
        return(nbSimul);
    }

    public int getNbVictoire(){
        return(nbVictoire);
    }

    public Plateau getPlateau(){
        return(plateau);
    }

    public void setPere(ArbreMC papa){
        pere = papa;
    }

    public void incrementerNbSimulation(){
        nbSimul++;
    }

    public void incrementerNbVictoire(){
        nbVictoire++;
    }

    public void incrementerNbDefaite(){
        nbVictoire--;
    }

    public ArbreMC selection(){ //On descend de l'arbre grace à la fonction de sélection en choisissant la feuille ayant une fonction d'évaluation maximale
        int Sp = nbSimul;
        int Si;
        int Vi;
        double noteSelection;
        double max;
        int positionMax = 0;
        if(coupNonVisites.isEmpty() && !plateau.partieTerminee()){ //Si tous les coups sont explorés au moins une fois et que la partie n'est pas terminée
            Si = getSousArbre(0).getNbSimulation();
            Vi = getSousArbre(0).getNbVictoire();
            max = Vi/Si + C*Math.sqrt(Math.log(Sp)/Si);
            for(int i = 0;i<sousArbres.size();i++){                //On parcourt les sous arbres et on retient la position où le maximum de la fonction de sélection a été atteint
                Si = getSousArbre(i).getNbSimulation();
                Vi = getSousArbre(i).getNbVictoire();
                noteSelection = Vi/Si + C*Math.sqrt(Math.log(Sp)/Si);
                if(max<noteSelection){
                    max = noteSelection;
                    positionMax = i;
                }
            }
            Coup coupSelectionne = getSousArbre(positionMax).getCoup();
            plateau.joueCoup(coupSelectionne);
            return(getSousArbre(positionMax).selection());
        }
        else{
            return(this); //Si il n'y a pas de sous-arbres, on renvoie simplement le noeud qui est donc une feuille (selection est sans effet)
        }
    }

    public ArbreMC expansion(){
        if(!plateau.partieTerminee()){ //On n'agrandit pas l'arbre si la feuille correspond à une situation finale du plateau
            Random rand = new Random();
            int positionAlea = rand.nextInt(coupNonVisites.size());
            Coup coupAlea = coupNonVisites.get(positionAlea);//On choisit un coup au hazard parmi les coups non visités
            coupNonVisites.remove(coupAlea);
            plateau.joueCoup(coupAlea); //On modifie le plateau en conséquence
            ArbreMC nouvelArbre = new ArbreMC(coupAlea,!estMonTour,this);
            sousArbres.add(nouvelArbre); //On agrandit l'arbre correspondant au coup tiré
            return(nouvelArbre);
        }
        return(this); //Si la partie est terminée, on renvoie simplement le noeud qui est donc une feuille (selection est sans effet)
    }

    public int simulation(){

        Joueur joueur;
        Random rand = new Random();
        boolean aMoiDeJouer = estMonTour;
        //Une partie est jouée aléatoirement à partir du plateau correspondant à la feuille choisie
        //On renvoie le résultat au terme de la partie
        while(!plateau.partieTerminee()){
            if(aMoiDeJouer)
                joueur=monJoueur;
            else{
                joueur=joueurAdverse;
            }
            ArrayList<Coup> listeCoups = plateau.getListeCoups(joueur);
            int positionAlea = rand.nextInt(listeCoups.size());
            Coup coupAlea = listeCoups.get(positionAlea);
            plateau.joueCoup(coupAlea);
            //Les joueurs jouent chacun son tour
            aMoiDeJouer = !aMoiDeJouer;
        }

        Joueur vainqueur = plateau.vainqueur();
        int resultat;
        if(vainqueur==monJoueur)
            resultat=VICTOIRE;
        else{
            if(vainqueur==joueurAdverse)
                resultat=DEFAITE;
            else{
                resultat=NUL;
            }
        }
        return(resultat);
    }

    public void backPropagation(int resultat){
        //On modifie les nombre de victoire et de simulation en remontant jusqu'à la racine, grâce au père

        //On incremente le nombre de victoire si c'est un noeud du joueur adverse et que l'on a gagné
        if(!estMonTour&&resultat==VICTOIRE)
            incrementerNbVictoire();
        if(estMonTour&&resultat==VICTOIRE)
            incrementerNbVictoire();
        if(!estMonTour&&resultat==DEFAITE)
            incrementerNbDefaite();
        //On incremente le nombre de victoire si c'est notre noeud et que l'on a perdu
        if(estMonTour&&resultat==DEFAITE)
            incrementerNbDefaite();
        //Dans tous les cas, on augmente le nombre de simulation
        incrementerNbSimulation();
        //On s'arrete lorsque l'on atteint la racine de l'arbre
        if(pere!=null)
            //Sinon on applique la méthode récursivement au père pour remonter
            pere.backPropagation(resultat);
    }


    //On répète les 4 étapes un nombre de fois précisé pour agrandir l'arbre
    public void developper(int nbDeveloppement){
        if(nbDeveloppement!=0){
            plateau.sauvegardePosition(0);
            ArbreMC nouvelleFeuille = selection().expansion();
            nouvelleFeuille.backPropagation(nouvelleFeuille.simulation());
            plateau.restaurePosition(0);
            developper(nbDeveloppement-1);
        }
    }

    //On lit l'arbre en choisissant le coup avec le meilleur ratio victoires/partieJouées
    public Coup getMeilleurCoup(){
        double max = (double) getSousArbre(0).getNbVictoire()/getSousArbre(0).getNbSimulation();
        int positionMax = 0;
        double pourcentageReussite;
        for(int i=0;i<sousArbres.size();i++){
            pourcentageReussite = (double) getSousArbre(i).getNbVictoire()/getSousArbre(i).getNbSimulation();
            if(max< pourcentageReussite){
                max = pourcentageReussite;
                positionMax = i;
            }
        }
        return(getSousArbre(positionMax).getCoup());
    }

}

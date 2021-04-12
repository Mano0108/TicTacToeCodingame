package tictactoecodingame;

public class ArbreMC {
    NoeudMC racine;

    public ArbreMC(){
        racine = new NoeudMC();
    }

    public ArbreMC (NoeudMC racine){
        this.racine=racine;
    }

    public NoeudMC getRacine(){
        return racine;
    }

    public void setRacine (NoeudMC racine){
        this.racine = racine;
    }

    public void ajouterEnfant(NoeudMC parent, NoeudMC enfant){
        parent.getNoeudsEnfants().add(enfant);
    }
}

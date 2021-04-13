package tictactoecodingame;

import java.util.List;
import java.util.ArrayList;

public class NoeudMC {
    NoeudMC parent; //renvoie le noeud précédent
    // Manque le State
    ArrayList<NoeudMC> noeudsEnfants; // renvoie la liste des noeuds enfants
    int nbSimulations  ; // nombre de partie lancer sous le noeud
    int nbVictoire; // nombre de victoire sur le noeud

    public Noeuds() {
        //this.state = new State();
        noeudsEnfants = new ArrayList<>();
    }

//    public NoeudMC(State state) {
//        //this.state = state;
//        noeudsEnfants = new ArrayList<>();
//    }
//
//    public NoeudMC(State state, NoeudMC parent, ArrayList<NoeudMC> childArray) {
//        //this.state = state;
//        this.parent = parent;
//        this.noeudsEnfants = noeudsEnfants;
//    }

    public NoeudMc(Node node) {
        this.noeudsEnfants = new ArrayList<>();
        this.state = new State(NoeudMC.getState());
        if (NoeudMC.getParent() != null)
            this.parent = NoeudMC.getParent();
        List<Node> childArray = node.getChildArray();
        for (Node child : childArray) {
            this.childArray.add(new Node(child));
        }
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public List<Node> getChildArray() {
        return childArray;
    }

    public void setChildArray(List<Node> childArray) {
        this.childArray = childArray;
    }

    public Node getRandomChildNode() {
        int noOfPossibleMoves = this.childArray.size();
        int selectRandom = (int) (Math.random() * noOfPossibleMoves);
        return this.childArray.get(selectRandom);
    }

    public Node getChildWithMaxScore() {
        return Collections.max(this.childArray, Comparator.comparing(c -> {
            return c.getState().getVisitCount();
        }));
    }

}



    public ArrayList<NoeudMC> getNoeudsEnfants() {
        return noeudsEnfants;
    }

    public Noeuds() {
       this.Plateau = new Plateau();
        noeudsEnfants = new ArrayList<>();
    }


}



import java.util.ArrayList;

public class Fournisseur extends Utilisateur {

    private int rating;
    private int visites;
    private int transactions;
    private ArrayList<String> typesComposantes;

    public Fournisseur(String username, String password){
        super(username, password);
    }

    // -------------------------- GETTER SETTER --------------------------


    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getVisites() {
        return visites;
    }

    public void setVistes(int vistes) {
        this.visites = vistes;
    }

    public int getTransactions() {
        return transactions;
    }

    public void setTransactions(int transactions) {
        this.transactions = transactions;
    }

    public ArrayList<String> getTypesComposantes() {
        return typesComposantes;
    }

    public void setTypesComposantes(ArrayList<String> typesComposantes) {
        this.typesComposantes = typesComposantes;
    }

    // -------------------------- UTILS METHODS --------------------------
    
    public void acheterComposante(Composante c){
        this.composantes.remove(c);
    }
}

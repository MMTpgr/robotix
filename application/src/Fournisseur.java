import java.util.ArrayList;

public class Fournisseur extends Utilisateur {

    private ArrayList<Composante> composantes;

    private int rating;
    private int vistes;
    private int transactions;
    private ArrayList<String> typesComposantes;

    public Fournisseur(String username, String password){
        super(username, password);
    }

    // -------------------------- GETTER SETTER --------------------------
    
    public ArrayList<Composante> getComposantes() {
        return composantes;
    }

    public void setComposantes(ArrayList<Composante> composantes) {
        this.composantes = composantes;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getVistes() {
        return vistes;
    }

    public void setVistes(int vistes) {
        this.vistes = vistes;
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

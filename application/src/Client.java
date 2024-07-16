import java.util.ArrayList;

public class Client extends Utilisateur {

    private ArrayList<Fournisseur> fournisseurs = new ArrayList<>();

    private Flotte flotte = new Flotte();
    public Client(String username, String password) {
        super(username, password);
    }
    
    // -------------------------- GETTER SETTER --------------------------
    public ArrayList<Fournisseur> getFournisseurs() {
        return fournisseurs;
    }

    public void setFournisseurs(ArrayList<Fournisseur> fournisseurs) {
        this.fournisseurs = fournisseurs;
    }

    public Flotte getFlotte() {
        return flotte;
    }

    public void setFlotte(Flotte flotte) {
        this.flotte = flotte;
    }

    // -------------------------- UTILS METHODS --------------------------
    
    public void addFournisseur(Fournisseur fourn){
        this.fournisseurs.add(fourn);
    }

}

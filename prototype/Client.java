import java.util.ArrayList;

public class Client extends Utilisateur{

    private ArrayList<Fournisseur> fournisseurs = new ArrayList<>();
    private ArrayList<String> interets = new ArrayList<>();

    public Client(String username, String password) {
        super(username, password);
    }

    public void addFournisseur(Fournisseur fourn){
        this.fournisseurs.add(fourn);
    }

}

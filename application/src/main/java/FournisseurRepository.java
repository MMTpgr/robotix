import java.util.ArrayList;
import java.util.Comparator;

public class FournisseurRepository {

    private static FournisseurRepository _instance;
    private String dataFile = "Fournisseurs.json";
    private ArrayList<Fournisseur> fournisseurs;

    // -------------------------- GETTER SETTER --------------------------

    // Singleton
    public static FournisseurRepository getInstance(){
        if (_instance == null){
            _instance = new FournisseurRepository();
        }
        return _instance;
    }

    private FournisseurRepository(){

    }

    // -------------------------- UTILS METHODS --------------------------

    public ArrayList<Fournisseur> getFournisseurs() {
        if (this.fournisseurs == null){
            this.parseFournisseurs();
        }
        return this.fournisseurs;
    }

    public Fournisseur getFournisseur(String name){

        Fournisseur foundFournisseur = null;

        return foundFournisseur;

    }

    public ArrayList<Fournisseur> parseFournisseurs(){

        ArrayList<Fournisseur> fournisseurs = new ArrayList<>();
        Fournisseur currentFournisseur;

        // JSON PARSING HERE

        return fournisseurs;

    }

    public void writeFournisseurs(){

        // JSON WRITING HERE

    }

}



import java.util.ArrayList;
import java.util.Comparator;

public abstract class FournisseurRepository {


    private String dataFile = "Fournisseurs.json";


    // -------------------------- GETTER SETTER --------------------------


    // -------------------------- UTILS METHODS --------------------------

    public Fournisseur getFournisseur(String name){

        Fournisseur foundFournisseur = null;

        return foundFournisseur;

    }

    public void addFournisseur(Fournisseur fournisseur){
        return;
    }

    public void removeFournisseur(String name){
        return;
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



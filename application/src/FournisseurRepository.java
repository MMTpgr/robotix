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


    public ArrayList<Fournisseur> filterFournisseurs (FournisseurFilter filter){

        ArrayList<Fournisseur> fournisseurs = this.parseFournisseurs();

        // Filtering with Treeset or sort function.
        if (filter.equals(FournisseurFilter.ANCIENNETE)){
            fournisseurs.sort(new AncienneteComparatorF());
        } else if (filter.equals(FournisseurFilter.RATING)) {
            fournisseurs.sort(new RatingComparator());
        } else if (filter.equals(FournisseurFilter.VISITES)) {
            fournisseurs.sort(new VisiteComparator());
        } else if (filter.equals(FournisseurFilter.TRANSACTIONS)) {
            fournisseurs.sort(new TransactionComparator());
        }else if (filter.equals(FournisseurFilter.TYPECOMPOSANTES)) {
            fournisseurs.sort(new TypeComposanteComparator());
        }

        return fournisseurs;
    }
}

// -------------------------- Filtering --------------------------

enum FournisseurFilter{
    ANCIENNETE,
    RATING,
    VISITES,
    TRANSACTIONS,
    TYPECOMPOSANTES
}

class AncienneteComparatorF implements Comparator<Fournisseur> {
    @Override
    public int compare(Fournisseur f1, Fournisseur f2) {
        return f1.getInscription().compareTo(f2.getInscription());
    }
}

class RatingComparator implements Comparator<Fournisseur> {
    @Override
    public int compare(Fournisseur f1, Fournisseur f2) {
        return Integer.compare( f1.getRating(), f2.getRating() );
    }
}
class VisiteComparator implements Comparator<Fournisseur> {
    @Override
    public int compare(Fournisseur f1, Fournisseur f2) {
        return Integer.compare(f1.getVistes(), f2.getVistes());
    }
}

class TransactionComparator implements Comparator<Fournisseur> {
    @Override
    public int compare(Fournisseur f1, Fournisseur f2) {
        return Integer.compare(f1.getTransactions(), f2.getTransactions());
    }
}

class TypeComposanteComparator implements Comparator<Fournisseur> {
    @Override
    public int compare(Fournisseur f1, Fournisseur f2) {

        // DO SOMETHING SOMEHOW :^)
        return 0;
    }
}


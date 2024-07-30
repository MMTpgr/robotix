import java.util.ArrayList;
import java.util.Comparator;

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


    public static void filterFournisseurs (ArrayList<Fournisseur> toSort, FournisseurFilter filter){

        // Filtering with Treeset or sort function.
        if (filter.equals(FournisseurFilter.ANCIENNETE)){
            toSort.sort(new AncienneteComparatorF());
        } else if (filter.equals(FournisseurFilter.RATING)) {
            toSort.sort(new RatingComparator());
        } else if (filter.equals(FournisseurFilter.VISITES)) {
            toSort.sort(new VisiteComparator());
        } else if (filter.equals(FournisseurFilter.TRANSACTIONS)) {
            toSort.sort(new TransactionComparator());
        }else if (filter.equals(FournisseurFilter.TYPECOMPOSANTES)) {
            toSort.sort(new TypeComposanteComparator());
        }
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
        return Integer.compare(f1.getVisites(), f2.getVisites());
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
        // DO SOMETHING SOMEHOW :)
        return 0;
    }
}
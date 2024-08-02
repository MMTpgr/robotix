import java.util.ArrayList;
import java.util.Comparator;

public class Fournisseur extends Utilisateur {

    private int rating;
    private int visites;
    private int transactions;
    private ArrayList<ComposanteType> typesComposantes;
    private ArrayList<Composante> composantes; // Ajout de la liste de composantes

    public Fournisseur(String username, String password) {
        super(username, password);
        this.composantes = new ArrayList<>(); // Initialisation de la liste de composantes
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

    public ArrayList<ComposanteType> getTypesComposantes() {
        return typesComposantes;
    }

    public void setTypesComposantes(ArrayList<ComposanteType> typesComposantes) {
        this.typesComposantes = typesComposantes;
    }

    public ArrayList<Composante> getComposantes() {
        return composantes;
    }

    // -------------------------- UTILS METHODS --------------------------

    public void ajouterComposante(Composante composante) {
        this.composantes.add(composante);
    }

    public void supprimerComposante(String nom) {
        composantes.removeIf(comp -> comp.getNom().equalsIgnoreCase(nom));
    }

    public void acheterComposante(Composante c) {
        this.composantes.remove(c);
    }

    public static void filterFournisseurs(ArrayList<Fournisseur> toSort, FournisseurFilter filter) {
        // Filtering with Treeset or sort function.
        if (filter.equals(FournisseurFilter.NOM)) {
            toSort.sort(new NomComparatorF());
        } else if (filter.equals(FournisseurFilter.TYPECOMPOSANTES)) {
            toSort.sort(new TypeComposanteComparator());
        }
    }
}


// -------------------------- Filtering --------------------------

enum FournisseurFilter {
    NOM,
    TYPECOMPOSANTES
}

class NomComparatorF implements Comparator<Fournisseur> {
    @Override
    public int compare(Fournisseur f1, Fournisseur f2) {
        return f1.getUsername().compareTo(f2.getUsername());
    }
}

class TypeComposanteComparator implements Comparator<Fournisseur> {
    @Override
    public int compare(Fournisseur f1, Fournisseur f2) {
        // DO SOMETHING SOMEHOW :)
        return 0;
    }
}

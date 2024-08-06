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

        ArrayList<ComposanteType> composanteTypes = new ArrayList<>();

        for (Composante composante : this.getComposantes()){

            if (!composanteTypes.contains(composante.getType())){
                composanteTypes.add(composante.getType());
            }

        }
        this.typesComposantes = composanteTypes;
        return typesComposantes;
    }

    public void setTypesComposantes(ArrayList<ComposanteType> typesComposantes) {
        this.typesComposantes = typesComposantes;
    }

    public ArrayList<Composante> getComposantes() {
        return composantes;
    }

    public void setComposantes(ArrayList<Composante> composantes) {
        this.composantes = composantes;
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
    /**
     * Comparaison par Nom
     *
     * @param f1 the first Fournisseur to be compared.
     * @param f2 the second Fournisseur to be compared.
     * @return
     */
    @Override
    public int compare(Fournisseur f1, Fournisseur f2) {
        return f1.getUsername().compareTo(f2.getUsername());
    }
}

class TypeComposanteComparator implements Comparator<Fournisseur> {

    /**
     * Comparaison par type de composantes
     *
     * @param f1 the first Fournisseur to be compared.
     * @param f2 the second Fournisseur to be compared.
     * @return
     */
    @Override
    public int compare(Fournisseur f1, Fournisseur f2) {
        return Integer.compare(f1.getTypesComposantes().size(), f2.getTypesComposantes().size());
    }
}

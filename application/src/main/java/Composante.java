import java.util.ArrayList;
import java.util.Comparator;

enum ComposanteType {
    ROUE,
    MICRO,
    ECRAN,
    HELICE,
    BRAS,
    CPU,
    CAMERA,
    HAUTPARLEUR
}

public class Composante {

    protected ComposanteType type;
    protected String[] actionsPossibles;
    private float prix;
    protected String id;
    protected String fournisseur;
    protected String nom;
    protected String description; // Ajout de l'attribut description

    // Constructeur par défaut
    public Composante() {
    }

    // Constructeur avec paramètres
    public Composante(String nom, String type, String description, String prix) {
        this.nom = nom;
        this.type = ComposanteType.valueOf(type.toUpperCase());
        this.description = description; // Initialisation de l'attribut description
        this.prix = Float.parseFloat(prix);
    }

    // -------------------------- GETTER SETTER --------------------------

    public String[] getActionsPossibles() {
        return actionsPossibles;
    }

    public void setActionsPossibles(String[] actionsPossibles) {
        this.actionsPossibles = actionsPossibles;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(String fournisseur) {
        this.fournisseur = fournisseur;
    }

    public ComposanteType getType() {
        return this.type;
    }

    public void setType(ComposanteType type) {
        this.type = type;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Nom: " + nom + ", Type: " + type + ", Description: " + description + ", Prix: " + prix;
    }

    // -------------------------- UTILS METHODS --------------------------

    public static void filterComposantes(ArrayList<Composante> toSort, ComposanteFilter filter) {
        // Filtering with Treeset or sort function.
        if (filter.equals(ComposanteFilter.NAME)) {
            toSort.sort(new NameComparator());
        } else if (filter.equals(ComposanteFilter.TYPE)) {
            toSort.sort(new TypeComparator());
        } else if (filter.equals(ComposanteFilter.FOURNISSEUR)) {
            toSort.sort(new FournisseurComparator());
        }
    }
}

// -------------------------- Filtering --------------------------

enum ComposanteFilter {
    NAME,
    TYPE,
    FOURNISSEUR
}

class NameComparator implements Comparator<Composante> {
    /**
     * Comparaison par nom
     *
     * @param c1 the first Composante to be compared.
     * @param c2 the second Composante to be compared.
     * @return
     */
    @Override
    public int compare(Composante c1, Composante c2) {
        return c1.getNom().compareTo(c2.getNom());
    }
}

class TypeComparator implements Comparator<Composante> {
    /**
     * Comparaison par type
     *
     * @param c1 the first Composante to be compared.
     * @param c2 the second Composante to be compared.
     * @return
     */
    @Override
    public int compare(Composante c1, Composante c2) {
        return c1.getType().compareTo(c2.getType());
    }
}

class FournisseurComparator implements Comparator<Composante> {
    /**
     * Comparaison par fournisseur
     *
     * @param c1 the first Composante to be compared.
     * @param c2 the second Composante to be compared.
     * @return
     */
    @Override
    public int compare(Composante c1, Composante c2) {
        return c1.getFournisseur().compareTo(c2.getFournisseur());
    }
}

import java.util.ArrayList;
import java.util.Comparator;

enum ComposanteType{
    ROUE,
    MICRO,
    ECRAN,
    HELICE,
    BRAS,
    CPU,
    CAMERA,
    HAUTPARLEUR
}

public abstract class Composante {

    protected ComposanteType type;

    public Composante(){

    }

    protected String[] actionsPossibles;


    private float prix;

    protected String id;

    protected Fournisseur fournisseur;

    protected String nom;

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

    public Fournisseur getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(Fournisseur fournisseur) {
        this.fournisseur = fournisseur;
    }

    public ComposanteType getType(){
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

    // -------------------------- UTILS METHODS --------------------------


    public static  void filterComposantes (ArrayList<Composante> toSort, ComposanteFilter filter){

        // Filtering with Treeset or sort function.
        if (filter.equals(ComposanteFilter.NAME)){
            toSort.sort(new NameComparator());
        } else if (filter.equals(ComposanteFilter.TYPE)) {
            toSort.sort(new TypeComparator());
        }else if (filter.equals(ComposanteFilter.FOURNISSEUR)) {
            toSort.sort(new FournisseurComparator());
        }

    }

}


// -------------------------- Filtering --------------------------

enum ComposanteFilter{
    NAME,
    TYPE,
    FOURNISSEUR
}

class NameComparator implements Comparator<Composante> {
    @Override
    public int compare(Composante c1, Composante c2) {
        return c1.getNom().compareTo(c2.getNom());
    }
}

class TypeComparator implements Comparator<Composante> {
    @Override
    public int compare(Composante c1, Composante c2) {
        return c1.getType().compareTo(c2.getType());
    }
}

class FournisseurComparator implements Comparator<Composante> {
    @Override
    public int compare(Composante c1, Composante c2) {
        return c1.getFournisseur().getUsername().compareTo(c2.getFournisseur().getUsername());
    }
}

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

    private ComposanteType type;

    public Composante(){

    }

    protected String[] actionsPossibles;


    private float prix;

    private String id;

    private Fournisseur fournisseur;

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

    // -------------------------- UTILS METHODS --------------------------


    public static  void filterComposantes (ArrayList<Composante> toSort, ComposanteFilter filter){

        // Filtering with Treeset or sort function.
        if (filter.equals(ComposanteFilter.PRIX)){
            toSort.sort(new PrixComparator());
        } else if (filter.equals(ComposanteFilter.TYPE)) {
            toSort.sort(new TypeComparator());
        }else if (filter.equals(ComposanteFilter.FOURNISSEUR)) {
            toSort.sort(new FournisseurComparator());
        }

    }

}


// -------------------------- Filtering --------------------------

enum ComposanteFilter{
    PRIX,
    TYPE,
    FOURNISSEUR
}

class PrixComparator implements Comparator<Composante> {
    @Override
    public int compare(Composante c1, Composante c2) {
        return Float.compare(c1.getPrix(), c2.getPrix());
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

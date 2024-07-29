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

}

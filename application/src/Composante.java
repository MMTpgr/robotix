public abstract class Composante {

    public Composante(){

    }

    protected String[] actionsPossibles;

    private String type;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    // -------------------------- UTILS METHODS --------------------------

}

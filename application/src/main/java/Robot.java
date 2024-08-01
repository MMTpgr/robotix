import java.util.ArrayList;

public class Robot {
    private String nom;
    private String type;
    private String numSerie;
    private int batterie;
    private boolean brise;
    private boolean cpustable;
    private float consommationCPU;
    private int[] position;
    private float vitesse;

    ArrayList<Composante> composantes;

    public Robot(String[] infos, int bat, boolean brise,ArrayList<Composante> Composantes){
        this.nom = infos[0];
        this.type = infos [1];
        this.numSerie = infos[2];
        this.batterie = bat;
        this.brise = brise;
        this.cpustable = true;
        this.composantes = Composantes;
    }
    // -------------------------- GETTER SETTER --------------------------

    public String getNom() {
        return nom;
    }

    public String getType() {
        return type;
    }

    public String getNumSerie() {
        return numSerie;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setNumSerie(String numSerie) {
        this.numSerie = numSerie;
    }

    public int getBatterie() {
        return batterie;
    }

    public void setBatterie(int batterie) {
        this.batterie = batterie;
    }

    public boolean isBrise() {
        return brise;
    }

    public void setBrise(boolean brise) {
        this.brise = brise;
    }

    public boolean isCpustable() {
        return cpustable;
    }

    public void setCpustable(boolean cpustable) {
        this.cpustable = cpustable;
    }

    public ArrayList<Composante> getComposantes() {
        return composantes;
    }

    public void setComposantes(ArrayList<Composante> composantes) {
        this.composantes = composantes;
    }

    public float getVitesse(){
        return this.vitesse;
    }

    public float getConsommationCPU(){
        return this.consommationCPU;
    }

    public int[] getPosition(){
        return this.position;
    }

    public void setVitesse(float nouvelleVitesse){
        this.vitesse = nouvelleVitesse;
    }

    public void setConsommationCPU (float nouvelleConsommation) {
        this.consommationCPU = nouvelleConsommation;
    }

    public void setPosition (int[] nouvelleposition) {
        this.position = nouvelleposition;
    }

    // -------------------------- UTILS METHODS --------------------------
    @Override
    public String toString() {
        return this.getNom() + " - " + this.getType() + " (" + this.getNumSerie() + ")";
    }
}

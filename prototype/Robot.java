import java.util.Arrays;

public class Robot {
    private String nom;
    private String type;
    private String numSerie;
    public int batterie;
    public boolean brise;
    public boolean cpustable;

    Composante[] composantes;

    public Robot(String[] infos, int bat, boolean brise){
        this.nom = infos[0];
        this.type = infos [1];
        this.numSerie = infos[2];
        this.batterie = bat;
        this.brise = brise;
        this.cpustable = true;
    }

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

    @Override
    public String toString() {
        return this.getNom() + " - " + this.getType() + " (" + this.getNumSerie() + ")";
    }
}

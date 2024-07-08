import java.util.ArrayList;
import java.util.Arrays;

public class Activite {
    String desc;
    ArrayList<Robot> robotsInclus;
    ArrayList<Interet> interetsConcernes;
    private ETAT etat;

    public Activite(String desc, Robot[] robots, ETAT etat){
        this.desc = desc;
        this.robotsInclus = new ArrayList<>(Arrays.asList(robots));
        this.etat = etat;

        // Définir les intérêts concernés de la façon qui sera demandée d'implémenter DM3+...
    }

    public enum ETAT{
        NONDEBUTEE,
        ENCOURS,
        TERMINEE
    }
}

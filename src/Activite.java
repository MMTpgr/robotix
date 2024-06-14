import java.util.ArrayList;
import java.util.Arrays;

public class Activite {
    String desc;
    ArrayList<Robot> robotsInclus;

    public Activite(String desc, Robot[] robots){
        this.desc = desc;
        this.robotsInclus = new ArrayList<>(Arrays.asList(robots));
    }
}

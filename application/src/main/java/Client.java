import java.util.ArrayList;
import java.util.Comparator;

public class Client extends Utilisateur {
    private ArrayList<Composante> composantesU;
    private final ArrayList<String> activites = new ArrayList<>();
    private final Flotte flotte = new Flotte();
    private int points;
    public Client(String username, String password) {
        super(username, password);
        this.composantesU = new ArrayList<>();
        this.points = 0;
    }
    
    // -------------------------- GETTER SETTER --------------------------

    public Flotte getFlotte() {
        return flotte;
    }

    public ArrayList<Composante> getComposantes() {
        return composantesU;
    }

    public void setComposantes(ArrayList<Composante> composantesU) {
        this.composantesU = composantesU;
    }

    public ArrayList<String> getActivites() {
        return activites;
    }

    public void addActivite(String act){
        this.activites.add(act);
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    // -------------------------- UTILS METHODS --------------------------

}


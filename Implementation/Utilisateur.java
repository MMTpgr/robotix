import java.util.ArrayList;
import java.util.Arrays;

public abstract class Utilisateur {
    private String username;
    private String password;
    ArrayList<Robot> robots = new ArrayList<>();
    ArrayList<Activite> activites = new ArrayList<>();

    public Utilisateur(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void addRobot(Robot robot){
        this.robots.add(robot);
    }

    public void addActivite(Activite act){
        this.activites.add(act);
    }

}
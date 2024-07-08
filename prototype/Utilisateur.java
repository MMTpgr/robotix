import java.util.ArrayList;
import java.util.Arrays;

public abstract class Utilisateur {
    private String username;
    private String password;
     ArrayList<Robot> robots = new ArrayList<>();
     ArrayList<Activite> activites = new ArrayList<>();
     ArrayList<Utilisateur> followers = new ArrayList<>();
     ArrayList<Interet> interets = new ArrayList<>();
     public int points;

    public Utilisateur(String username, String password) {
        this.username = username;
        this.password = password;
        this.points = 0;
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

    public void addInteret(Interet interet){
        this.interets.add(interet);
    }

    public void addFollower(Utilisateur follower){
        this.followers.add(follower);
    }

    // Autres méthodes à venir, possiblement pour se désabonner, gérer les
    // abonnements, etc...

}
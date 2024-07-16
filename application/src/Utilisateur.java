import java.time.LocalDate;
import java.util.ArrayList;

public abstract class Utilisateur {
    private String username;
    private String password;
    private ArrayList<Activite> activites = new ArrayList<>();
    private ArrayList<Utilisateur> followers = new ArrayList<>();
    private ArrayList<Interet> interets = new ArrayList<>();
    private int points;
    private LocalDate inscription;
    private ArrayList<Notification> notifications;
    private ArrayList<Composante> composantes;

    public Utilisateur(String username, String password) {
        this.username = username;
        this.password = password;
        this.points = 0;
    }
    
    // -------------------------- GETTER SETTER --------------------------
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Activite> getActivites() {
        return activites;
    }

    public void setActivites(ArrayList<Activite> activites) {
        this.activites = activites;
    }

    public ArrayList<Utilisateur> getFollowers() {
        return followers;
    }

    public void setFollowers(ArrayList<Utilisateur> followers) {
        this.followers = followers;
    }

    public ArrayList<Interet> getInterets() {
        return interets;
    }

    public void setInterets(ArrayList<Interet> interets) {
        this.interets = interets;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public LocalDate getInscription() {
        return inscription;
    }

    public void setInscription(LocalDate inscription) {
        this.inscription = inscription;
    }
    
    public String getUsername() {
        return username;
    }

    public ArrayList<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(ArrayList<Notification> notifications) {
        this.notifications = notifications;
    }

    public ArrayList<Composante> getComposantes() {
        return composantes;
    }

    public void setComposantes(ArrayList<Composante> composantes) {
        this.composantes = composantes;
    }

    // -------------------------- UTILS METHODS --------------------------


    public void addActivite(Activite act){
        this.activites.add(act);
    }

    public void addInteret(Interet interet){
        this.interets.add(interet);
    }

    public void addFollower(Utilisateur follower){
        this.followers.add(follower);
    }

    public void addNotification(Notification notification){
        this.notifications.add(notification);
    }

}
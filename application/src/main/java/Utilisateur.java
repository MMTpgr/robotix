import java.time.LocalDate;
import java.util.ArrayList;

public abstract class Utilisateur {
    protected String username;
    protected String password;
    protected ArrayList<Notification> notifications = new ArrayList<>();

    public Utilisateur(String username, String password) {
        this.username = username;
        this.password = password;
        this.notifications = new ArrayList<>();
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
    
    public String getUsername() {
        return username;
    }

    public ArrayList<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(ArrayList<Notification> notifications) {
        this.notifications = notifications;
    }

    // -------------------------- UTILS METHODS -------------------------- //

    public void addNotification(Notification notification){
        this.notifications.add(notification);
    }

    /**
     * Méthode toString() qui renvoit un message en String à afficher lorsque l'on veux voir
     * toutes les notifications associées à un user
     * @return
     */
    public String toStringNotifs(){
        String display = "";
        for (Notification notif : notifications){
            display += "Notification - FROM " + notif.getFrom() + ": " + notif.getMessage() + "\n";
        }
        return display;
    }
}
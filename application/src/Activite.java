import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.time.LocalDate;


public class Activite {
    private String name;
    private LocalDate date;
    private String desc;
    private int popularite;
    private LinkedList<Tache> taches;
    private String host;
    private ArrayList<String> participants;
    private ArrayList<Robot> robotsInclus;
    private ArrayList<Interet> interetsConcernes;
    private ETAT etat;

    public Activite(String name, LocalDate date, String desc, String host, ETAT etat){
        this.desc = desc;
        this.etat = etat;

        // Définir les intérêts concernés de la façon qui sera demandée d'implémenter DM3+...
    }

    public enum ETAT{
        NONDEBUTEE,
        ENCOURS,
        TERMINEE
    }

    public ArrayList<Composante> getComposantesRequises() {

        ArrayList<Composante> composantesRequise = new ArrayList<>();

        for (Tache tache : this.taches){
            for (Action action: tache.getActions()){
                composantesRequise.addAll(action.getComposantesRequises());
            }
        }

        return composantesRequise;
    }

    public void addTache(Tache tache, int order){

        for (Tache t : this.taches){
            if (t.getName().equals(tache.getName())){
                System.out.println("Tache avec nom: " + tache.getName() + " deja presente. Renommer la tache.");
                return;
            }
        }
        this.taches.add(order, tache);
    }

    public void removeTache(String name){

        Tache tache = this.getTacheByName(name);

        if ((tache==null)){
            System.out.println("Tache non presente.");
            return;
        }

        this.taches.remove(tache);
    }


    public Tache getTacheByName(String name){
        Tache tache = null;

        for (Tache t : this.taches){
            if (t.getName().equals(name)){
                tache = t;
                break;
            }
        }
        return tache;
    }


    // Getter Setter --------------------------
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getPopularite() {
        return popularite;
    }

    public void setPopularite(int popularite) {
        this.popularite = popularite;
    }

    public LinkedList<Tache> getTaches() {
        return taches;
    }

    public void setTaches(LinkedList<Tache> taches) {
        this.taches = taches;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public ArrayList<String> getParticipants() {
        return participants;
    }

    public void setParticipants(ArrayList<String> participants) {
        this.participants = participants;
    }

    public ArrayList<Interet> getInteretsConcernes() {
        return interetsConcernes;
    }

    public void setInteretsConcernes(ArrayList<Interet> interetsConcernes) {
        this.interetsConcernes = interetsConcernes;
    }
}

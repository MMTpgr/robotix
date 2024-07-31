import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.time.LocalDate;

enum ACTIVITEETAT{
    NONDEBUTEE,
    ENCOURS,
    TERMINEE
}



public class Activite {
    private String name;
    private LocalDate date;
    private String desc;
    private int popularite = 0;
    private ArrayList<Tache> taches = new ArrayList<>();
    private String host;
    private ArrayList<String> participants = new ArrayList<>();
    private ArrayList<String> robotsInclus = new ArrayList<>();
    private ArrayList<Interet> interetsConcernes = new ArrayList<>();
    private ACTIVITEETAT etat;

    /**
     * Constructeur Activite.
     *
     * @param name Nom de l'activité
     * @param date Date de l'activité
     * @param desc Description de l'activité
     * @param host Host de l'activité
     * @param etat État de l'activité
     */
    public Activite(String name, LocalDate date, String desc, String host, ACTIVITEETAT etat){
        this.name = name;
        this.date = date;
        this.host = host;
        this.desc = desc;
        this.etat = etat;
    }

    /**
     * Obtient les composantes requises de l'activité selon les tâches et les actions.
     *
     * @return ArrayList de composantes requises.
     */
    public ArrayList<ComposanteType> getComposantesRequises() {

        ArrayList<ComposanteType> composantesRequise = new ArrayList<>();

        for (Tache tache : this.taches){
            for (Action action: tache.getActions()){
                for (ComposanteType composanteType : action.getComposantesRequises()){
                    if (!composantesRequise.contains(composanteType)){
                        composantesRequise.add(composanteType);
                    }
                }
            }
        }

        return composantesRequise;
    }

    /**
     * Ajout d'une tâche à l'activité.
     * La tâche doit avoir un nom unique parmis les tâches de l'activité.
     *
     * @param tache Tache à ajouter.
     * @param order Index de la tâche à ajouter.
     */
    public void addTache(Tache tache, int order){

        for (Tache t : this.taches){
            if (t.getName().equals(tache.getName())){
                System.out.println("Tache avec nom: " + tache.getName() + " deja presente. Renommer la tache.");
                return;
            }
        }
        this.taches.add(order, tache);
    }


    /**
     * Obtenir une tâche à partir de son nom.
     *
     * @param name String nom.
     * @return Objet 'Tache'.
     */
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

    /**
     * Ajouter un participant ainsi qu'une liste de robots à l'activité.
     *
     * @param client Client à ajouter.
     * @param robots Robots à ajouter.
     */
    public void addParticipant(Client client, ArrayList<Robot> robots){
        this.participants.add(client.getUsername());

        ArrayList<String> robotsName = new ArrayList<>();

        for(Robot r : robots){
            robotsName.add(r.getNom());
        }

        this.robotsInclus.addAll(robotsName);
    }

    /**
     * Retrait d'un participant à l'activité, ainsi que ses robots.
     * 
     * @param client Client à retirer. 
     */
    public void removeParticipant(Client client){

        this.participants.remove(client.getUsername());

        ArrayList<String> robotsName = new ArrayList<>();

        for(Robot r : client.getFlotte().getRobots()){
            robotsName.add(r.getNom());
        }

        this.robotsInclus.removeAll(robotsName);

    }

    /**
     * Ordonner d'une liste d'activités.
     * 
     * @param toSort Liste d'activités.
     * @param filter Filtre à appliquer.
     */
    public static void sortActivites(ArrayList<Activite> toSort,
                                                    ActiviteFilter filter){

        // Filtering with Treeset or sort function.
        if (filter.equals(ActiviteFilter.NOM)) {
            toSort.sort(new NomComparator());
        } else if (filter.equals(ActiviteFilter.DATE)) {
            toSort.sort(new DateComparator());
        } else if (filter.equals(ActiviteFilter.POPULARITE)) {
            toSort.sort(new PopulariteComparator());
        }

    }


    // Getter Setter --------------------------
    public String getName() {
        return this.name;
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

    public ArrayList<Tache> getTaches() {
        return taches;
    }

    public void setTaches(ArrayList<Tache> taches) {
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

    public ArrayList<String> getRobotsInclus() {
        return robotsInclus;
    }

    public void setRobotsInclus(ArrayList<String> robotsInclus) {
        this.robotsInclus = robotsInclus;
    }

    public ACTIVITEETAT getEtat() {
        return etat;
    }

    public void setEtat(ACTIVITEETAT etat) {
        this.etat = etat;
    }
}

// -------------------------- Filtering --------------------------

enum ActiviteFilter{
    NOM,
    DATE,
    POPULARITE
}

class NomComparator implements Comparator<Activite> {
    /**
     * Comparer selon le nom
     *
     * @param a1 the first object to be compared.
     * @param a2 the second object to be compared.
     * @return
     */
    @Override
    public int compare(Activite a1, Activite a2) {
        return a1.getName().compareTo(a2.getName());
    }
}

class DateComparator implements Comparator<Activite> {
    /**
     * Comparer selon la date
     *
     * @param a1 the first object to be compared.
     * @param a2 the second object to be compared.
     * @return
     */
    @Override
    public int compare(Activite a1, Activite a2) {
        return a1.getDate().compareTo(a2.getDate());
    }
}
class PopulariteComparator implements Comparator<Activite> {
    /**
     * Comparer selon la popularité.
     *
     * @param a1 the first object to be compared.
     * @param a2 the second object to be compared.
     * @return
     */
    @Override
    public int compare(Activite a1, Activite a2) {
        return Integer.compare(a1.getPopularite(), a2.getPopularite());
    }
}
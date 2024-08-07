import java.util.ArrayList;

public class ActiviteController{

    private static ActiviteController _instance;


    // -------------------------- GETTER SETTER --------------------------

    /**
     * Singleton
     *
     * @return Unique instance de ActiviteController
     */
    public static ActiviteController getInstance(){
        if (_instance == null){
            _instance = new ActiviteController();
        }
        return _instance;
    }

    private ActiviteController(){

    }


    /**
     * Repertoire des activités
     *
     * @return Unique instance de ActiviteRepository.
     */
    public ActiviteRepository getRepository(){
        return ActiviteRepository.getInstance();
    }
    
    
    // -------------------------- UTILS METHODS --------------------------

    /**
     * Inscription d'un client à une activité.
     * 
     * @param client Client à ajouter
     * @param activite Activité cible
     * @return Inscription est un succes ou non.
     */
    public boolean inscriptionClient(Client client, Activite activite){

        ArrayList<Robot> robotValides = clientValidesRobotsForActivite(client, activite);

        if (robotValides.isEmpty()){
            return false;
        }

        activite.addParticipant(client, robotValides);
        client.addActivite(activite.getName());

        Notification notification = new Notification("Menu Activite", "Vous etes maintenant inscrit à " + activite.getName());

        client.addNotification(notification);

        return true;
    }

    /**
     * Vérifie si un client dispose de robots valides pour une activité.
     *
     * @param client Client à valider
     * @param activite Activité cible.
     *
     * @return ArrayList de robots valides.
     */
    public ArrayList<Robot> clientValidesRobotsForActivite(Client client,
                                                         Activite activite){

        ArrayList<Robot> robotValides = new ArrayList<>();

        for (Robot robot : client.getFlotte().getRobots()){
            for (Composante composante : robot.getComposantes()){
                if (activite.getComposantesRequises().contains(composante.getType().name())){
                    robotValides.add(robot);
                    break;
                }
            }

        }
        return robotValides;
    }

    /**
     * Désincription d'un client à une activité.
     *
     * @param client Client à désinscrire
     * @param activite Activité cible
     */
    public void desinscriptionClient(Client client, Activite activite){

        activite.getParticipants().remove(client);
        client.getActivites().remove(activite.getName());

        for (Robot robot : client.getFlotte().getRobots()){
                activite.getRobotsInclus().remove(robot.getNom());
        }

        Notification notification = new Notification("Menu Activite", "Vous etes maintenant désinscrit de " + activite.getName());


        client.addNotification(notification);

    }

    /**
     * Return list of activite names has a list of activites
     *
     * @param names list of names
     * @return list des activites.
     */
    public ArrayList<Activite> activitesNametoList(ArrayList<String> names){

        ArrayList<Activite> activites = new ArrayList<>();

        for (Activite activite : getRepository().getActivites()){

            if (names.contains(activite.getName())){
                activites.add(activite);
            }

        }

        return activites;

    }


}

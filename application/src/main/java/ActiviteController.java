import java.util.ArrayList;

public class ActiviteController{

    private static ActiviteController _instance;

    private ActiviteRepository repository;

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
        client.addActivite(activite);
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
                if (activite.getComposantesRequises().contains(composante.getType())){
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
     * @param activite Activtié cible
     */
    public void desinscriptionClient(Client client, Activite activite){

        activite.getParticipants().remove(client.getUsername());
        client.getActivites().remove(activite);

        for (Robot robot : client.getFlotte().getRobots()){
                activite.getRobotsInclus().remove(robot.getNom());
        }
    }

    public void notifierUtilisateurs(Activite activite, Notification notification){

    }


    public void addPointToUser(Utilisateur utilisateur, int points){

    }


}

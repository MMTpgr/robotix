import java.util.ArrayList;

public class ActiviteController{

    // Singleton
    private static ActiviteController _instance;

    private ActiviteRepository repository;

    // -------------------------- GETTER SETTER --------------------------

    public static ActiviteController getInstance(){
        if (_instance == null){
            _instance = new ActiviteController();
        }
        return _instance;
    }

    public ActiviteRepository getRepository(){
        return ActiviteRepository.getInstance();
    }
    
    
    // -------------------------- UTILS METHODS --------------------------
    
    public boolean inscriptionUtilisateur(Client client, Activite activite){


        ArrayList<Robot> robotValides = clientValidesRobotsForActivite(client, activite);

        if (robotValides.isEmpty()){
            return false;
        }

        activite.addParticipant(client, robotValides);
        return true;
    }

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


    public void DesinscriptionUtilisateur(Client client, Activite activite){



    }

    public void createActivite(ArrayList<String> infos){

    }

    public void annulerActivite(Activite activite){

    }

    public void notifierUtilisateurs(Activite activite, Notification notification){

    }


    public void addPointToUser(Utilisateur utilisateur, int points){

    }


}

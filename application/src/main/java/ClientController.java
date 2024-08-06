import java.util.ArrayList;

public class ClientController extends ClientRepository {

    // Singleton
    private static ClientController _instance;

    public static ClientController getInstance(){
        if (_instance == null){
            _instance = new ClientController();
        }
        return _instance;
    }

    private ClientController(){

    }
    public static ClientRepository getRepository(){
        return ClientRepository.getInstance();
    }


    public boolean createAccount(ArrayList<String> infos){

        boolean succes = true;


        return succes;
    }


    public void notifierUtilisateur(Client client, Notification notification){

        client.addNotification(notification);

    }

    public void emailConfirmation(String email, String message){

        // Do Something
    }


    public void modifierSonProfil(ArrayList<String> infos){

        // Do Something
    }


    public void gererFollowers(ArrayList<String> infos){

        // Do Something
    }


    public void followUtilisateur(Utilisateur utilisateur){
        // Do Something
    }

    public void showNotifications(Utilisateur utilisateur){
        // Do Something
    }


}

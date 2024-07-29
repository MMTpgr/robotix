import java.util.ArrayList;

public class FournisseurController extends FournisseurRepository {

    // Singleton
    private static FournisseurController _instance;

    public static FournisseurController getInstance(){
        if (_instance == null){
            _instance = new FournisseurController();
        }
        return _instance;
    }


    public boolean createAccount(ArrayList<String> infos){

        boolean succes = true;


        return succes;
    }


    public void notifierUtilisateur(Fournisseur fournisseur, Notification notification){

        fournisseur.addNotification(notification);

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

    public void registerComposante(Composante composante){
        // Do Something
    }

    void gererComposantes(ArrayList<String> infos){
        // Do Something
    }



}

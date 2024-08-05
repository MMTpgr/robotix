import java.util.ArrayList;

public class FournisseurController {

    // Singleton
    private static FournisseurController _instance;

    public static FournisseurController getInstance(){
        if (_instance == null){
            _instance = new FournisseurController();
        }
        return _instance;
    }

    private FournisseurController(){

    }


    public boolean createAccount(ArrayList<String> infos){

        boolean succes = true;


        return succes;
    }

    public FournisseurRepository getRepository(){
        return FournisseurRepository.getInstance();
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

    public void registerComposante(Fournisseur fournisseur, Composante composante){

    }

    public void supprimerComposante(){

    }

    void gererComposantes(ArrayList<String> infos){
        // Do Something
    }

}

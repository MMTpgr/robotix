import java.util.ArrayList;

public class ActiviteController extends ActiviteRepository {

    // Singleton
    private static ActiviteController _instance;

    public static ActiviteController getInstance(){
        if (_instance == null){
            _instance = new ActiviteController();
        }
        return _instance;
    }

    // -------------------------- GETTER SETTER --------------------------

    
    
    // -------------------------- UTILS METHODS --------------------------
    
    public void inscriptionUtilisateur(Utilisateur utilisateur, Activite activite){

    }

    public void DesinscriptionUtilisateur(Utilisateur utilisateur, Activite activite){

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

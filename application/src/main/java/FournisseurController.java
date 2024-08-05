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

    /**
     * Return fournisseur repository
     *
     * @return Reposository of fournisseur
     */
    public FournisseurRepository getRepository(){
        return FournisseurRepository.getInstance();
    }


}

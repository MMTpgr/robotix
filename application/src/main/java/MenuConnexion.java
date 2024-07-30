public class MenuConnexion {

    // Singleton
    private static MenuConnexion _instance;

    public static MenuConnexion getInstance(){
        if (_instance == null){
            _instance = new MenuConnexion();
        }
        return _instance;
    }

    // -------------------------- MAIN MENU --------------------------


    public void displayPageStart(){

        // Here we choose between connexion ou inscription

    }

    public void displayPageConnexion(){

    }

    public void displayPageInscriptionClient(){

    }

    public void displayPageInscriptionFournisseur(){

    }

}

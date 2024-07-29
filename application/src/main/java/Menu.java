import java.util.Scanner;

public class Menu {

    // Singleton
    private static Menu _instance;

    public static Menu getInstance(){
        if (_instance == null){
            _instance = new Menu();
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

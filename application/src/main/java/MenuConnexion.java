import java.util.Scanner;

public class MenuConnexion {

    // Singleton
    private static MenuConnexion _instance;

    public static MenuConnexion getInstance(){
        if (_instance == null){
            _instance = new MenuConnexion();
        }
        return _instance;
    }

    private MenuConnexion(){

    }

    // -------------------------- MAIN MENU --------------------------

    public int displayPageStart(){
        System.out.println(
                "######### ROBOTIX Inc #########\n\n" + "--------------------\n" +
                        "\t*CLIENT*\n1- Connexion\n" + "2- Inscription\n---------------\n\n" +
                        "\t*FOURNISSEUR*\n3- Connexion compte professionnel\n4- Créer un compte\n" +
                        "------------------"
        );
        Scanner keyb = new Scanner(System.in);

        // Demande de input, login ou register
        while (true) {
            try {
                String option = keyb.nextLine();
                int optionInt = Integer.parseInt(option);
                if (optionInt <= 0 || optionInt >= 5) {
                    throw new Exception();
                } else return optionInt;
            } catch (Exception e) {
                System.out.print("Veuillez entrer une option valide SVP: ");
            }
        }
    }

    public void displayPageConnexion(){
        System.out.print("CONNEXION\n\nÀ tout moment, entrer \"0\" pour " +
                "retourner au menu principal.\nEntrez votre nom d'utilisateur: ");
    }

    public void displayPageInscriptionClient(){

    }

    public void displayPageInscriptionFournisseur(){

    }

}

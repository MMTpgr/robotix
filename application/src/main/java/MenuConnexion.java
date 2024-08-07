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

    /**
     * Affichage Page start
     *
     * @return int
     */
    public int displayPageStart(){
        System.out.println(
                "######### ROBOTIX Inc #########\n\n" + "--------------------\n" +
                        "\t*CLIENT*\n1- Connexion\n" + "2- Inscription\n---------------\n\n" +
                        "\t*FOURNISSEUR*\n3- Connexion compte professionnel\n4- Créer un compte\n\n" +
                        "(entrez '0' pour quitter)\n------------------"
        );
        Scanner keyb = new Scanner(System.in);

        // Demande de input, login ou register
        while (true) {
            try {
                String option = keyb.nextLine();
                int optionInt = Integer.parseInt(option);
                if (optionInt < 0 || optionInt >= 5) {
                    throw new Exception();
                } else return optionInt;
            } catch (Exception e) {
                System.out.print("Veuillez entrer une option valide SVP: ");
            }
        }
    }

    /**
     * Affichage page de connexion
     *
     */
    public void displayPageConnexion(){
        System.out.print("CONNEXION\n\nÀ tout moment, entrer \"0\" pour " +
                "retourner au menu principal.\nEntrez votre nom d'utilisateur: ");
    }


    /**
     * Affichage page inscription
     *
     * @param fourn fournisseur
     * @return Utilisateur
     */
    public Utilisateur displayPageInscription(boolean fourn){
        System.out.print("Choisissez un nom d'utilisateur: ");
        Scanner keyb = new Scanner(System.in);
        String username = keyb.nextLine();

        System.out.print("Choisissez un mot de passe: ");
        String password = keyb.nextLine();

        System.out.println("Bienvenue, " + username);
        if (!fourn){
            return new Client(username, password);
        } else {
            return new Fournisseur(username, password);
        }
    }
}

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Classe singleton responsable de l'affichage des menus et de la gestion des entrées utilisateur pour le Fournisseur.
 */
public class MenuFournisseur extends MenuUtilisateur{

    // Singleton
    private static MenuFournisseur _instance;

    /**
     * Obtient l'unique instance de MenuFournisseur.
     *
     * @return l'unique instance de MenuFournisseur
     */
    public static MenuFournisseur getInstance(){
        if (_instance == null){
            _instance = new MenuFournisseur();
        }
        return _instance;
    }

    private MenuFournisseur(){

    }

    /**
     * Affiche le menu principal pour le Fournisseur.
     *
     * @param user le Fournisseur
     * @return le choix de l'utilisateur sous forme de String
     */
    public String displayPagePrincipal(Fournisseur user) {
        String pick;
        Scanner scan = new Scanner(System.in);

        System.out.println("-------------------------");
        System.out.println("Bonjour " + user.getUsername() + "!");
        System.out.println("1 : Modifier son profil");
        System.out.println("2 : Gérer ses composantes");
        System.out.println("3 : Enregistrer une composante");
        System.out.println("- : Quitter");

        ArrayList<String> validStrings = new ArrayList<>(Arrays.asList("-"));

        pick = scan.nextLine();
        while (!_pickIsValid(pick, validStrings, 5)) {
            System.out.print("Veuillez entrer un choix valide: ");
            pick = scan.nextLine();
        }
        return pick;
    }

    /**
     * Affiche le menu de gestion du profil pour le Fournisseur.
     *
     * @param user le Fournisseur
     * @return le choix de l'utilisateur sous forme de String
     */
    public String displayPageProfil(Fournisseur user) {
        String pick;
        Scanner scan = new Scanner(System.in);

        System.out.println("-------------------------");
        System.out.println("Mon profil");
        System.out.println("1 : Modifier nom d'utilisateur");
        System.out.println("2 : Modifier mot de passe");
        System.out.println("3 : Modifier courriel");
        System.out.println("- : Revenir au menu principal");

        ArrayList<String> validStrings = new ArrayList<>(Arrays.asList("-"));

        pick = scan.nextLine();
        while (!_pickIsValid(pick, validStrings, 3)) {
            System.out.print("Veuillez entrer un choix valide: ");
            pick = scan.nextLine();
        }
        return pick;
    }

    /**
     * Affiche le menu de gestion des composantes pour le Fournisseur.
     *
     * @param user le Fournisseur
     * @return le choix de l'utilisateur sous forme de String
     */
    public String displayPageGestionComposante(Fournisseur user){
        String pick;
        Scanner scan = new Scanner(System.in);

        System.out.println("-------------------------");
        System.out.println("Gérer mes composantes");
        System.out.println("1 : Afficher mes composantes");
        System.out.println("2 : Supprimer une composante");
        System.out.println("3 : Modifier mes composantes");
        System.out.println("- : Revenir au menu principal");

        ArrayList<String> validStrings = new ArrayList<>(Arrays.asList("-"));

        pick = scan.nextLine();
        while (!_pickIsValid(pick, validStrings, 3)) {
            System.out.print("Veuillez entrer un choix valide: ");
            pick = scan.nextLine();
        }
        return pick;

    }

    /**
     * Affiche le menu d'enregistrement de composante pour le Fournisseur.
     *
     * @param user le Fournisseur
     * @return le choix de l'utilisateur sous forme de String
     */
    public String displayPageEnregisterComposante(Fournisseur user){
        String pick;
        Scanner scan = new Scanner(System.in);

        System.out.println("-------------------------");
        System.out.println("Enregistrer une composante");
        System.out.println("1 : Enregistrer une nouvelle composante");
        System.out.println("- : Quitter");

        ArrayList<String> validStrings = new ArrayList<>(Arrays.asList("-"));

        pick = scan.nextLine();
        while (!_pickIsValid(pick, validStrings, 1)) {
            System.out.print("Veuillez entrer un choix valide: ");
            pick = scan.nextLine();
        }
        return pick;

    }


    /**
     * Vérifie si le choix de l'utilisateur est valide selon le contexte du menu.
     *
     * @param pick le choix de l'utilisateur
     * @param validStrings liste des chaînes valides selon le contexte
     * @param maxIndex index maximal de choix valide selon le contexte (commençant à '1')
     * @return vrai si le choix est valide, faux sinon
     */
    private boolean _pickIsValid(String pick, ArrayList<String> validStrings, int maxIndex){

        boolean validNumber = false;
        boolean validString = validStrings.contains(pick);

        try {
            validNumber = Integer.parseInt(pick) > 0 && Integer.parseInt(pick) <= maxIndex;
        } catch (NumberFormatException e) {
        }

        return (validString || validNumber);

    }
}

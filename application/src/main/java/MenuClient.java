import java.util.ArrayList;
import java.util.Scanner;


public class MenuClient {

    // Singleton
    private static MenuClient _instance;

    public static MenuClient getInstance(){
        if (_instance == null){
            _instance = new MenuClient();
        }
        return _instance;
    }


        public String displayPagePrincipal(Client user) {

        String pick;
        Scanner scan = new Scanner(System.in);

        System.out.println("\n\n\t-------------------------\n" +
                "\t- Bonjour " + user.getUsername() + "!" +
                "\n\t-\n\t- 1. Voir ma flotte\n\t- 2. Activités\n\t- 3. MarketPlace" +
                "\n\t- 4. Trouver des intérêts\n\t- 5. Parcourir des utilisateurs" +
                "\n\t- 6. Se déconnecter" +
                "\t\n\t( Vos points: " + user.points + " - Votre classement: 1er )" +
                "\n\t-------------------------");

        pick = scan.nextLine();
        while (Integer.parseInt(pick) < 1 || Integer.parseInt(pick) > 6) {
            System.out.print("Veuillez entrer un choix valide: ");
            pick = scan.nextLine();
        }

        return pick;
    }

    public void displayPageFlotte(Utilisateur user) {

    }

    public void displayPageEnregistrerRobot(){

    }


    public void displayPageSupprimerRobot(){

    }

    // -------------------------- Activite --------------------------

    public String displayPageActivite(Client user) {

        String pick;
        Scanner scan = new Scanner(System.in);


        System.out.println("\t-\n\t-------------------------\n\t- 1. Mes activités" +
                "\n\t- 2. Rechercher une activité\n\t- 3. Quitter");

        pick = scan.nextLine();
        while (!pick.equals("1") && !pick.equals("2") && !pick.equals("3")) {
            System.out.print("Veuillez entrer un choix valide: ");
            pick = scan.nextLine();
        }
        return pick;
    }


    public String displayPageRechercheActivite(ArrayList<Activite> activites){

    int activiteIndex = 0;

        System.out.println("-------------------------");
        System.out.println("Filter by Name: '!'");
        System.out.println("Filter by Date: '#'");
        System.out.println("Filter by Popularite: '*'");
        System.out.println("Quitter: '`'");
        System.out.println();
        for (Activite activite : activites){
            activiteIndex += 1;
            System.out.println(activiteIndex + "- " + activite.getName());
        }

        String pick;
        Scanner scan = new Scanner(System.in);

        pick = scan.nextLine();
        while ((!pick.equals("!") && !pick.equals("#") && !pick.equals("*") && !pick.equals("-") && !pick.equals("`"))
                 || (Integer.parseInt(pick) < 1 && Integer.parseInt(pick) > activiteIndex)) {
            System.out.print("Veuillez entrer un choix valide: ");
            pick = scan.nextLine();
        }
        return pick;

    }

    public String displayPageFicheActivite(Activite activite, boolean alreadySubscribed){

        String composanteRequise = "";
        for (ComposanteType ct : activite.getComposantesRequises()){
            composanteRequise += " " + ct.name();
        }

        String interets = "";
        for (Interet interet : activite.getInteretsConcernes()){
            interets += " " + interet.getNom();
        }

        String participants = "";
        for (String client : activite.getParticipants()){
            participants += " " + client;
        }

        System.out.println("-------------------------");
        System.out.println("Name: " + activite.getName());
        System.out.println("Description: " + activite.getDesc());
        System.out.println("Date: " + activite.getDate().toString());
        System.out.println("Participants: " + participants);
        System.out.println("Popularite: " + activite.getPopularite());
        System.out.println("Host: " + activite.getHost());
        System.out.println("Composantes Requises: " + composanteRequise);
        System.out.println("Interets: " + interets);
        System.out.println();
        if (alreadySubscribed){
            System.out.println("1- Desinscription");
        } else {
            System.out.println("1- Inscription");
        }
        System.out.println("2- Quitter");

        String pick;
        Scanner scan = new Scanner(System.in);
        pick = scan.nextLine();
        while (!pick.equals("1") && !pick.equals("2")) {
            System.out.print("Veuillez entrer un choix valide: ");
            pick = scan.nextLine();
        }

        return pick;

    }

    public void displayPagecomposanteManquantes(Activite activite){

        System.out.println("-------------------------");
        System.out.println("!!! Inscription Echoue !!!");
        System.out.println("Aucun de vos robots ne possede une composante requise:");
        String composanteRequises = "";
        for (ComposanteType composanteType : activite.getComposantesRequises()){
            composanteRequises += " " + composanteType.name();
        }
        System.out.println(composanteRequises);
    }


    // -------------------------- MARKETPLACE --------------------------

    public void displayPageMarket(Utilisateur user) {

    }

    public void displayPageFicheFournisseur(){

    }

    public void displayPageRechercherFournisseur() {
    }

    public void displayPageRechercheComposante(){

    }

    public void displayPageFicheComposante(){

    }


    // -------------------------- UTILISATEURS --------------------------

    public void displayPageFicheUtilisateur(){

    }

    public void displayPageRechercherUtilisateur(){

    }

    public void displayPageModifierSonProfil(){

    }

    public void displayPageNotifications(){

    }


}

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class MenuClient extends MenuUtilisateur{

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


        System.out.println("-------------------------");
        System.out.println("1 : Mes activités");
        System.out.println("2 : Rechercher une activité");
        System.out.println("'-' : Quitter");

        ArrayList<String> validStrings = new ArrayList<>(Arrays.asList("-"));

        pick = scan.nextLine();
        while (!_pickIsValid(pick, validStrings, 2)) {
            System.out.print("Veuillez entrer un choix valide: ");
            pick = scan.nextLine();
        }
        return pick;
    }


    public String displayPageRechercheActivite(ArrayList<Activite> activites){

        System.out.println(activites.get(0));

        int activiteIndex = 0;

        System.out.println("-------------------------");
        System.out.println("'!' : Filter by Name");
        System.out.println("'#' : Filter by Date");
        System.out.println("'*' : Filter by Popularite");
        System.out.println("'-' : Quitter");
        System.out.println();
        for (Activite activite : activites){
            activiteIndex += 1;
            System.out.println(activiteIndex + " : " + activite.getName());
        }

        String pick;
        Scanner scan = new Scanner(System.in);
        ArrayList<String> validStrings = new ArrayList<>(Arrays.asList("!", "#", "*", "-"));

        pick = scan.nextLine();

        while (!_pickIsValid(pick, validStrings, activiteIndex)){
            System.out.println("Veuillez entrer un choix valide: ");
            pick = scan.nextLine();
        }

        return pick;

    }

    public String displayPageFicheActivite(Activite activite, boolean alreadySubscribed){

        String composanteRequise = "";
        for (ComposanteType ct : activite.getComposantesRequises()){
            composanteRequise +=  ct.name() + " ";
        }

        String participants = "";
        for (String client : activite.getParticipants()){
            participants += client + " ";
        }

        String robots = "";
        for (String robot : activite.getRobotsInclus()){
            participants += robot + " ";
        }

        System.out.println("-------------------------");
        System.out.println("Name: " + activite.getName());
        System.out.println("Description: " + activite.getDesc());
        System.out.println("Date: " + activite.getDate().toString());
        System.out.println("Participants: " + participants);
        System.out.println("Robots: " + robots);
        System.out.println("Popularite: " + activite.getPopularite());
        System.out.println("Host: " + activite.getHost());
        System.out.println("Composantes Requises: " + composanteRequise);
        System.out.println("État: " + activite.getEtat().name());
        System.out.println();
        if (alreadySubscribed){
            System.out.println("1 : Desinscription");
        } else {
            System.out.println("1 : Inscription");
        }
        System.out.println("'-' : Quitter");

        String pick;
        Scanner scan = new Scanner(System.in);
        pick = scan.nextLine();
        ArrayList<String> validStrings = new ArrayList<>(Arrays.asList("-"));

        while (!_pickIsValid(pick, validStrings, 1)) {
            System.out.print("Veuillez entrer un choix valide: ");
            pick = scan.nextLine();
        }

        return pick;

    }

    public void displayPagecomposanteManquantes(Activite activite){

        System.out.println("-------------------------");
        System.out.println("!!! Inscription Echoué !!!");
        System.out.println("Aucun de vos robots ne possede une composante requise:");
        String composanteRequises = "";
        for (ComposanteType composanteType : activite.getComposantesRequises()){
            composanteRequises += " " + composanteType.name();
        }
        System.out.println(composanteRequises);
    }


    // -------------------------- MARKETPLACE --------------------------

    public String displayPageMarket(Utilisateur user) {

        String pick;
        Scanner scan = new Scanner(System.in);

        System.out.println("-------------------------");
        System.out.println("1 : Rechercher une Composante");
        System.out.println("2 : Rechercher un Fournisseur");
        System.out.println("'-' : Quitter");

        ArrayList<String> validStrings = new ArrayList<>(Arrays.asList("-"));

        pick = scan.nextLine();
        while(!_pickIsValid(pick, validStrings, 2)){
            System.out.println("Veuillez entrer un choix valide: ");
            pick = scan.nextLine();
        }

        return pick;

    }

    public String displayPageFicheFournisseur(Fournisseur fournisseur){

        String pick;
        Scanner scan = new Scanner(System.in);

        String typeComposante = "";
        for (ComposanteType composanteType : fournisseur.getTypesComposantes()){
            typeComposante += composanteType.name() + " ";
        }

        System.out.println("-------------------------");
        System.out.println("'+' : Afficher Ses Composantes");
        System.out.println("'-' : Quitter");
        System.out.println();
        System.out.println("Nom: " + fournisseur.getUsername());
        System.out.println("Visites: " + fournisseur.getVisites());
        System.out.println("Types de Composantes: " + typeComposante);

        ArrayList<String> validStrings = new ArrayList<>(Arrays.asList("-", "+"));

        pick = scan.nextLine();
        while(!_pickIsValid(pick, validStrings, 0)){
            System.out.println("Veuillez entrer un choix valide: ");
            pick = scan.nextLine();
        }

        return pick;

    }

    public String displayPageRechercherFournisseur(ArrayList<Fournisseur> fournisseurs) {

        String pick;
        Scanner scan = new Scanner(System.in);
        int maxIndex = fournisseurs.size();

        System.out.println("-------------------------");
        System.out.println("'!' : Filter by Name");
        System.out.println("'#' : Filter by Type de Composantes");
        System.out.println("'-' : Quitter");
        System.out.println();
        int index = 1;
        String composantes;
        for(Fournisseur fournisseur : fournisseurs){
            composantes = "";
            for (ComposanteType composanteType : fournisseur.getTypesComposantes()){
                composantes += composanteType.name() + " ";
            }
            System.out.println(index + " : " + fournisseur.getUsername() + " (" + composantes + ")");
            index += 1;
        }

        pick = scan.nextLine();
        ArrayList<String> validStrings = new ArrayList<>(Arrays.asList("-", "!", "#"));

        while (!_pickIsValid(pick, validStrings, maxIndex)){
            System.out.print("Veuillez entrer un choix valide: ");
            pick = scan.nextLine();
        }

        return pick;

    }

    public String displayPageRechercheComposante(ArrayList<Composante> composantes){

        String pick;
        Scanner scan = new Scanner(System.in);
        int maxIndex = composantes.size();

        System.out.println("-------------------------");
        System.out.println("'!' : Filter by Name");
        System.out.println("'#' : Filter by Type");
        System.out.println("'*' : Filter by Fournisseur");
        System.out.println("'-' : Quitter");
        System.out.println();
        int index = 1;
        for(Composante composante : composantes){
            System.out.println(index + " : " + composante.getNom() + " (" + composante.getType().name() + ")");
            index += 1;

        }

        pick = scan.nextLine();
        ArrayList<String> validStrings = new ArrayList<>(Arrays.asList("-", "!", "#", "*"));

        while (!_pickIsValid(pick, validStrings, maxIndex)){
            System.out.print("Veuillez entrer un choix valide: ");
            pick = scan.nextLine();
        }

        return pick;
    }

    public String displayPageFicheComposante(Composante composante){

        String pick;
        Scanner scan = new Scanner(System.in);

        System.out.println("-------------------------");
        System.out.println("'-' : Quitter");
        System.out.println();
        System.out.println("Nom: " + composante.getNom());
        System.out.println("Type: " + composante.getType());


        pick = scan.nextLine();
        ArrayList<String> validStrings = new ArrayList<>(Arrays.asList("-"));

        while (!_pickIsValid(pick, validStrings, 0)){
            System.out.print("Veuillez entrer un choix valide: ");
            pick = scan.nextLine();
        }

        return pick;

    }


    public String displayPageFicheAchatComposante(Composante composante){

        String pick;
        Scanner scan = new Scanner(System.in);

        System.out.println("-------------------------");
        System.out.println("'+' : Acheter");
        System.out.println("'-' : Quitter");
        System.out.println();
        System.out.println("Nom: " + composante.getNom());
        System.out.println("Type: " + composante.getType());
        System.out.println("Prix: " + composante.getPrix());
        System.out.println("Fournisseur: " + composante.getFournisseur().getUsername());


        pick = scan.nextLine();
        ArrayList<String> validStrings = new ArrayList<>(Arrays.asList("-", "+"));

        while (!_pickIsValid(pick, validStrings, 0)){
            System.out.print("Veuillez entrer un choix valide: ");
            pick = scan.nextLine();
        }

        return pick;

    }

    public void displayPageAchatFondInsuffisant(Composante composante, Client client){

        System.out.println("-------------------------");
        System.out.println("!!! Achat Echoué !!!");
        System.out.println("Fond de l'utilisateur insuffisant:");
        System.out.println("Prix de la composante: " + composante.getPrix() + "$.");
    }


    // -------------------------- UTILISATEURS --------------------------

    public void displayPageFicheUtilisateur(){

    }

    public void displayPageRechercherUtilisateur(){

    }

    public void displayPageNotifications(){

    }


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

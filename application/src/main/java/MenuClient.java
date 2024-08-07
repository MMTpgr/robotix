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

    private MenuClient(){

    }

    /**
     * Affichage du menu principal d'un client.
     * Le client peut choisir de voir sa flottes, ses activites, marketplace ou
     * de voir ses notifications
     *
     * @param user Le client.
     * @return Choix du client.
     */
    public String displayPagePrincipal(Client user) {

        String pick;
        Scanner scan = new Scanner(System.in);

        System.out.println("-------------------------");
        System.out.println("Bonjour " + user.getUsername() + "!");
        System.out.println("1 : Voir ma flotte");
        System.out.println("2 : Activités");
        System.out.println("3 : MarketPlace");
        System.out.println("4 : Voir Notifications");
        System.out.println("5 : Modifier mon nom d'utilisateur");
        System.out.println("6 : Modifier mon mot de passe");
        System.out.println("- : Quitter");

        ArrayList<String> validStrings = new ArrayList<>(Arrays.asList("-"));

        pick = scan.nextLine();
        while (!_pickIsValid(pick, validStrings, 6)) {
            System.out.print("Veuillez entrer un choix valide: ");
            pick = scan.nextLine();
        }

        return pick;
    }


    /**
     * Affichage de la page principal de la flotte du client d'activités.
     * Le client peut choisir d'ajouter/supprimer un robot a sa flotte, afficher etat de ses robot.
     * ou de retourner au menu principal
     * @param user Le client.
     * @return Choix du client.
     */
    public String displayPageFlotte(Utilisateur user) {

        String pick;
        Scanner scan = new Scanner(System.in);

        System.out.println("-------------------------");
        System.out.println("Ma flotte");
        System.out.println("1 : Ajouter un robot à la flotte");
        System.out.println("2 : Supprimer un robot à la flotte");
        System.out.println("3 : Afficher l'état des robot");
        System.out.println("- : Quitter");

        ArrayList<String> validStrings = new ArrayList<>(Arrays.asList("-"));


        pick = scan.nextLine();
        while (!_pickIsValid(pick, validStrings, 4)) {
            System.out.print("Veuillez entrer un choix valide: ");
            pick = scan.nextLine();
        }

        return pick;


    }


    // -------------------------- Activite --------------------------

    /**
     * Affichage de la page principal d'activités.
     * Le client peut choisir de voir ses activités ou les activités de la base de données.
     * * Le client peux faire retour arrière.
     *
     * @param user Le client.
     * @return Choix du client.
     */
    public String displayPageActivite(Client user) {

        String pick;
        Scanner scan = new Scanner(System.in);


        System.out.println("-------------------------");
        System.out.println("1 : Mes activités");
        System.out.println("2 : Rechercher une activité");
        System.out.println("- : Quitter");

        ArrayList<String> validStrings = new ArrayList<>(Arrays.asList("-"));

        pick = scan.nextLine();
        while (!_pickIsValid(pick, validStrings, 2)) {
            System.out.print("Veuillez entrer un choix valide: ");
            pick = scan.nextLine();
        }
        return pick;
    }

    /**
     * Affichage de la page de recherche d'activités.
     * Le client peux ordonner les activité selon plusieurs filtre.
     * * Le client peux faire retour arrière.
     * Le client peux choisir une activité, sa fiche sera affiché.
     *
     * @param activites Liste d'activité à afficher.
     * @return Choix du client.
     */
    public String displayPageRechercheActivite(ArrayList<Activite> activites){

        int activiteIndex = 0;

        System.out.println("-------------------------");
        System.out.println("! : Filter by Name");
        System.out.println("# : Filter by Date");
        System.out.println("* : Filter by Popularite");
        System.out.println("- : Quitter");
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

    /**
     * Affichage d'une fiche d'activité.
     * Le client peux s'inscrire ou se désinscrire de l'activité
     * Le client peux faire retour arrière.
     *
     * @param activite Activité duquelle afficher la fiche.
     * @param alreadySubscribed Si le client est deja abonné ou non.
     * @return Choix du client.
     */
    public String displayPageFicheActivite(Activite activite, boolean alreadySubscribed){

        String composanteRequise = "";
        for (String ct : activite.getComposantesRequises()){
            composanteRequise +=  ct + " ";
        }

        String participants = "";
        for (Client client : activite.getParticipants()){
            participants += client.getUsername() + " ";
        }

        String robots = "";
        for (Robot robot : activite.getRobotsInclus()){
            participants += robot.getNom() + " ";
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

        System.out.println("- : Quitter");

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

    /**
     * Affichage avertissement, le client de possede pas de robots valide.
     *
     * @param activite activité
     */
    public void displayPagecomposanteManquantes(Activite activite){

        System.out.println("-------------------------");
        System.out.println("!!! Inscription Echoué !!!");
        System.out.println("Aucun de vos robots ne possede une composante requise:");
        String composanteRequises = "";
        for (String composanteType : activite.getComposantesRequises()){
            composanteRequises += " " + composanteType;
        }
        System.out.println(composanteRequises);
    }


    // -------------------------- MARKETPLACE --------------------------

    /**
     * Affichage de la page principal du MarketPlace.
     * Le client peut afficher le menu de recherche de composante.
     * * Le client peut afficher le menu de recherche de fournisseur.
     * * Le client peut faire retour arrière.
     *
     * @param user Le Client.
     * @return Choix du client.
     */
    public String displayPageMarket(Utilisateur user) {

        String pick;
        Scanner scan = new Scanner(System.in);

        System.out.println("-------------------------");
        System.out.println("1 : Rechercher une Composante");
        System.out.println("2 : Rechercher un Fournisseur");
        System.out.println("- : Quitter");

        ArrayList<String> validStrings = new ArrayList<>(Arrays.asList("-"));

        pick = scan.nextLine();
        while(!_pickIsValid(pick, validStrings, 2)){
            System.out.println("Veuillez entrer un choix valide: ");
            pick = scan.nextLine();
        }

        return pick;

    }

    /**
     * Affichage d'une fiche d'un fournisseur.
     * Le client peut afficher ses composantes.
     * Le client peut faire retour arrière.
     *
     * @param fournisseur Fournisseur duquel afficher la fiche.
     * @return Choix du Client
     */
    public String displayPageFicheFournisseur(Fournisseur fournisseur){

        String pick;
        Scanner scan = new Scanner(System.in);

        String typeComposante = "";
        for (ComposanteType composanteType : fournisseur.getTypesComposantes()){
            typeComposante += composanteType.name() + " ";
        }

        System.out.println("-------------------------");
        System.out.println("+ : Afficher Ses Composantes");
        System.out.println("- : Quitter");
        System.out.println();
        System.out.println("Nom: " + fournisseur.getUsername());
        System.out.println("Types de Composantes: " + typeComposante);

        ArrayList<String> validStrings = new ArrayList<>(Arrays.asList("-", "+"));

        pick = scan.nextLine();
        while(!_pickIsValid(pick, validStrings, 0)){
            System.out.println("Veuillez entrer un choix valide: ");
            pick = scan.nextLine();
        }

        return pick;

    }

    /**
     * Affichage page de recherche de fournisseur.
     *
     * Le client peux ordonner les fournisseurs selon plusieurs filtre.
     * Le client peux faire retour arrière.
     * Le client peux choisir un fournisseur, sa fiche sera affiché.
     *
     * @param fournisseurs Liste de fournisseur à afficher.
     * @return Choix du client.
     */
    public String displayPageRechercherFournisseur(ArrayList<Fournisseur> fournisseurs) {

        String pick;
        Scanner scan = new Scanner(System.in);
        int maxIndex = fournisseurs.size();

        System.out.println("-------------------------");
        System.out.println("! : Filter by Name");
        System.out.println("# : Filter by Type de Composantes");
        System.out.println("- : Quitter");
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

    /**
     * Affichage page de recherche de Composantes.
     *
     * Le client peux ordonner les composantes selon plusieurs filtre.
     * Le client peux faire retour arrière.
     * Le client peux choisir une composante, sa fiche sera affiché.
     *
     * @param composantes Liste de composante à afficher.
     * @return Choix du client.
     */
    public String displayPageRechercheComposante(ArrayList<Composante> composantes){

        String pick;
        Scanner scan = new Scanner(System.in);
        int maxIndex = composantes.size();

        System.out.println("-------------------------");
        System.out.println("! : Filter by Name");
        System.out.println("# : Filter by Type");
        System.out.println("* : Filter by Fournisseur");
        System.out.println("- : Quitter");
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

    /**
     * Affichage d'une fiche d'une composante.
     * Le client peut faire retour arrière.
     *
     * @param composante Composante duquelle afficher la fiche.
     * @return Choix du client.
     */
    public String displayPageFicheComposante(Composante composante){

        String pick;
        Scanner scan = new Scanner(System.in);

        System.out.println("-------------------------");
        System.out.println("- : Quitter");
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

    /**
     * Affichage d'une fiche d'une composante pour un achat.
     * Le client peut acheter la composante.
     * Le client peut faire retour arrière.
     *
     * @param composante Composante duquelle afficher la fiche.
     * @return Choix du client.
     */
    public String displayPageFicheAchatComposante(Composante composante){

        String pick;
        Scanner scan = new Scanner(System.in);

        System.out.println("-------------------------");
        System.out.println("+ : Acheter");
        System.out.println("- : Quitter");
        System.out.println();
        System.out.println("Nom: " + composante.getNom());
        System.out.println("Type: " + composante.getType());
        System.out.println("Prix: " + composante.getPrix());
        System.out.println("Fournisseur: " + composante.getFournisseur());

        pick = scan.nextLine();
        ArrayList<String> validStrings = new ArrayList<>(Arrays.asList("-", "+"));

        while (!_pickIsValid(pick, validStrings, 0)){
            System.out.print("Veuillez entrer un choix valide: ");
            pick = scan.nextLine();
        }

        return pick;

    }


    // -------------------------- UTILISATEURS --------------------------

    /**
     * Affichage Page des notifications
     *
     * @param user Le client.
     */
    public void displayPageNotifications(Utilisateur user){
        System.out.println("-- VOS NOTIFICATIONS --");
        for (Notification notif : user.notifications){
            System.out.println(notif);
        }
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

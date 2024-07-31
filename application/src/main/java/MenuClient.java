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

        System.out.println("-------------------------");
        System.out.println("Bonjour " + user.getUsername() + "!");
        System.out.println("1 : Voir ma flotte");
        System.out.println("2 : Activités");
        System.out.println("3 : MarketPlace");
        System.out.println("4 : Trouver des intérêts");
        System.out.println("5 : Parcourir des utilisateurs");
        System.out.println("- : Quitter");

        ArrayList<String> validStrings = new ArrayList<>(Arrays.asList("-"));

        pick = scan.nextLine();
        while (!_pickIsValid(pick, validStrings, 5)) {
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

    /**
     * Affichage de la page principal d'activités.
     * Le client pour choisir de voir ses activités ou les activités de la base de données.
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

        System.out.println(activites.get(0));

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
        for (ComposanteType composanteType : activite.getComposantesRequises()){
            composanteRequises += " " + composanteType.name();
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
        System.out.println("Fournisseur: " + composante.getFournisseur().getUsername());


        pick = scan.nextLine();
        ArrayList<String> validStrings = new ArrayList<>(Arrays.asList("-", "+"));

        while (!_pickIsValid(pick, validStrings, 0)){
            System.out.print("Veuillez entrer un choix valide: ");
            pick = scan.nextLine();
        }

        return pick;

    }

    /**
     * Affichage avertissement, fond insuffisant pour achat d'une composante.
     *
     * @param composante Composante à acheter.
     * @param client Le client.
     */
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

    /**
     * Vérifie se le choix du client est valide selon le context du menu.
     *
     * @param pick Le choix du client.
     * @param validStrings List de String valide selon le context.
     * @param maxIndex index maximal de choix valide selon le context. (starting at '1')
     * @return Si le choix est valide ou non.
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

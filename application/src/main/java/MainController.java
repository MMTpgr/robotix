import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MainController {


    // Singleton
    private static MainController _instance;

    public static MainController getInstance(){
        if (_instance == null){
            _instance = new MainController();
        }
        return _instance;
    }

    private MainController(){

    }


    // -------------------------- Controller and Repositories --------------------------

    private final ActiviteController activiteController =  ActiviteController.getInstance();
    private final ActiviteRepository activiteRepository = activiteController.getRepository();
    private final ClientController clientController =  ClientController.getInstance();
    private final ClientRepository clientRepository = ClientController.getRepository();
    private final FournisseurController fournisseurController = FournisseurController.getInstance();
    private final FournisseurRepository fournisseurRepository = fournisseurController.getRepository();
    private final  ComposanteController composanteController =  ComposanteController.getInstance();
    private final ComposanteRepository composanteRepository = composanteController.getRepository();
    private FlotteController flotteController;
    private final MenuConnexion menu = MenuConnexion.getInstance();
    protected final MenuClient menuClient = MenuClient.getInstance();
    //MenuFournisseur
    MenuFournisseur menuFournisseur = MenuFournisseur.getInstance();

    // -------------------------- START --------------------------

    protected Utilisateur currentUser = null;

    public Utilisateur getCurrentUser(){
        return this.currentUser;
    }

    public void start(){

        // Connexion or login here
        int choixConnexion = menu.displayPageStart();

        switch (choixConnexion){
            case 0:
                // Sauvegarder les données, puis quitter
                fournisseurRepository.writeFournisseurs();
                activiteRepository.writeActivites();
                clientRepository.writeClient();
                System.exit(0);
            case 1:
            case 3:
                connexion();
                break;
            case 2:
                currentUser = menu.displayPageInscription(false);
                clientRepository.getClients().add((Client) currentUser);
                break;
            default:
                currentUser = menu.displayPageInscription(true);
                fournisseurRepository.getFournisseurs().add((Fournisseur) currentUser);
                break;
        }

        if (currentUser instanceof Client){
            // Affiche le menu principal du client
            menuPrincipalClient((Client) currentUser);
        } else {
            // Affiche le menu principal du fournisseur
            menuPrincipalFournisseur((Fournisseur) currentUser);
        }
    }


    // -------------------------- CONNEXION/SIGN-IN --------------------------

    /**
     * Connexion a un compte Robotix.
     * Client ou Fournisseur
     */
    public void connexion(){
        boolean validUser = false;
        boolean validPassword = false;

        menu.displayPageConnexion();

        Scanner keyb = new Scanner(System.in);

        // Demander username
        while (!validUser){
            String username = keyb.nextLine();

            // Si utilisateur veux quitter
            if (username.equals("0")){
                currentUser = null;
                start();
            }

            // Chercher à travers les users
            for (Client client : clientRepository.getClients()){
                if (client.getUsername().equals(username)){
                    validUser = true;
                    currentUser = client;
                }
            }

            // Chercher à travers les fournisseurs
            for (Fournisseur fourn : fournisseurRepository.getFournisseurs()){
                if (fourn.getUsername().equals(username)){
                    validUser = true;
                    currentUser = fourn;
                }
            }
        }

        while (!validPassword){
            System.out.print("Mot de passe: ");
            String password = keyb.nextLine();

            if (password.equals("0")){
                currentUser = null;
                start();
            }

            if (!currentUser.getPassword().equals(password)){
                System.out.print("\nMot de passe erroné.");
            } else {validPassword = true;}
        }

    }

    // -------------------------- Client --------------------------
    // Version pour fournisseur vers le milieu / fin de ce document

    /**
     * Logique du menu Principal d'utilisateur
     * Selon le choix du client, menu flotte, menu activites, menu marketplace, afficher les notifications
     * ou retour au menu de connexion.
     * @param client
     */
    public void menuPrincipalClient(Client client){

        String pick = menuClient.displayPagePrincipal(client);

        switch (pick) {
            case "1":
                MenuFlotte(client);
                break;
            case "2":
                this.menuActivitesPrincipal(client);
                break;
            case "3":
                menuMarketPlacePrincipal(client);
                break;
            case "4":
                menuClient.displayPageNotifications(client);
                menuPrincipalClient(client);
                break;
            case "5":
                menuClient.displayPageModifierSonProfil(client, "username");
                menuPrincipalClient(client);
                break;
            case "6":
                menuClient.displayPageModifierSonProfil(client, "password");
                menuPrincipalClient(client);
            case "-":
                this.start();

        }

    }


    // -------------------------- MarketPlace --------------------------

    /**
     * Logique du menu MarketPlace.
     * Selon le choix du client, le menu de recherche de composantes/fournisseurs sera atteint ou
     * retour au menu principal.
     *
     * @param client Le client.
     */
    public void menuMarketPlacePrincipal(Client client){

        String pick = menuClient.displayPageMarket(client);
        ArrayList<Composante> composantes;
        ArrayList<Fournisseur> fournisseurs;

        switch (pick){
            case "1":
                composantes = composanteRepository.getComposantes();
                this.menuRechercheComposante(client, composantes, null);
            case "2":
                fournisseurs = fournisseurRepository.getFournisseurs();
                this.menuRechercheFournisseurs(client, fournisseurs);
            case "-":
                this.menuPrincipalClient(client);
        }
    }

    /**
     * Logique du menu de Recherche de Composante.
     * Selon le choix du client, affichage de la fiche d'une activité, filtrage
     * des activités ou retour au menu de recherche de composantes.
     *
     * @param client Le client
     * @param composantes Liste de Composante à afficher/filtrer.
     */
    public void menuRechercheComposante(Client client, ArrayList<Composante> composantes, Fournisseur fournisseur){

        String pick = menuClient.displayPageRechercheComposante(composantes);


        switch (pick){

            case "!":
                Composante.filterComposantes(composantes, ComposanteFilter.NAME);
                this.menuRechercheComposante(client, composantes, fournisseur);
            case "#":
                Composante.filterComposantes(composantes, ComposanteFilter.TYPE);
                this.menuRechercheComposante(client, composantes, fournisseur);
            case "*":
                Composante.filterComposantes(composantes, ComposanteFilter.FOURNISSEUR);
                this.menuRechercheComposante(client, composantes, fournisseur);
            case "-":
                this.menuMarketPlacePrincipal(client);
            default:
                Composante choosenComposante = composantes.get(Integer.parseInt(pick)-1);

                // For now checking if this is a sold composante with composante.getFournisseur()
                if (choosenComposante.getFournisseur() == null){
                    this.menuFicheComposante(choosenComposante);
                } else {
                    this.menuFicheAchatComposante(choosenComposante);
                }
                if (fournisseur != null){
                    menuRechercheComposante(client, fournisseur.getComposantes(), fournisseur);
                } else {
                    menuRechercheComposante(client, composanteRepository.getComposantes(), fournisseur);
                }
        }

    }

    /**
     * Logique d'une fiche d'une composante.
     *
     * @param composante Composante duqelle afficher la fiche.
     */
    public void menuFicheComposante(Composante composante){
        menuClient.displayPageFicheComposante(composante);
    }

    /**
     * Logique d'une fiche d'achat d'une composante.
     *
     * @param composante Composante duqelle afficher la fiche.
     */
    public void menuFicheAchatComposante(Composante composante){

        String pick = menuClient.displayPageFicheAchatComposante(composante);

        switch (pick){
            case "+":
            composanteController.achatComposante((Client) this.currentUser, composante);
            case "-":
                return;
        }
    }

    /**
     * Logique du menu de recherche de Fournisseurs.
     * Selon le choix du client, afficher la fiche d'un fournisseur, filtrage
     * des fournisseurs ou retour au menu MarketPlace.
     *
     * @param client Le client.
     * @param fournisseurs Liste de Fournisseur à afficher/filtrer.
     */
    public void menuRechercheFournisseurs(Client client, ArrayList<Fournisseur> fournisseurs){

        String pick = menuClient.displayPageRechercherFournisseur(fournisseurs);

        switch (pick){

            case "!":
                Fournisseur.filterFournisseurs(fournisseurs, FournisseurFilter.NOM);
                menuRechercheFournisseurs(client, fournisseurs);
            case "#":
                Fournisseur.filterFournisseurs(fournisseurs, FournisseurFilter.TYPECOMPOSANTES);
                menuRechercheFournisseurs(client, fournisseurs);
            case "-":
                this.menuMarketPlacePrincipal(client);
            default:
                Fournisseur choosenFournisseur = fournisseurs.get(Integer.parseInt(pick)-1);
                this.menuFicheFournisseur(client, choosenFournisseur);
                menuRechercheFournisseurs(client,  fournisseurs);
        }
    }

    /**
     * Logique d'une fiche d'un fournisseur.
     * Selon le choix du client, afficher les composantes du founisseur ou retour
     * au menu de recherche de fournisseur.
     *
     * @param client Le client
     * @param fournisseur Fournisseur duquel afficher la fiche.
     */
    public void menuFicheFournisseur(Client client, Fournisseur fournisseur){

        String pick = menuClient.displayPageFicheFournisseur(fournisseur);

        switch (pick) {
            case "+":
                this.menuRechercheComposante(client, fournisseur.getComposantes(), fournisseur);
                this.menuFicheFournisseur(client, fournisseur);
            case "-":
        }
    }



    // -------------------------- Activite --------------------------

    /**
     * Logique du menu principal des activités.
     * Selon le choix du client, afficher le menu de recherche d'activité du repertoir/des
     * activités du client ou retour au menu principal.
     *
     * @param client le Client.
     */
    public void menuActivitesPrincipal(Client client){

        String pick = menuClient.displayPageActivite(client);
        switch (pick){

            // ---------------- Mes Activites ----------------
            case "1":
                this.menuRechercheActivites(client, activiteController.activitesNametoList(client.getActivites()), true);
            // ---------------- Toutes Activites ----------------
            case "2":
                this.menuRechercheActivites(client,
                        activiteRepository.getActivites(), false);
            case "-":
                this.menuPrincipalClient(client);
        }
        }

    /**
     * Logique du menu de recherche d'activité.
     * Selon le choix du client, affichage d'une fiche d'activité, filtrage ou
     * retour au menu principal des activités.
     *
     * @param client
     * @param activites
     */
    public void menuRechercheActivites(Client client, ArrayList<Activite> activites, boolean clientActivite){

        String pick;

        pick = menuClient.displayPageRechercheActivite(activites);

        switch (pick){
            // ---- FILTERS ----
            case "!":
                Activite.sortActivites(activites, ActiviteFilter.NOM);
                this.menuRechercheActivites(client, activites, clientActivite);
            case "#":
                Activite.sortActivites(activites, ActiviteFilter.DATE);
                this.menuRechercheActivites(client, activites, clientActivite);
            case "*":
                Activite.sortActivites(activites, ActiviteFilter.POPULARITE);
                this.menuRechercheActivites(client, activites, clientActivite);
            // ---- QUITTER ----
            case "-":
                this.menuActivitesPrincipal(client);
            // ---- CHOOSED AN ACTIVITE ----
            default:
                Activite choosenActivite = activites.get(Integer.parseInt(pick)-1);

                boolean alreadySubscribed = false;
                for(Activite act : activiteController.activitesNametoList(client.getActivites())){
                    if (act.equals(choosenActivite)){
                        alreadySubscribed = true;
                        break;
                    }
                }

                this.MenuFicheActivite(client, choosenActivite, alreadySubscribed);

                if (clientActivite){
                    activites = activiteController.activitesNametoList(client.getActivites());
                }

                this.menuRechercheActivites(client, activites, clientActivite);
        }

    }

    /**
     * Logique d'une fiche d'activité.
     * Selon le choix du client, si il est deja inscrit, il peut se désinscrire, si il n'est pas inscrit, il peut
     * s'inscrire ou retour au menu de recherche d'activité.
     *
     * @param client Le client.
     * @param activite Activité duquelle afficher la fiche.
     * @param alreadySubscribed Client déja inscrit ou non.
     */
    public void MenuFicheActivite(Client client, Activite activite, boolean alreadySubscribed){

        String pick;

        pick = menuClient.displayPageFicheActivite(activite, alreadySubscribed);

        switch (pick){
        case "1":
            if (alreadySubscribed){
                activiteController.desinscriptionClient(client, activite);
                MenuFicheActivite(client, activite, false);
            } else {
                boolean succes = activiteController.inscriptionClient(client, activite);

                if (succes){
                    MenuFicheActivite(client, activite, true);
                } else {
                    menuClient.displayPagecomposanteManquantes(activite);
                    MenuFicheActivite(client, activite, false);
                }
            }
        case "-":
        }
    }

    /**
     * Logique du menu Flotte
     *
     * @param client Le client.
     */
    public void MenuFlotte(Client client){
        String pick;
        FlotteController flotteController = FlotteController.getInstance(client);

        pick = menuClient.displayPageFlotte(client);

        switch (pick){
            case "1" :
                Robot robot = flotteController.createRobot(client.getComposantes());
                if (!(robot==null)) {
                    flotteController.enregistrerRobot(robot);
                }

                MenuFlotte(client);
                break;

            case "2" :
                Scanner scanner = new Scanner(System.in);
                System.out.println("Entrez le nom du robot que vous voulez supprimer");
                String inputName = scanner.nextLine();

                flotteController.removeRobot(inputName);
                MenuFlotte(client);
                break;


            case "3" :
                Scanner subscanner = new Scanner(System.in);
                System.out.println("souhaitez vous une vue Generale ou une vue complete?");
                System.out.println("1. vue generale");
                System.out.println("2. vue complete");
                String subpick = subscanner.nextLine();
                if (subpick.equals("1")){
                    flotteController.vueGenerale(client.getFlotte());
                }
                else if (subpick.equals("2")){
                    flotteController.vueComplete(client.getFlotte());
                }
                else {
                    System.out.println("prenez un choix valide sinon consequences");
                }
                MenuFlotte(client);

            case "-":
                menuPrincipalClient(client);
        }
    }


    // -------------------------- Fournisseur --------------------------
    /**
     * Affiche le menu principal pour le Fournisseur et gère les choix de l'utilisateur.
     *
     * @param fournisseur le Fournisseur
     */
    public void menuPrincipalFournisseur(Fournisseur fournisseur){

        String pick = menuFournisseur.displayPagePrincipal(fournisseur);

        switch (pick) {
            case "1":
                menuProfil(fournisseur);
                break;
            case "2":
                menuGestionComposantes(fournisseur);
                break;
            case "3":
                menuEnregisterComposante(fournisseur);
                break;
            case "-":
                this.start();
        }

    }
    //menuProfil est mentionné dans DM3, mais pas à compléter.
    /**
     * Affiche le menu de gestion du profil pour le Fournisseur et gère les choix de l'utilisateur.
     *
     * @param fournisseur le Fournisseur
     */
    public void menuProfil(Fournisseur fournisseur){
        String pick;
        pick = menuFournisseur.displayPageProfil(fournisseur);

        switch (pick){
            case "1" :
                menuFournisseur.displayPageModifierSonProfil(fournisseur, "username");
                menuProfil(fournisseur);
                break;

            case "2" :
                menuFournisseur.displayPageModifierSonProfil(fournisseur, "password");
                menuProfil(fournisseur);
                break;

            case "-":
                //retour aux menus
                menuPrincipalFournisseur(fournisseur);
                break;
        }
    }

    /**
     * Affiche le menu de gestion des composantes pour le Fournisseur et gère les choix de l'utilisateur.
     *
     * @param fournisseur le Fournisseur
     */
    public void menuGestionComposantes(Fournisseur fournisseur) {
        String pick;
        pick = menuFournisseur.displayPageGestionComposante(fournisseur);

        switch (pick) {
            case "1":
                // Affiche toutes les composantes du fournisseur
                for (Composante comp : fournisseur.getComposantes()) {
                    System.out.println(comp);
                }
                menuGestionComposantes(fournisseur);
            case "2":
                // Supprime une composante au choix
                Scanner scan = new Scanner(System.in);
                System.out.print("Entrez le nom de la composante à supprimer: ");
                String nomASupprimer = scan.nextLine();
                fournisseur.supprimerComposante(nomASupprimer);
                System.out.println("Composante supprimée avec succès!");
                menuGestionComposantes(fournisseur);
            case "3":
                // Modifier une composante
                Scanner scan2 = new Scanner(System.in);

                for (Composante comp : fournisseur.getComposantes()) {
                    System.out.println(comp);
                }
                System.out.println();
                System.out.print("Entrez le nom de la composante à modifier: ");
                String nomAModifier = scan2.nextLine();

                ArrayList<String> composanteNames = new ArrayList<>();


                for(Composante composante : fournisseur.getComposantes()){
                    composanteNames.add(composante.getNom());
                }

                if(!composanteNames.contains(nomAModifier)){
                    System.out.println("Composante non existante!");
                    menuGestionComposantes(fournisseur);
                }

                Composante composante = fournisseur.getComposanteByName(nomAModifier);
                String oldType = composante.getType().name();

                fournisseur.supprimerComposante(nomAModifier);
                System.out.print("Entrez le nouveau nom de la composante: ");
                String nouveauNom = scan2.nextLine();
                System.out.print("Entrez la nouvelle description de la composante: ");
                String nouvelleDescription = scan2.nextLine();
                String nouveauPrix = "";

                while (true) {
                    System.out.print("Entrez le nouveau prix de la composante: (Integer)");
                    nouveauPrix = scan2.nextLine();

                    try {
                        int number = Integer.parseInt(nouveauPrix);
                        break; // Exit the loop if the input is a valid integer
                    } catch (NumberFormatException e) {
                        System.out.println("That's not a valid integer. Please try again.");
                    }
                }

                composanteController.enregistrerComposante(fournisseur, new Composante(nouveauNom, oldType, nouvelleDescription, nouveauPrix));
                System.out.println("Composante modifiée avec succès!");
                menuGestionComposantes(fournisseur);
            case "-":
                menuPrincipalFournisseur(fournisseur);
        }
    }


    /**
     * Affiche le menu d'enregistrement de composante pour le Fournisseur et gère les choix de l'utilisateur.
     *
     * @param fournisseur le Fournisseur
     */
    public void menuEnregisterComposante(Fournisseur fournisseur) {
        String pick;
        pick = menuFournisseur.displayPageEnregisterComposante(fournisseur);

        switch (pick) {
            case "1":
                // Enregistrer nouvelle composante
                Scanner scan = new Scanner(System.in);
                System.out.print("Entrez le nom de la composante: ");
                String nom = scan.nextLine();

                ArrayList<String> typesValides = new ArrayList<>();
                String type = "";
                for (ComposanteType composanteType : ComposanteType.values()){
                    typesValides.add(composanteType.name());
                }

                while (true) {
                    System.out.print("Entrez le type de la composante: ");
                    System.out.print("(CPU, ROUE, MICRO, ECRAN, HELICE, BRAS, CAMERA, HAUTPARLEUR) ");
                    type = scan.nextLine();

                    if (typesValides.contains(type)){
                        break;
                    }

                }

                System.out.print("Entrez la description de la composante: ");
                String description = scan.nextLine();
                String prix = "";

                while (true) {
                    System.out.print("Entrez le prix de la composante: (Integer)");
                    prix = scan.nextLine();

                    try {
                        int number = Integer.parseInt(prix);
                        break; // Exit the loop if the input is a valid integer
                    } catch (NumberFormatException e) {
                        System.out.println("That's not a valid integer. Please try again.");
                    }
                }

                composanteController.enregistrerComposante(fournisseur, new Composante(nom, type, description, prix));
                System.out.println("Composante ajoutée avec succès!");
                menuEnregisterComposante(fournisseur);
            case "-":
                menuPrincipalFournisseur(fournisseur);
                break;
        }
    }
}

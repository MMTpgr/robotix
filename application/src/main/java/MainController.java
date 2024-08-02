import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class MainController {

    // -------------------------- Controller and Repositories --------------------------

    private final ActiviteController activiteController =  ActiviteController.getInstance();
    private final ActiviteRepository activiteRepository = activiteController.getRepository();
    private final ClientController clientController =  ClientController.getInstance();
    private final FournisseurController fournisseurController = FournisseurController.getInstance();
    private final FournisseurRepository fournisseurRepository = fournisseurController.getRepository();
    private final  ComposanteController composanteController =  ComposanteController.getInstance();
    private final ComposanteRepository composanteRepository = composanteController.getRepository();
    private FlotteController flotteController ;
    private final MenuConnexion menu = MenuConnexion.getInstance();
    private final MenuClient menuClient = MenuClient.getInstance();
    //MenuFournisseur
    //private final MenuFournisseur menuFournisseur = MenuFournisseur.getInstance();
    MenuFournisseur menuFournisseur = MenuFournisseur.getInstance();

    // -------------------------- START --------------------------

    private final Utilisateur currentUser = null;

    public void start(){
        // Connexion or login here
        //menu.displayPageStart();

        // Client
        Client client = new Client("totoClient", "bobo");
        // Fournisseur
        Fournisseur fournisseur = new Fournisseur("totoFournisseur", "bobo");

        ArrayList<Composante> nes = new ArrayList<Composante>();

        Composante c1 = new Bras();
        Composante c2 = new CPU();
        Composante c3 = new Camera();

        nes.add(c1);
        nes.add(c2);
        nes.add(c3);
        client.composantes = nes;

        //------
        //ENLEVER! TESTS! - Stocker dans un fichier permanent plus tard.
        // Initialisation des composantes
        fournisseur.ajouterComposante(new Composante("MegaRotor", "HELICE", "Gâtez-vous avec le tout nouveau MegaRotor!", "299.99"));
        fournisseur.ajouterComposante(new Composante("BigBrain", "CPU", "Maintenant disponible, le processeur BigBrain9 offre les meilleures performances du marché!", "799.99"));
        fournisseur.ajouterComposante(new Composante("SuperSqueaker", "HAUTPARLEUR", "SuperSqueaker, un classique!", "69.99"));
        //-----

        // Affiche le menu principal du client
        //menuPrincipalClient(client);
        // Affiche le menu principal du fournisseur
        menuPrincipalFournisseur(fournisseur);

    }

    // -------------------------- CONNEXION/SIGN-IN --------------------------

    public void connexion(String username, String password){

        // Get User from ClientRepository or FournisseurRepository

        // If it exists, validate password.

        // if Client -> this.connexionClient()
        // else -> this.connexionFournisseur()
        // this.currentUser = foundUser;
        // if
        // flotteController = flotteController.getInstance(this.currentUser);

    }

    public void connexionClient(Client client){

    }

    public void connexionFournisseur(Fournisseur fournisseur){

    }

    // -------------------------- Client --------------------------
    // Version pour fournisseur vers le milieu / fin de ce document

    public void menuPrincipalClient(Client client){

        String pick = menuClient.displayPagePrincipal(client);

        switch (Integer.parseInt(pick)) {
            case 1:
                //flotteController = FlotteController.getInstance(client);
                //menuClient.displayPageFlotte(client);

                MenuFlotte(client);

                break;
            case 2:
                this.menuActivitesPrincipal(client);
                break;
            case 3:
                menuMarketPlacePrincipal(client);
//            case 4:
//                displayPageInterets(user);
//            case 5:
//                displayPageAbonnements(user);
//            case 6:
//                interets.clear();
//                users.clear();
//                main(null);
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
                this.menuRechercheComposante(client, composantes);
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
    public void menuRechercheComposante(Client client, ArrayList<Composante> composantes){

        String pick = menuClient.displayPageRechercheComposante(composantes);


        switch (pick){

            case "!":
                Composante.filterComposantes(composantes, ComposanteFilter.NAME);
                this.menuRechercheComposante(client, composantes);
            case "#":
                Composante.filterComposantes(composantes, ComposanteFilter.TYPE);
                this.menuRechercheComposante(client, composantes);
            case "*":
                Composante.filterComposantes(composantes, ComposanteFilter.FOURNISSEUR);
                this.menuRechercheComposante(client, composantes);
            case "-":
                return;
            default:
                Composante choosenComposante = composantes.get(Integer.parseInt(pick)-1);

                // For now checking if this is a sold composante with composante.getFournisseur()
                if (choosenComposante.getFournisseur() == null){
                    this.menuFicheComposante(choosenComposante);
                } else {
                    this.menuFicheAchatComposante(choosenComposante);
                }
                menuRechercheComposante(client, composantes);
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
                // Achat Composante here
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
                return;
            default:
                Fournisseur choosenFournisseur = fournisseurs.get(Integer.parseInt(pick));
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
                this.menuRechercheComposante(client, fournisseur.getComposantes());
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
                this.menuRechercheActivites(client, client.getActivites());
            // ---------------- Toutes Activites ----------------
            case "2":
                this.menuRechercheActivites(client,
                        activiteRepository.getActivites());
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
    public void menuRechercheActivites(Client client, ArrayList<Activite> activites){

        String pick;

        pick = menuClient.displayPageRechercheActivite(activites);

        switch (pick){
            // ---- FILTERS ----
            case "!":
                Activite.sortActivites(activites, ActiviteFilter.NOM);
                this.menuRechercheActivites(client, activites);
            case "#":
                Activite.sortActivites(activites, ActiviteFilter.DATE);
                this.menuRechercheActivites(client, activites);
            case "*":
                Activite.sortActivites(activites, ActiviteFilter.POPULARITE);
                this.menuRechercheActivites(client, activites);
            // ---- QUITTER ----
            case "-":
                this.menuActivitesPrincipal(client);
            // ---- CHOOSED AN ACTIVITE ----
            default:
                Activite choosenActivite = activites.get(Integer.parseInt(pick)-1);

                boolean alreadySubscribed = false;
                for(Activite act : client.getActivites()){
                    if (act.equals(choosenActivite)){
                        alreadySubscribed = true;
                        break;
                    }
                }

                this.MenuFicheActivite(client, choosenActivite, alreadySubscribed);
                this.menuRechercheActivites(client, activites);
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

    public void MenuFlotte(Client client){
        String pick;
        FlotteController flotteController = FlotteController.getInstance(client);

        pick = menuClient.displayPageFlotte(client);

        switch (pick){
            case "1" :
                Robot robot = flotteController.createRobot(client.getComposantes());
                flotteController.enregistrerRobot(robot);

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

            case "4":
                menuClient.displayPagePrincipal(client);
        }
    }


    // TO BE CONTINUED...

    // -------------------------- Fournisseur --------------------------
    /**
     * Affiche le menu principal pour le Fournisseur et gère les choix de l'utilisateur.
     *
     * @param fournisseur le Fournisseur
     */
    public void menuPrincipalFournisseur(Fournisseur fournisseur){

        String pick = menuFournisseur.displayPagePrincipal(fournisseur);

        switch (Integer.parseInt(pick)) {
            case 1:
                menuProfil(fournisseur);
                break;
            case 2:
                menuGestionComposantes(fournisseur);
                //voir ses composantes (de base)
                //supprimer une composante
                //modifier ses composantes
                break;
            case 3:
                menuEnregisterComposante(fournisseur);
                break;

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
                //nouveau nom d'utilisateur
                break;

            case "2" :
                //nouveau mot de passe
                break;

            case "3" :
                //nouveau courriel
                break;

            case "4":
                //retour aux menus
                menuFournisseur.displayPagePrincipal(fournisseur);
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
                break;
            case "2":
                // Supprime une composante au choix
                Scanner scan = new Scanner(System.in);
                System.out.print("Entrez le nom de la composante à supprimer: ");
                String nomASupprimer = scan.nextLine();
                fournisseur.supprimerComposante(nomASupprimer);
                System.out.println("Composante supprimée avec succès!");
                break;
            case "3":
                // Modifier une composante
                Scanner scan2 = new Scanner(System.in);
                System.out.print("Entrez le nom de la composante à modifier: ");
                String nomAModifier = scan2.nextLine();
                fournisseur.supprimerComposante(nomAModifier);
                System.out.print("Entrez le nouveau nom de la composante: ");
                String nouveauNom = scan2.nextLine();
                System.out.print("Entrez le nouveau type de la composante: ");
                String nouveauType = scan2.nextLine();
                System.out.print("Entrez la nouvelle description de la composante: ");
                String nouvelleDescription = scan2.nextLine();
                System.out.print("Entrez le nouveau prix de la composante: ");
                String nouveauPrix = scan2.nextLine();
                fournisseur.ajouterComposante(new Composante(nouveauNom, nouveauType, nouvelleDescription, nouveauPrix));
                System.out.println("Composante modifiée avec succès!");
                break;
            case "4":
                menuFournisseur.displayPagePrincipal(fournisseur);
                break;
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
                System.out.print("Entrez le type de la composante: ");
                String type = scan.nextLine();
                System.out.print("Entrez la description de la composante: ");
                String description = scan.nextLine();
                System.out.print("Entrez le prix de la composante: ");
                String prix = scan.nextLine();
                fournisseur.ajouterComposante(new Composante(nom, type, description, prix));
                System.out.println("Composante ajoutée avec succès!");
                break;
            case "2":
                menuFournisseur.displayPagePrincipal(fournisseur);
                break;
        }
    }


}

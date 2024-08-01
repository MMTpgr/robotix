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

    // -------------------------- START --------------------------

    private final Utilisateur currentUser = null;

    public void start(){
        // Connexion or login here
        //menu.displayPageStart();
        Client client = new Client("toto", "bobo");
        ArrayList<Composante> nes = new ArrayList<Composante>();

        Composante c1 = new Bras();
        Composante c2 = new CPU();
        Composante c3 = new Camera();

        nes.add(c1);
        nes.add(c2);
        nes.add(c3);
        client.composantes = nes;

        menuPrincipalClient(client);

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

    public void menuFicheComposante(Composante composante){
        menuClient.displayPageFicheComposante(composante);
    }

    public void menuFicheAchatComposante(Composante composante){

        String pick = menuClient.displayPageFicheAchatComposante(composante);

        switch (pick){
            case "+":
                // Achat Composante here
            case "-":
                return;
        }
    }

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

    public void MenuFicheActivite(Client client, Activite activite, boolean alreadySubscribed){

        String pick;

        pick = menuClient.displayPageFicheActivite(activite, alreadySubscribed);

        switch (pick){
        case "1":
            if (alreadySubscribed){
                activiteController.desinscriptionUtilisateur(client, activite);
                MenuFicheActivite(client, activite, false);
            } else {
                boolean succes = activiteController.inscriptionUtilisateur(client, activite);

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
                System.out.println("Entrez le nom du robot que vous voullez supprimer");
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

}

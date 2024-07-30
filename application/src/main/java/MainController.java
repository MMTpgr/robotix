import java.time.LocalDate;
import java.util.ArrayList;

public class MainController {

    public ActiviteController activiteController =  ActiviteController.getInstance();
    public ActiviteRepository activiteRepository = activiteController.getRepository();
    public ClientController clientController =  ClientController.getInstance();
    public FournisseurController fournisseurController =  FournisseurController.getInstance();
    public ComposanteController composanteController =  ComposanteController.getInstance();
    public FlotteController flotteController = null;
    public Utilisateur currentUser = null;

    public MenuConnexion menu = MenuConnexion.getInstance();
    public MenuClient menuClient = MenuClient.getInstance();


    // -------------------------- START --------------------------
    public void start(){
        // Connexion or login here
        //menu.displayPageStart();
        Client client = new Client("toto", "bobo");
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
        this.currentUser = client;
        flotteController = FlotteController.getInstance(client);
    }

    public void connexionFournisseur(Fournisseur fournisseur){

        this.currentUser = fournisseur;

    }

    // -------------------------- Client --------------------------

    public void menuPrincipalClient(Client client){

        String pick = menuClient.displayPagePrincipal(client);

        switch (Integer.parseInt(pick)) {
//            case 1:
//                displayPageFlotte(user);
//                break;
            case 2:
                this.menuActivitesPrincipal(client);
                break;
//            case 3:
//                displayPageMarket(user);
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
            case "3":
                this.menuPrincipalClient(client);
        }
        }


        public void menuRechercheActivites(Client client, ArrayList<Activite> activites){

        ArrayList<Activite> sortedActivites;
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
                activiteController.DesinscriptionUtilisateur(client, activite);
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
        case "2":
            return;
        }
    }

    // TO BE CONTINUED...

}

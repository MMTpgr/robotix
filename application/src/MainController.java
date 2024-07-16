public class MainController {

    public ActiviteController activiteController =  ActiviteController.getInstance();
    public ClientController clientController =  ClientController.getInstance();
    public FournisseurController fournisseurController =  FournisseurController.getInstance();
    public ComposanteController composanteController =  ComposanteController.getInstance();
    public FlotteController flotteController = null;
    public Utilisateur currentUser = null;

    public Menu menu = Menu.getInstance();

    // Everything starts from here
    public void start(){

        menu.displayPageStart();

    }

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

    // TO BE CONTINUED...

}

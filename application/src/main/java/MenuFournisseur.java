public class MenuFournisseur extends MenuUtilisateur{

    // Singleton
    private static MenuFournisseur _instance;

    public static MenuFournisseur getInstance(){
        if (_instance == null){
            _instance = new MenuFournisseur();
        }
        return _instance;
    }

    public void displayPagePrincipal(){

    }

    public void displayPageEnregistrerComposante(){

    }

    public void displayPageAfficherComposantes(){

    }

    public void displayPageModifierComposantes(){

    }

    public void displayPageFicheComposantes(){
        // Deleting composante here
    }

}

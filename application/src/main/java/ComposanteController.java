public class ComposanteController {


    // Singleton
    private static ComposanteController _instance;

    public static ComposanteController getInstance(){
        if (_instance == null){
            _instance = new ComposanteController();
        }
        return _instance;
    }

    private ComposanteController(){

    }

    /**
     * Retourne le repository des composantes a vendres.
     *
     * @return
     */
    public ComposanteRepository getRepository(){
        return ComposanteRepository.getInstance();
    }

    /**
     * Achat d'une composante dans MarketPlace
     *
     * @param client Le client.
     * @param composante La Composante.
     */
    public void achatComposante(Client client, Composante composante){

        client.getComposantes().add(composante);

        ComposanteRepository composanteRepository = ComposanteRepository.getInstance();
        composanteRepository.removeComposante(composante);

        Fournisseur fournisseur = composante.getFournisseur();
        fournisseur.getComposantes().remove(composante);

        composante.setPrix(0);

        Notification notification = new Notification("MarketPlace", "Achat de la composante " + composante.getNom());

        client.addNotification(notification);

    }


}

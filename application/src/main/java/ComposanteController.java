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

    public ComposanteRepository getRepository(){
        return ComposanteRepository.getInstance();
    }

}

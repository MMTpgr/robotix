import java.util.ArrayList;

public class ClientController extends ClientRepository {

    // Singleton
    private static ClientController _instance;

    public static ClientController getInstance(){
        if (_instance == null){
            _instance = new ClientController();
        }
        return _instance;
    }

    private ClientController(){

    }

    public static ClientRepository getRepository(){
        return ClientRepository.getInstance();
    }


}

import java.util.ArrayList;

public class FlotteController {

    // Singleton
    private static FlotteController _instance;

    private static Flotte _flotte;

    public static FlotteController getInstance(Client client){
        if (_instance == null){
            _instance = new FlotteController();
            _flotte = client.getFlotte();
        }
        return _instance;
    }

    public void removeRobot(String name){
        // Do Something
    }

    public void enregistrerRobot(Robot robot){
        // Do Something
    }

    public void createRobot(ArrayList<Composante> composantes){
        // Do Something
    }

    public Robot getRobot(String name){
        Robot robot = null;

        // Do Something

        return robot;
    }


    public void afficherMetriqueFlotte(){

    }

    public void afficherMetriqueRobot(Robot robot){

    }


}

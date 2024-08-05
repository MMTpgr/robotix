import java.util.ArrayList;
import java.util.Comparator;

public class ComposanteRepository {

    private static ComposanteRepository _instance;

    private String dataFile = "Composantes.json";

    private ArrayList<Composante> composantes;

    // -------------------------- GETTER SETTER --------------------------

    // Singleton
    public static ComposanteRepository getInstance(){
        if (_instance == null){
            _instance = new ComposanteRepository();
        }
        return _instance;
    }

    private ComposanteRepository(){

    }


    // -------------------------- UTILS METHODS --------------------------

    public ArrayList<Composante> getComposantes() {
        if (this.composantes == null){
            this.parseComposantes();
        }
        return this.composantes;
    }

    public Composante getComposante(String name){

        Composante foundComposante = null;

        return foundComposante;

    }

    public void addComposante(Composante composante){
        return;
    }

    public void removeComposante(String name){
        return;
    }

    public ArrayList<Composante> parseComposantes(){

        ArrayList<Composante> composantes = new ArrayList<>();
        Composante currentComposante;

        // JSON PARSING HERE

        return composantes;

    }

    public void writeComposantes(){

        // JSON WRITING HERE

    }

}


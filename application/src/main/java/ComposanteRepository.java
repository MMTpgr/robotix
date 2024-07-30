import java.util.ArrayList;
import java.util.Comparator;

public abstract class ComposanteRepository {


    private String dataFile = "Composantes.json";


    // -------------------------- GETTER SETTER --------------------------


    // -------------------------- UTILS METHODS --------------------------

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


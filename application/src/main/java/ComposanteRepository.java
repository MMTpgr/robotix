import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

public class ComposanteRepository {

    private static ComposanteRepository _instance;

    private String DATAFILE = "Composantes.json";

    private ArrayList<Composante> composantes;

    // -------------------------- GETTER SETTER --------------------------

    // Singleton
    public static ComposanteRepository getInstance(){
        if (_instance == null){
            _instance = new ComposanteRepository();
            _instance.parseComposantes();
        }
        return _instance;
    }

    private ComposanteRepository(){

    }


    // -------------------------- UTILS METHODS --------------------------

    public ArrayList<Composante> getComposantes() {
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

    /**
     * Fonction qui initialise les données en les lisant du fichier json. Appelé à l'instanciation.
     */
    public void parseComposantes(){
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(DATAFILE)) {
            composantes = gson.fromJson(reader, new TypeToken<ArrayList<Composante>>(){}.getType());
        } catch (IOException e) {
            e.printStackTrace();
            composantes = new ArrayList<>();
        }
    }

    public void writeComposantes(){
        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter(DATAFILE)) {
            gson.toJson(composantes, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}


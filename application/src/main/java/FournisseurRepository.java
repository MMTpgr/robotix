import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

public class FournisseurRepository {

    private static FournisseurRepository _instance;
    private final String DATAFILE = "Fournisseurs.json";
    private ArrayList<Fournisseur> fournisseurs;

    // -------------------------- GETTER SETTER --------------------------

    // Singleton
    public static FournisseurRepository getInstance(){
        if (_instance == null){
            _instance = new FournisseurRepository();
            _instance.parseFournisseurs();
        }
        return _instance;
    }

    private FournisseurRepository(){

    }

    // -------------------------- UTILS METHODS --------------------------

    public ArrayList<Fournisseur> getFournisseurs() {
        return this.fournisseurs;
    }


    public void addFournisseurs(ArrayList<Fournisseur> fournisseurs){
        this.fournisseurs.addAll(fournisseurs);
    }

    /**
     * Fonction qui initialise les données en les lisant du fichier json. Appelé à l'instanciation.
     */
    public void parseFournisseurs(){
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(DATAFILE)) {
            fournisseurs = gson.fromJson(reader, new TypeToken<ArrayList<Fournisseur>>(){}.getType());
        } catch (IOException e) {
            e.printStackTrace();
            fournisseurs = new ArrayList<>();
        }
    }

    /**
     * Appeler cette fonction en fin de programme pour stocker les nouvelles données de Fournisseurs
     */
    public void writeFournisseurs(){
        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter(DATAFILE)) {
            gson.toJson(fournisseurs, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}



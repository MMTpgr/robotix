import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;

public class ActiviteRepository {

    private static ActiviteRepository _instance;

    private String dataFile = "Activites.json";

    private ArrayList<Activite> activites = null;

    // -------------------------- GETTER SETTER --------------------------

    // Singleton
    public static ActiviteRepository getInstance(){
        if (_instance == null){
            _instance = new ActiviteRepository();
        }
        return _instance;
    }

    public String getDataFile() {
        return dataFile;
    }

    public ArrayList<Activite> getActivites() {
        if (this.activites == null){
            this.parseActivites();
        }
        return activites;
    }

    public void setActivites(ArrayList<Activite> activites) {
        this.activites = activites;
    }


    // -------------------------- UTILS METHODS --------------------------

    public Activite getActivite(String name){

        Activite foundActivite = null;

        return foundActivite;

    }

    public void addActivite(Activite activite){
        return;
    }

    public void removeActivite(String name){
        return;
    }

    public void parseActivites(){
        Gson gson = new Gson().newBuilder().setPrettyPrinting().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();

        try {
            String content = Files.readString(Paths.get(this.dataFile));

            Type foundType = new TypeToken<ArrayList<Activite>>(){}.getType();

            this.activites = gson.fromJson(content, foundType);

        } catch (IOException e){
            System.out.println(e);
        }
    }

    public void writeActivites(ArrayList<Activite> activites){
        try {
            FileWriter file = new FileWriter(this.dataFile);
            Gson gson = new Gson().newBuilder().setPrettyPrinting().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();
            gson.toJson(activites, file);
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}


// -------------------------- Serializers --------------------------

class LocalDateAdapter implements JsonSerializer<LocalDate> {
    public JsonElement serialize(LocalDate date, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(date.format(DateTimeFormatter.ISO_LOCAL_DATE)); // "yyyy-mm-dd"
    }
}


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

    private ArrayList<Activite> activites;

    // -------------------------- GETTER SETTER --------------------------
    /**
     * Singleton
     *
     * @return Unique instance de ActiviteRepository
     */
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

    /**
     * Retourne les activité de la base de données.
     * Si null ptr, on tente de parser le fichier "Activites.json"
     *
     * @return Liste d activités dans la base de données
     */
    public ArrayList<Activite> getActivites() {
        if (this.activites == null){
            this.parseActivites();
        }
        return this.activites;
    }

    public void setActivites(ArrayList<Activite> activites) {
        this.activites = activites;
    }


    // -------------------------- UTILS METHODS --------------------------

    /**
     * Lit/Parse le fichier "Activites.json".
     * Les activités sont ajouter à l'attribut 'activites'.
     *
     * Utilisé uniquement lorsque l'application est lancer.
     */
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

    /**
     * Écriture de l'attribut 'activites' dans le fichier "Activites.json".
     *
     * * Utilisé uniquement lorsque l'application est arreté.
     *
     * @param activites ArrayList d'activités à écrire.
     */
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

class LocalDateAdapter implements JsonSerializer<LocalDate>, JsonDeserializer<LocalDate> {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * Serialize un object LocalDate afin de pouvoir l'ecrire dans le fichier 'Activites.json'
     *
     * @param date Localdate à ècrire.
     * @param typeOfSrc
     * @param context
     * @return JsonPrimitive pouvant être écrit par gson.
     */
    @Override
    public JsonElement serialize(final LocalDate date, final Type typeOfSrc,
                                 final JsonSerializationContext context) {
        return new JsonPrimitive(date.format(formatter));
    }

    /**
     * Deserialize un object LocalDate afin de pouvoir lire le fichier 'Activites.json'
     *
     * @param json JsonElement à lire.
     * @param typeOfT
     * @param context
     * @return Objet Localdate
     * @throws JsonParseException
     */
    @Override
    public LocalDate deserialize(final JsonElement json, final Type typeOfT,
                                 final JsonDeserializationContext context) throws JsonParseException {
        return LocalDate.parse(json.getAsString(), formatter);
    }
}


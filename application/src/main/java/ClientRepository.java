import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;


public class ClientRepository {

    private static ClientRepository _instance;

    private String DATAFILE = "application/src/main/resources/Clients.json";
    private ArrayList<Client> clients;

    // -------------------------- GETTER SETTER --------------------------
    
    // -------------------------- UTILS METHODS --------------------------
    public Client getClient(String name){

        Client foundClient = null;

        return foundClient;

    }

    /**
     * Singleton instance
     *
     * @return
     */
    public static ClientRepository getInstance(){
        if (_instance == null){
            _instance = new ClientRepository();
            _instance.parseClients();
            if (_instance.clients == null){
                _instance.clients = new ArrayList<>();
            }

        }
        return _instance;
    }
    public void addClient(Client client){
        return;
    }

    public void removeClient(String name){
        return;
    }

    /**
     * Add a single client to repository
     *
     * @param clients
     */
    public void addClients(ArrayList<Client> clients){

        this.clients.addAll(clients);

    }

    public ArrayList<Client> getClients() {
        return this.clients;
    }

    /**
     * Lit/Parse le fichier "Client.json".
     * Les activités sont ajouter à l'attribut 'client'.
     *
     * Utilisé uniquement lorsque l'application est lancer.
     */
    public void parseClients(){
        Gson gson = new Gson().newBuilder().setPrettyPrinting().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();
        try {
            String content = Files.readString(Paths.get(this.DATAFILE));
            Type foundType = new TypeToken<ArrayList<Client>>(){}.getType();
            this.clients = gson.fromJson(content, foundType);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Écriture de l'attribut 'clients' dans le fichier "Client.json".
     * * Utilisé uniquement lorsque l'application est arreté.
     *
     */
    public void writeClient(){
        Gson gson = new Gson().newBuilder().setPrettyPrinting().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();
        try (FileWriter writer = new FileWriter(DATAFILE)) {
            gson.toJson(clients, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

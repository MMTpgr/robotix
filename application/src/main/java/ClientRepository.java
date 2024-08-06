import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;


public class ClientRepository {

    private static ClientRepository _instance;

    private String DATAFILE = "Clients.json";
    private ArrayList<Client> clients;

    // -------------------------- GETTER SETTER --------------------------
    
    // -------------------------- UTILS METHODS --------------------------
    public Client getClient(String name){

        Client foundClient = null;

        return foundClient;

    }

    public static ClientRepository getInstance(){
        if (_instance == null){
            _instance = new ClientRepository();
            _instance.parseClients();
        }
        return _instance;
    }
    public void addClient(Client client){
        return;
    }

    public void removeClient(String name){
        return;
    }

    public void addClients(ArrayList<Client> clients){

        this.clients.addAll(clients);

    }

    public ArrayList<Client> getClients() {
        return this.clients;
    }

    /**
     * Fonction qui initialise les données en les lisant du fichier json. Appelé à l'instanciation.
     */
    public void parseClients(){
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(DATAFILE)) {
            clients = gson.fromJson(reader, new TypeToken<ArrayList<Client>>(){}.getType());
        } catch (IOException e) {
            e.printStackTrace();
            clients = new ArrayList<>();
        }
    }

    public void writeClient(){
        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter(DATAFILE)) {
            gson.toJson(clients, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

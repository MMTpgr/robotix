import java.util.*;


public abstract class ClientRepository {

    private String dataFile = "Clients.json";
    private ArrayList<Client> clients;



    // -------------------------- GETTER SETTER --------------------------
    
    // -------------------------- UTILS METHODS --------------------------
    public Client getClient(String name){

        Client foundClient = null;

        return foundClient;

    }

    public void addClient(Client client){
        return;
    }

    public void removeClient(String name){
        return;
    }

    public ArrayList<Client> getClients() {
        if (this.clients == null){
            this.parseClients();
        }
        return this.clients;
    }

    public ArrayList<Client> parseClients(){

        ArrayList<Client> clients = new ArrayList<>();
        Client currentClient;

        // JSON PARSING HERE


        return clients;
    }

    public void writeClient(){

        // JSON WRITING HERE

    }

}

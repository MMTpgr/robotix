import java.util.*;


public abstract class ClientRepository {

    private String dataFile = "Clients.json";


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

    public ArrayList<Client> parseClients(){

        ArrayList<Client> clients = new ArrayList<>();
        Client currentClient;

        // JSON PARSING HERE


        return clients;
    }

    public void writeClient(){

        // JSON WRITING HERE

    }


    public ArrayList<Client> filterClient (ClientFilter filter){

        ArrayList<Client> clients = this.parseClients();

        if (filter.equals(ClientFilter.USERNAME)){
            clients.sort(new UserNameComparator());
        } else if (filter.equals(ClientFilter.ANCIENNETE)) {
            clients.sort(new AncienneteComparatorC());
        } else if (filter.equals(ClientFilter.SCORE)) {
            clients.sort(new ScoreComparator());
        }
        return clients;
    }
}

// -------------------------- Filtering --------------------------
enum ClientFilter{
    USERNAME,
    ANCIENNETE,
    SCORE
}

class UserNameComparator implements Comparator<Client> {
    @Override
    public int compare(Client c1, Client c2) {
        return c1.getUsername().compareTo(c2.getUsername());
    }
}

class AncienneteComparatorC implements Comparator<Client> {
    @Override
    public int compare(Client c1, Client c2) {
        return c1.getInscription().compareTo(c2.getInscription());
    }
}

class ScoreComparator implements Comparator<Client> {
    @Override
    public int compare(Client c1, Client c2) {
        return Integer.compare(c1.getPoints(), c2.getPoints());
    }
}
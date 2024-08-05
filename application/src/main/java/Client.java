import java.util.ArrayList;
import java.util.Comparator;

public class Client extends Utilisateur {

    private ArrayList<Fournisseur> fournisseurs = new ArrayList<>();

    private Flotte flotte = new Flotte();
    public Client(String username, String password) {
        super(username, password);
    }
    
    // -------------------------- GETTER SETTER --------------------------
    public ArrayList<Fournisseur> getFournisseurs() {
        return fournisseurs;
    }

    public void setFournisseurs(ArrayList<Fournisseur> fournisseurs) {
        this.fournisseurs = fournisseurs;
    }

    public Flotte getFlotte() {
        return flotte;
    }

    public void setFlotte(Flotte flotte) {
        this.flotte = flotte;
    }

    // -------------------------- UTILS METHODS --------------------------

    public static void filterClient (ArrayList<Client> toSort, ClientFilter filter){

        if (filter.equals(ClientFilter.USERNAME)){
            toSort.sort(new UserNameComparator());
        } else if (filter.equals(ClientFilter.ANCIENNETE)) {
            toSort.sort(new AncienneteComparatorC());
        } else if (filter.equals(ClientFilter.SCORE)) {
            toSort.sort(new ScoreComparator());
        }
    }

}

// ----------------- FILTERING -----------------

enum ClientFilter{
    USERNAME,
    ANCIENNETE,
    SCORE
}


class UserNameComparator implements Comparator<Client> {
    /**
     * Comparaison par Username
     *
     * @param c1 the first Client to be compared.
     * @param c2 the second Client to be compared.
     * @return
     */
    @Override
    public int compare(Client c1, Client c2) {
        return c1.getUsername().compareTo(c2.getUsername());
    }
}

class AncienneteComparatorC implements Comparator<Client> {
    /**
     * Comparaison par anciennete
     *
     * @param c1 the first Client to be compared.
     * @param c2 the second Client to be compared.
     * @return
     */
    @Override
    public int compare(Client c1, Client c2) {
        return c1.getInscription().compareTo(c2.getInscription());
    }
}

class ScoreComparator implements Comparator<Client> {
    /**
     * Comparaison par Score
     *
     * @param c1 the first Client to be compared.
     * @param c2 the second Client to be compared.
     * @return
     */
    @Override
    public int compare(Client c1, Client c2) {
        return Integer.compare(c1.getPoints(), c2.getPoints());
    }
}
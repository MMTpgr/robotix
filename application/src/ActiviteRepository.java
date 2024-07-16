import java.util.ArrayList;
import java.util.Comparator;

public abstract class ActiviteRepository {


    private String dataFile = "Activites.json";


    // -------------------------- GETTER SETTER --------------------------

    
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

    public ArrayList<Activite> parseActivites(){

        ArrayList<Activite> activites = new ArrayList<>();
        Activite currentActivite;

        // JSON PARSING HERE

        return activites;

    }

    public void writeActivites(){

        // JSON WRITING HERE

    }


    public ArrayList<Activite> filterActivites (ActiviteFilter filter, boolean robotsConform){

        ArrayList<Activite> activites = this.parseActivites();

        // Filtering with Treeset or sort function.
        if (filter.equals(ActiviteFilter.NOM)){
            activites.sort(new NomComparator());
        } else if (filter.equals(ActiviteFilter.DATE)) {
            activites.sort(new DateComparator());
        } else if (filter.equals(ActiviteFilter.POPULARITE)) {
            activites.sort(new PopulariteComparator());
        }

        return activites;
    }
}

// -------------------------- Filtering --------------------------

enum ActiviteFilter{
    NOM,
    DATE,
    POPULARITE
}

class NomComparator implements Comparator<Activite> {
    @Override
    public int compare(Activite a1, Activite a2) {
        return a1.getName().compareTo(a2.getName());
    }
}

class DateComparator implements Comparator<Activite> {
    @Override
    public int compare(Activite a1, Activite a2) {
        return a1.getDate().compareTo(a2.getDate());
    }
}
class PopulariteComparator implements Comparator<Activite> {
    @Override
    public int compare(Activite a1, Activite a2) {
        return Integer.compare(a1.getPopularite(), a2.getPopularite());
    }
}



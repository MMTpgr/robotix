import java.util.ArrayList;
import java.util.Comparator;

public abstract class ComposanteRepository {


    private String dataFile = "Composantes.json";


    // -------------------------- GETTER SETTER --------------------------


    // -------------------------- UTILS METHODS --------------------------

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

    public ArrayList<Composante> parseComposantes(){

        ArrayList<Composante> composantes = new ArrayList<>();
        Composante currentComposante;

        // JSON PARSING HERE

        return composantes;

    }

    public void writeComposantes(){

        // JSON WRITING HERE

    }


    public ArrayList<Composante> filterComposantes (ComposanteFilter filter, boolean robotsConform){

        ArrayList<Composante> composantes = this.parseComposantes();

        // Filtering with Treeset or sort function.
        if (filter.equals(ComposanteFilter.PRIX)){
            composantes.sort(new PrixComparator());
        } else if (filter.equals(ComposanteFilter.TYPE)) {
            composantes.sort(new TypeComparator());
        }else if (filter.equals(ComposanteFilter.FOURNISSEUR)) {
            composantes.sort(new FournisseurComparator());
        }

        return composantes;
    }
}

// -------------------------- Filtering --------------------------

enum ComposanteFilter{
    PRIX,
    TYPE,
    FOURNISSEUR
}

class PrixComparator implements Comparator<Composante> {
    @Override
    public int compare(Composante c1, Composante c2) {
        return Float.compare(c1.getPrix(), c2.getPrix());
    }
}

class TypeComparator implements Comparator<Composante> {
    @Override
    public int compare(Composante c1, Composante c2) {
        return c1.getType().compareTo(c2.getType());
    }
}

class FournisseurComparator implements Comparator<Composante> {
    @Override
    public int compare(Composante c1, Composante c2) {
        return c1.getFournisseur().getUsername().compareTo(c2.getFournisseur().getUsername());
    }
}


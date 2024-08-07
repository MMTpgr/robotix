
import java.util.ArrayList;


public class ComposanteRepository {

    private static ComposanteRepository _instance;


    // -------------------------- GETTER SETTER --------------------------

    // Singleton
    public static ComposanteRepository getInstance(){
        if (_instance == null){
            _instance = new ComposanteRepository();
        }
        return _instance;
    }

    private ComposanteRepository(){

    }


    // -------------------------- UTILS METHODS --------------------------

    /**
     * Retourne toutes les composantes de tout les Fournisseurs
     *
     * @return
     */
    public ArrayList<Composante> getComposantes() {

        ArrayList<Composante> composantes = new ArrayList<>();

        FournisseurRepository fournisseurRepository = FournisseurRepository.getInstance();

        for (Fournisseur fournisseur : fournisseurRepository.getFournisseurs()){
            composantes.addAll(fournisseur.getComposantes());
        }


        return composantes;
    }

}


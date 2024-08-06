
import java.util.ArrayList;


public class ComposanteRepository {

    private static ComposanteRepository _instance;

    private ArrayList<Composante> composantes;

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

    public ArrayList<Composante> getComposantes() {
        if (this.composantes == null){
            FournisseurRepository fournisseurRepository = FournisseurRepository.getInstance();

            for (Fournisseur fournisseur : fournisseurRepository.getFournisseurs()){
                this.composantes.addAll(fournisseur.getComposantes());
            }

        }
        return this.composantes;

    }


    public void addComposante(Composante composante){
        this.composantes.add(composante);
    }

    public void removeComposante(Composante composante){
        this.composantes.remove(composante);
    }


}


import java.util.ArrayList;

public class Fournisseur extends Utilisateur{

    ArrayList<Composante> composantes;

    public Fournisseur(String username, String password){
        super(username, password);
    }

    public void acheterComposante(Composante c){
        this.composantes.remove(c);
    }
}

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

public class FournisseurControllerTest {

    public FournisseurController fournisseurController = FournisseurController.getInstance();
    public Fournisseur baseFournisseur;

    @Before
    public void generateBaseFournisseur() {
        this.baseFournisseur = new Fournisseur("Fournisseux", "CaFournit");
    }

//    @Test
//    public void testRegisterComposante(){
//        fournisseurController.registerComposante();
//    }
//
//    @Test
//    public void testSupprimerComposante(){
//        fournisseurController.supprimerComposante();
//    }
//
//    @Test
//    public void testNotifierUtilisateur(){
//        fournisseurController.notifierUtilisateur();
//    }
//
//
//    @Test
//    public void testGererComposantes(){
//        fournisseurController.gererComposantes();
//    }

}
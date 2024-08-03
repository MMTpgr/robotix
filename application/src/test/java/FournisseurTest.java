import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class FournisseurTest {

    private Fournisseur fournisseur;
    private Composante composante;

    @BeforeEach
    public void setUp() {
        fournisseur = new Fournisseur("UtilisateurTest", "mdp");
        composante = new Composante("TestComposante", "CPU", "Description pour test", "100.00");
    }

    /**
     * Test pour ajouter une composante valide.
     */
    @Test
    public void testAjouterComposanteValide() {
        fournisseur.ajouterComposante(composante);
        List<Composante> composantes = fournisseur.getComposantes();
        assertEquals(1, composantes.size());
        assertEquals("TestComposante", composantes.get(0).getNom());
    }

    /**
     * Test pour ajouter une composante null.
     */
    @Test
    public void testAjouterComposanteNull() {
        fournisseur.ajouterComposante(null);
        List<Composante> composantes = fournisseur.getComposantes();
        assertEquals(1, composantes.size());
        assertNull(composantes.get(0));
    }

    /**
     * Test pour ajouter plusieurs composantes.
     */
    @Test
    public void testAjouterPlusieursComposantes() {
        Composante composante2 = new Composante("Composante2", "HELICE", "Description 2", "200.00");
        fournisseur.ajouterComposante(composante);
        fournisseur.ajouterComposante(composante2);

        List<Composante> composantes = fournisseur.getComposantes();
        assertEquals(2, composantes.size());
        assertEquals("TestComposante", composantes.get(0).getNom());
        assertEquals("Composante2", composantes.get(1).getNom());
    }
}


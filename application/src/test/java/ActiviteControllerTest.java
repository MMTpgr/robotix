import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;

public class ActiviteControllerTest {
    private ActiviteController activiteController = ActiviteController.getInstance();
    private Activite baseActivite;
    private Client baseClient;
    // before each test, Resetting this activite
    @Before
    public void generateBaseActivite(){

        // Activite numero 1
        LocalDate date = LocalDate.of(2024, 10, 6);

        Activite activite = new Activite("Menage chez Jordan",
                date,
                "Menage de la cuisine et du salon",
                "Jordan",
                ACTIVITEETAT.NONDEBUTEE);

        Tache tache1 = new Tache("Balayeuse");

        ArrayList<ComposanteType> composanteTypes1 = new ArrayList<>();
        composanteTypes1.add(ComposanteType.ROUE);
        Action action1 = new Action("Avancer", composanteTypes1);

        ArrayList<ComposanteType> composanteTypes2 = new ArrayList<>();
        composanteTypes1.add(ComposanteType.BRAS);
        Action action2 = new Action("Aspirer", composanteTypes1);

        tache1.addAction(action2, 0);
        tache1.addAction(action1, 0);

        activite.addTache(tache1, 0);
        this.baseActivite = activite;
    }
    // Before each test, Resetting this client
    @Before
    public void generateBaseClient(){
        Client client = new Client("Testy", "Testeux");
        this.baseClient = client;

        // Creating a robot
        String[] infos = {"Bender", "bender", "sad1879s32dsa"};
        Robot robot = new Robot(infos, 100, false);
        Bras bras = new Bras();
        Roue roue = new Roue();
        CPU cpu = new CPU();
        Composante[] composantes = {bras, roue, cpu};
        robot.setComposantes(composantes);
        client.getFlotte().addRobot(robot);
    }


    @Test
    public void testClientValidesRobotsForActivite(){

        ArrayList<Robot> robotsValide = activiteController.clientValidesRobotsForActivite(this.baseClient, baseActivite);
        Assert.assertEquals(robotsValide.get(0).getNom(), "Bender");
    }

    @Test
    public void testInscriptionUtilisateur(){

        activiteController.inscriptionUtilisateur(this.baseClient, this.baseActivite);
        // Activite is link to client
        Assert.assertEquals(this.baseClient.getActivites().get(0).getName(), this.baseActivite.getName());
        // Client is link to activite
        Assert.assertEquals(this.baseActivite.getParticipants().get(0), this.baseClient.getUsername());
    }

    @Test
    public void testDesinscriptionUtilisateur(){
        activiteController.inscriptionUtilisateur(this.baseClient, this.baseActivite);
        activiteController.desinscriptionUtilisateur(this.baseClient, this.baseActivite);
        // Activite is unlink to client
        Assert.assertTrue(this.baseClient.getActivites().isEmpty());
        // Client is unlink to activite
        Assert.assertTrue(this.baseActivite.getParticipants().isEmpty());
    }
}
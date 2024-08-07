import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import static org.junit.Assert.*;

public class FlotteControllerTest {
    private Client baseClient = new Client("Testy", "Testeux");
    private FlotteController flotteController = FlotteController.getInstance(baseClient);

    @Before
    public void generateBaseClient(){

        // Creating a robot
        String[] infos = {"Bender", "bender", "sad1879s32dsa"};
        String[] infos2 = {"hasouss", "pipi", "lehauler"};
        String[] infos3= {"hajaaj", "pipi", "lehauler"};

        Bras bras = new Bras();
        Roue roue = new Roue();
        CPU cpu = new CPU();
        Helice helice = new Helice();
        Camera camera = new Camera();

        ArrayList<Composante> composantess= new ArrayList<Composante>();
        composantess.add(bras); composantess.add(roue); composantess.add(cpu);

        Robot robot = new Robot(infos, 100, false,composantess);
        composantess.add(helice);
        Robot robot2 = new Robot(infos2,100, false, composantess);
        composantess.add(camera);
        Robot robot3 = new Robot(infos3,100, false, composantess);

        this.baseClient.getFlotte().addRobot(robot);
        this.baseClient.getFlotte().addRobot(robot2);
        this.baseClient.getFlotte().addRobot(robot3);
    }



    @Test
    public void removeRobotTest() {

        flotteController.removeRobot("Bender");
        assertEquals(baseClient.getFlotte().getRobots().size(),2);
    }


    @Test
    public void enregistrerRobotTest() {
        String[] infos3= {"hajaaj", "pipi", "lehauler"};

        Bras bras = new Bras();
        Roue roue = new Roue();
        CPU cpu = new CPU();

        ArrayList<Composante> composantess= new ArrayList<Composante>();
        composantess.add(bras); composantess.add(roue); composantess.add(cpu);
        Robot robottest = new Robot(infos3,100,false,composantess);

        flotteController.enregistrerRobot(robottest);
        assertEquals(baseClient.getFlotte().getRobots().size(),3);

    }


    @Test
    public void vueGeneraleTest() {
        // Creer un ByteArrayOutputStream pour capturer l'output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;

        try {
            // Rediriger System.out a outContent
            System.setOut(new PrintStream(outContent));

            // methode a tester
            flotteController.vueGenerale(this.baseClient.getFlotte());

            // test
            String expectedstr = "Robot: Bender type: bender batterie restante: 100\n" + "Robot: hasouss type: pipi batterie restante: 100\n" + "Robot: hajaaj type: pipi batterie restante: 100\n\r\n";
            assertEquals(expectedstr, outContent.toString());

        } finally {
            // Restaurer System.out dans tous les cas
            System.setOut(originalOut);
        }
    }

}

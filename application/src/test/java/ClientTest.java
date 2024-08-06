import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ClientTest {

    private Client client;
    private MainController mainController = new MainController();

    @BeforeEach
    public void setUp() {
        client = new Client("client1", "mdp");
    }

    /**
     * Test pour ajouter une notification.
     */
    @Test
    public void testAjouterNotification() {
        client.addNotification(new Notification("Denis", "Robot pile à plat"));
        assertEquals(1, client.notifications.size());
        assertEquals("Denis", client.notifications.get(0).getFrom());
    }

    /**
     * Test pour ajouter une notification sans envoyeur.
     */
    @Test
    public void testAjouterNotificationNull() {
        client.addNotification(new Notification("Robot brisé"));
        List<Notification> notif = client.getNotifications();
        assertEquals(1, notif.size());
        assertEquals(notif.get(0).getFrom(), "UNKNOWN");
    }

    /**
     * Test pour ajouter plusieurs composantes.
     */
    @Test
    public void testAjouterPlusieursComposantes() {
        String[] infos3= {"hajaaj", "pipi", "lehauler"};
        client.setNotifications(null);
        testAjouterNotification();
        mainController.currentUser = client;
        // Creer un ByteArrayOutputStream pour capturer l'output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;

        try {
            // Rediriger System.out a outContent
            System.setOut(new PrintStream(outContent));

            // methode a tester
            mainController.menuClient.displayPageNotifications(client);

            // test
            String expectedstr = "-- VOS NOTIFICATIONS --\nDenis: Robot pile à plat";
            Assert.assertEquals(expectedstr, outContent.toString());

        } finally {
            // Restaurer System.out dans tous les cas
            System.setOut(originalOut);
        }
    }
}


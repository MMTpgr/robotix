import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MainController {


    // Singleton
    private static MainController _instance;

    public static MainController getInstance(){
        if (_instance == null){
            _instance = new MainController();
        }
        return _instance;
    }

    private MainController(){

    }


    // -------------------------- Controller and Repositories --------------------------

    private final ActiviteController activiteController =  ActiviteController.getInstance();
    private final ActiviteRepository activiteRepository = activiteController.getRepository();
    private final ClientController clientController =  ClientController.getInstance();
    private final ClientRepository clientRepository = ClientController.getRepository();
    private final FournisseurController fournisseurController = FournisseurController.getInstance();
    private final FournisseurRepository fournisseurRepository = fournisseurController.getRepository();
    private final  ComposanteController composanteController =  ComposanteController.getInstance();
    private final ComposanteRepository composanteRepository = composanteController.getRepository();
    private FlotteController flotteController;
    private final MenuConnexion menu = MenuConnexion.getInstance();
    private final MenuClient menuClient = MenuClient.getInstance();
    //MenuFournisseur
    MenuFournisseur menuFournisseur = MenuFournisseur.getInstance();

    // -------------------------- START --------------------------

    private Utilisateur currentUser = null;

    public Utilisateur getCurrentUser(){
        return this.currentUser;
    }

    public void start(){

        System.out.println(activiteRepository.getActivites().size());

        // Connexion or login here
        int choixConnexion = menu.displayPageStart();

        switch (choixConnexion){
            case 0:
                // Sauvegarder les données, puis quitter
                fournisseurRepository.writeFournisseurs();
                activiteRepository.writeActivites();
                clientRepository.writeClient();
                System.exit(0);
            case 1:
            case 3:
                connexion();
                break;
            case 2:
                menu.displayPageInscriptionClient();
                break;
            default:
                menu.displayPageInscriptionFournisseur();
                break;
        }

        if (currentUser instanceof Client){
            // Affiche le menu principal du client
            menuPrincipalClient((Client) currentUser);
        } else {
            // Affiche le menu principal du fournisseur
            menuPrincipalFournisseur((Fournisseur) currentUser);
        }
    }


    public void writeDefaultValues(){


        // ------------------------- Clients ------------------------

        // --- client1 ---
        Client client1 = new Client("toto", "bobo");
        String[] infos1 = {"Bender", "bender", "KD-09290"};
        client1.getFlotte().addRobot( new Robot(infos1, 100, false,
                new ArrayList<>(Arrays.asList(new CPU(), new Bras(), new Camera(), new HautParleur())) ));

        String[] infos2 = {"AlphaTron", "Assistant domestique", "AT-00123"};
        client1.getFlotte().addRobot( new Robot(infos2, 18, false,
                new ArrayList<>(Arrays.asList(new CPU(), new Bras(), new Camera(), new Roue())) ));

        // --- client2 ---
        Client client2 = new Client("coco", "lolo");
        String[] infos3 = {"BetaGuard", "Sécurité résidentielle", "BG-00456"};
        client2.getFlotte().addRobot( new Robot(infos3, 92, false,
                new ArrayList<>(Arrays.asList(new CPU(), new Bras(), new Camera(), new Micro(), new Roue()))));
        String[] infos4 = {"GammaCook", "Chef culinaire", "GC-00789"};
        client2.getFlotte().addRobot( new Robot(infos4, 82, false,
                new ArrayList<>(Arrays.asList(new CPU(), new Bras(), new HautParleur(), new Helice()))));

        // --- client3 ---
        Client client3 = new Client("popo", "momo");
        String[] infos5 = {"DeltaMed", "Infirmier médical", "DM-01012"};
        client3.getFlotte().addRobot( new Robot(infos5, 92, false,
                new ArrayList<>(Arrays.asList(new CPU(), new Bras(), new Camera(), new Micro(), new Ecran()))));
        String[] infos6 = {"EpsilonClean", "Nettoyage industriel", "EC-01345"};
        client3.getFlotte().addRobot( new Robot(infos6, 98, false,
                new ArrayList<>(Arrays.asList(new CPU(), new Bras(), new Camera(), new Roue()))));

        // --- client4 ---
        Client client4 = new Client("Guillaume", "Frisbee");
        String[] infos7 = {"ZetaBot", "Robot éducatif", "ZB-01678"};
        client4.getFlotte().addRobot( new Robot(infos7, 45, false,
                new ArrayList<>(Arrays.asList(new CPU(), new Bras(), new Micro(), new Ecran()))));
        String[] infos8 = {"EtaGard", "Jardinier automatisé", "EG-01901"};
        client4.getFlotte().addRobot( new Robot(infos8, 32, false,
                new ArrayList<>(Arrays.asList(new CPU(), new Bras(), new Helice(), new Ecran()))));

        // --- client5 ---
        Client client5 = new Client("Nikolas", "Crypto");
        String[] infos9 = {"ThetaLab", "Assistant de laboratoire", "TL-02234"};
        client5.getFlotte().addRobot( new Robot(infos9, 72, false,
                new ArrayList<>(Arrays.asList(new CPU(), new Bras(), new Micro(), new HautParleur()))));
        String[] infos10 = {"IotaBuild", "Ouvrier de construction", "IB-02567"};
        client5.getFlotte().addRobot( new Robot(infos10, 64, false,
                new ArrayList<>(Arrays.asList(new CPU(), new Bras(), new Micro(), new Roue()))));

        // --- client6 ---
        Client client6 = new Client("Jordan", "VFX");
        String[] infos11 = {"KappaDrone", "Drone de surveillance", "KD-02890"};
        client6.getFlotte().addRobot( new Robot(infos11, 64, false,
                new ArrayList<>(Arrays.asList(new CPU(), new Helice(), new Micro(), new Camera(), new Ecran()))));
        String[] infos12 = {"LambdaLift", "Exosquelette d'assistance", "LL-03123"};
        client6.getFlotte().addRobot( new Robot(infos12, 82, false,
                new ArrayList<>(Arrays.asList(new CPU(), new Bras(), new Roue(), new Camera()))));

        // --- client7 ---
        Client client7 = new Client("Medhi", "Couscous");
        String[] infos13 = {"MuPet", "Animal de compagnie robotique", "MP-03456"};
        client7.getFlotte().addRobot( new Robot(infos13, 42, false,
                new ArrayList<>(Arrays.asList(new CPU(), new Bras(), new Roue(), new Camera(), new Micro()))));
        String[] infos14 = {"NuComm", "Communication avancée", "NC-03789"};
        client7.getFlotte().addRobot( new Robot(infos14, 34, false,
                new ArrayList<>(Arrays.asList(new CPU(), new Roue(), new Camera(), new Micro(), new Ecran()))));

        // --- client8 ---
        Client client8 = new Client("Amal", "DaQueen");
        String[] infos15 = {"XiCare", "Assistant aux personnes âgées", "XC-04112"};
        client8.getFlotte().addRobot( new Robot(infos15, 81, false,
                new ArrayList<>(Arrays.asList(new CPU(), new Bras(), new Roue(), new Camera(), new Micro()))));
        String[] infos16 = {"OmicronClean", "Nettoyage de vitres", "OC-04445"};
        client8.getFlotte().addRobot( new Robot(infos16, 18, false,
                new ArrayList<>(Arrays.asList(new CPU(), new Bras(), new Roue(), new Camera(), new Micro(), new Ecran()))));

        // --- client9 ---
        Client client9 = new Client("Johnny", "test");
        String[] infos17 = {"PiFix", "Réparateur de systèmes", "PF-04778"};
        client9.getFlotte().addRobot( new Robot(infos17, 59, false,
                new ArrayList<>(Arrays.asList(new CPU(), new Roue(), new Camera(), new Micro(), new Helice()))));
        String[] infos18 = {"RhoGuard", "Robot de sécurité publique", "RG-05101"};
        client9.getFlotte().addRobot( new Robot(infos18, 46, false,
                new ArrayList<>(Arrays.asList(new CPU(), new Roue(), new Camera(), new Micro(), new Helice(), new Ecran()))));

        // --- client10 ---
        Client client10 = new Client("Gateau", "vanille");
        String[] infos19 = {"SigmaChef", "Chef de cuisine gastronomique", "SC-05434"};
        client10.getFlotte().addRobot( new Robot(infos19, 82, false,
                new ArrayList<>(Arrays.asList(new CPU(), new Roue(), new Bras(), new Micro(), new Helice()))));
        String[] infos20 = {"TauTutor", "Tuteur éducatif", "TT-05767"};
        client10.getFlotte().addRobot( new Robot(infos20, 76, false,
                new ArrayList<>(Arrays.asList(new CPU(), new Roue(), new Bras(), new Micro(), new Helice(), new Ecran()))));

        ArrayList<Client> clients = new ArrayList<>(Arrays.asList(client1, client2, client3, client4, client5,
                                                    client6, client7, client8, client9, client10));
        clientRepository.addClients(clients);



        // ------------------------- Fournisseur ------------------------

        // --- Fournisseur1 ---

        Fournisseur fournisseur1 = new Fournisseur("SKF Group", "sadwf");

        CPU cpu1 = new CPU();
        cpu1.setNom("dasdas");
        cpu1.setPrix(25);
        Roue roue1 = new Roue();
        roue1.setNom("dawheel");
        roue1.setPrix(68);
        Bras bras1 = new Bras();
        bras1.setNom("chestbras");
        bras1.setPrix(76);
        HautParleur hautParleur1 = new HautParleur();
        hautParleur1.setNom("celine");
        hautParleur1.setPrix(100);
        Ecran ecran1 = new Ecran();
        ecran1.setNom("Samsung");
        ecran1.setPrix(200);
        composanteController.enregistrerComposante(fournisseur1, cpu1);
        composanteController.enregistrerComposante(fournisseur1, roue1);
        composanteController.enregistrerComposante(fournisseur1, bras1);
        composanteController.enregistrerComposante(fournisseur1, hautParleur1);
        composanteController.enregistrerComposante(fournisseur1, ecran1);

        // --- Fournisseur2 ---

        Fournisseur fournisseur2 = new Fournisseur("Bosch Rexroth", "gfdyger");
        CPU cpu2 = new CPU();
        cpu2.setNom("sdadas");
        cpu2.setPrix(95);
        Helice helice1 = new Helice();
        helice1.setNom("helice1");
        helice1.setPrix(65);
        Bras bras2 = new Bras();
        bras2.setNom("Pipe");
        bras2.setPrix(68);
        Micro micro1 = new Micro();
        micro1.setNom("crachoire");
        micro1.setPrix(54);
        HautParleur hautParleur2 = new HautParleur();
        hautParleur2.setNom("JohnnyHoliday");
        hautParleur2.setPrix(999);
        composanteController.enregistrerComposante(fournisseur2, cpu2);
        composanteController.enregistrerComposante(fournisseur2, helice1);
        composanteController.enregistrerComposante(fournisseur2, bras2);
        composanteController.enregistrerComposante(fournisseur2, micro1);
        composanteController.enregistrerComposante(fournisseur2, hautParleur2);


        // --- Fournisseur3 ---

        Fournisseur fournisseur3 = new Fournisseur("Nappa", "fsdfsefe");
        CPU cpu3 = new CPU();
        cpu3.setNom("capu");
        cpu3.setPrix(56);
        CPU cpu4 = new CPU();
        cpu4.setNom("capuplus");
        cpu4.setPrix(560);
        Roue roue2 = new Roue();
        roue2.setNom("vroomvroom");
        roue2.setPrix(45);
        Roue roue3 = new Roue();
        roue3.setNom("vroooom");
        roue3.setPrix(87);
        Helice helice2 = new Helice();
        helice2.setNom("helicopterius");
        helice2.setPrix(100);
        composanteController.enregistrerComposante(fournisseur3, cpu3);
        composanteController.enregistrerComposante(fournisseur3, cpu4);
        composanteController.enregistrerComposante(fournisseur3, roue2);
        composanteController.enregistrerComposante(fournisseur3, roue3);
        composanteController.enregistrerComposante(fournisseur3, helice2);

        // --- Fournisseur4 ---
        Fournisseur fournisseur4 = new Fournisseur("KIA", "grthgerge");
        Bras bras3 = new Bras();
        bras3.setNom("Manchoke");
        bras3.setPrix(72);
        Ecran ecran2 = new Ecran();
        ecran2.setNom("Roku");
        ecran2.setPrix(75);
        Camera camera1 = new Camera();
        camera1.setNom("Sony");
        camera1.setPrix(500);
        HautParleur hautParleur3 = new HautParleur();
        hautParleur3.setNom("blabla");
        hautParleur3.setPrix(45);
        CPU cpu5 = new CPU();
        cpu5.setNom("dsdds");
        cpu5.setPrix(30);
        composanteController.enregistrerComposante(fournisseur4, bras3);
        composanteController.enregistrerComposante(fournisseur4, ecran2);
        composanteController.enregistrerComposante(fournisseur4, camera1);
        composanteController.enregistrerComposante(fournisseur4, hautParleur3);
        composanteController.enregistrerComposante(fournisseur4, cpu5);


        // --- Fournisseur5 ---
        Fournisseur fournisseur5 = new Fournisseur("FORD", "hty4ujerth");
        Helice helice3 = new Helice();
        helice3.setNom("windy");
        helice3.setPrix(65);
        CPU cpu6 = new CPU();
        cpu6.setNom("wewdw");
        cpu6.setPrix(82);
        Ecran ecran3 = new Ecran();
        ecran3.setNom("Toshiba");
        ecran3.setPrix(65);
        Bras bras4 = new Bras();
        bras4.setNom("Tchad");
        bras4.setPrix(62);
        Roue roue = new Roue();
        roue.setNom("wheelo");
        roue.setPrix(81);
        composanteController.enregistrerComposante(fournisseur5, helice3);
        composanteController.enregistrerComposante(fournisseur5, cpu6);
        composanteController.enregistrerComposante(fournisseur5, ecran3);
        composanteController.enregistrerComposante(fournisseur5, bras4);
        composanteController.enregistrerComposante(fournisseur5, roue);


        ArrayList<Fournisseur> fournisseurs = new ArrayList<>(Arrays.asList(fournisseur1, fournisseur2, fournisseur3,
                                                                fournisseur4, fournisseur5));

        fournisseurRepository.addFournisseurs(fournisseurs);


        // ------------------------- Activites ------------------------

        // ---Activite numero 1---
        LocalDate date = LocalDate.of(2024, 10, 6);

        Activite activite1 = new Activite("Menage chez Jordan",
                date,
                "Menage de la cuisine et du salon",
                "Jordan",
                ACTIVITEETAT.NONDEBUTEE);
        activite1.setPopularite(0);
        Tache tache1 = new Tache("Balayeuse");

        ArrayList<ComposanteType> composanteTypes1 = new ArrayList<>();
        composanteTypes1.add(ComposanteType.ROUE);
        Action action1 = new Action("Avancer", composanteTypes1);

        ArrayList<ComposanteType> composanteTypes2 = new ArrayList<>();
        composanteTypes2.add(ComposanteType.BRAS);
        Action action2 = new Action("Aspirer", composanteTypes2);

        tache1.addAction(action2, 0);
        tache1.addAction(action1, 0);

        activite1.addTache(tache1, 0);

        // ---Activite numero 2---
        LocalDate date1 = LocalDate.of(2024, 8, 23);

        Activite activite2 = new Activite("Chasse au trésor",
                date1,
                "Recherche de trésors enfoui",
                "Dora",
                ACTIVITEETAT.NONDEBUTEE);
        activite2.setPopularite(4);
        Tache tache2 = new Tache("Chercher");

        ArrayList<ComposanteType> composanteTypes3 = new ArrayList<>();
        composanteTypes3.add(ComposanteType.ROUE);
        Action action3 = new Action("Avancer", composanteTypes3);

        ArrayList<ComposanteType> composanteTypes4 = new ArrayList<>();
        composanteTypes4.add(ComposanteType.CAMERA);
        Action action4 = new Action("Analyser", composanteTypes4);


        ArrayList<ComposanteType> composanteTypes5 = new ArrayList<>();
        composanteTypes5.add(ComposanteType.BRAS);
        Action action5 = new Action("Creuser", composanteTypes5);

        tache2.addAction(action3, 0);
        tache2.addAction(action4, 0);
        tache2.addAction(action5, 0);

        activite2.addTache(tache2, 0);

        // ---Activite numero 3---
        LocalDate date2 = LocalDate.of(2024, 7, 25);

        Activite activite3 = new Activite("Captation Ultimate Frisbee",
                date2,
                "Captation d'un match de ultimate Frisbee.",
                "Guillaume",
                ACTIVITEETAT.NONDEBUTEE);
        activite2.setPopularite(4);
        Tache tache3 = new Tache("Camera Drone");

        ArrayList<ComposanteType> composanteTypes6 = new ArrayList<>();
        composanteTypes6.add(ComposanteType.HELICE);
        Action action6 = new Action("Voler", composanteTypes6);

        ArrayList<ComposanteType> composanteTypes7 = new ArrayList<>();
        composanteTypes7.add(ComposanteType.CAMERA);
        Action action7 = new Action("Enregistrer", composanteTypes7);


        tache3.addAction(action6, 0);
        tache3.addAction(action7, 0);


        Tache tache4 = new Tache("Camera Ground");

        ArrayList<ComposanteType> composanteTypes8 = new ArrayList<>();
        composanteTypes8.add(ComposanteType.ROUE);
        Action action8 = new Action("Avancer", composanteTypes8);

        ArrayList<ComposanteType> composanteTypes9 = new ArrayList<>();
        composanteTypes9.add(ComposanteType.CAMERA);
        Action action9 = new Action("Enregistrer", composanteTypes9);

        tache4.addAction(action8, 0);
        tache4.addAction(action9, 0);


        Tache tache5 = new Tache("Enregistrer Son");
        ArrayList<ComposanteType> composanteTypes10 = new ArrayList<>();
        composanteTypes10.add(ComposanteType.MICRO);
        Action action10 = new Action("Enregistrer", composanteTypes10);

        tache5.addAction(action10, 0);

        activite3.addTache(tache3, 0);
        activite3.addTache(tache4, 0);
        activite3.addTache(tache5, 0);


        // ---Activite numero 4---
        LocalDate date3 = LocalDate.of(2024, 7, 22);

        Activite activite4 = new Activite("Revision Examen",
                date3,
                "Robot de support lors de la revision.",
                "MEDHI",
                ACTIVITEETAT.TERMINEE);
        activite2.setPopularite(8);
        Tache tache6 = new Tache("Poser une question.");

        ArrayList<ComposanteType> composanteTypes11 = new ArrayList<>();
        composanteTypes11.add(ComposanteType.ECRAN);
        Action action11 = new Action("Question Visuelle", composanteTypes11);

        ArrayList<ComposanteType> composanteTypes12 = new ArrayList<>();
        composanteTypes12.add(ComposanteType.HAUTPARLEUR);
        Action action12 = new Action("Question Audio", composanteTypes12);

        tache6.addAction(action11, 0);
        tache6.addAction(action12, 0);

        activite4.addTache(tache6, 0);


        // ---Activite numero 5---

        LocalDate date4 = LocalDate.of(2024, 8, 4);

        Activite activite5 = new Activite("Course Robots",
                date4,
                "Course de robots sur 100 mètres.",
                "Nikolas",
                ACTIVITEETAT.ENCOURS);
        activite2.setPopularite(10);

        Tache tache7 = new Tache("Rouler.");
        ArrayList<ComposanteType> composanteTypes13 = new ArrayList<>();
        composanteTypes13.add(ComposanteType.ROUE);
        Action action13 = new Action("Question Visuelle", composanteTypes13);


        tache7.addAction(action13, 0);
        activite5.addTache(tache7, 0);


        // ---Activite numero 6---


        LocalDate date5 = LocalDate.of(2024, 8, 20);

        Activite activite6 = new Activite("Ateliers mecanique",
                date5,
                "Journee d'atelier sur la mecanique des robots",
                "Nappa",
                ACTIVITEETAT.NONDEBUTEE);
        activite2.setPopularite(10);
        Tache tache8 = new Tache("Reparation");
        ArrayList<ComposanteType> composanteTypes14 =
                new ArrayList<>( Arrays.asList(ComposanteType.CPU,
                        ComposanteType.ROUE, ComposanteType.BRAS,

                        ComposanteType.HAUTPARLEUR, ComposanteType.ECRAN,
                        ComposanteType.HELICE));
        Action action14 = new Action("Support Visuelle", composanteTypes14);

        tache8.addAction(action14, 0);

        activite6.addTache(tache8, 0);

        // ------------------------- Inscription Activites ------------------------
        activiteController.inscriptionClient(client1, activite1);
        activiteController.inscriptionClient(client1, activite2);
        activiteController.inscriptionClient(client1, activite3);
        activiteController.inscriptionClient(client1, activite4);
        activiteController.inscriptionClient(client1, activite5);

        activiteController.inscriptionClient(client2, activite1);
        activiteController.inscriptionClient(client2, activite2);
        activiteController.inscriptionClient(client2, activite3);
        activiteController.inscriptionClient(client2, activite4);
        activiteController.inscriptionClient(client2, activite6);

        activiteController.inscriptionClient(client3, activite1);
        activiteController.inscriptionClient(client3, activite2);
        activiteController.inscriptionClient(client3, activite3);
        activiteController.inscriptionClient(client3, activite4);
        activiteController.inscriptionClient(client3, activite5);

        activiteController.inscriptionClient(client4, activite1);
        activiteController.inscriptionClient(client4, activite2);
        activiteController.inscriptionClient(client4, activite3);
        activiteController.inscriptionClient(client4, activite4);
        activiteController.inscriptionClient(client4, activite6);

        activiteController.inscriptionClient(client5, activite1);
        activiteController.inscriptionClient(client5, activite2);
        activiteController.inscriptionClient(client5, activite3);
        activiteController.inscriptionClient(client5, activite4);
        activiteController.inscriptionClient(client5, activite6);

        activiteController.inscriptionClient(client6, activite1);
        activiteController.inscriptionClient(client6, activite2);
        activiteController.inscriptionClient(client6, activite3);
        activiteController.inscriptionClient(client6, activite4);
        activiteController.inscriptionClient(client6, activite5);

        activiteController.inscriptionClient(client7, activite1);
        activiteController.inscriptionClient(client7, activite2);
        activiteController.inscriptionClient(client7, activite3);
        activiteController.inscriptionClient(client7, activite4);
        activiteController.inscriptionClient(client7, activite6);

        activiteController.inscriptionClient(client8, activite1);
        activiteController.inscriptionClient(client8, activite2);
        activiteController.inscriptionClient(client8, activite3);
        activiteController.inscriptionClient(client8, activite4);
        activiteController.inscriptionClient(client8, activite5);

        activiteController.inscriptionClient(client9, activite1);
        activiteController.inscriptionClient(client9, activite2);
        activiteController.inscriptionClient(client9, activite3);
        activiteController.inscriptionClient(client9, activite4);
        activiteController.inscriptionClient(client9, activite6);

        activiteController.inscriptionClient(client10, activite1);
        activiteController.inscriptionClient(client10, activite2);
        activiteController.inscriptionClient(client10, activite3);
        activiteController.inscriptionClient(client10, activite4);
        activiteController.inscriptionClient(client10, activite5);


        ArrayList<Activite> activites = new ArrayList<>(Arrays.asList(activite1, activite2, activite3, activite4, activite5, activite6));
        activiteRepository.addActivites(activites);




        clientRepository.writeClient();
        activiteRepository.writeActivites();
        fournisseurRepository.writeFournisseurs();



    }






    // -------------------------- CONNEXION/SIGN-IN --------------------------

    /**
     * Connexion a un compte Robotix.
     * Client ou Fournisseur
     */
    public void connexion(){
        boolean validUser = false;
        boolean validPassword = false;

        menu.displayPageConnexion();

        Scanner keyb = new Scanner(System.in);

        // Demander username
        while (!validUser){
            String username = keyb.nextLine();

            // Si utilisateur veux quitter
            if (username.equals("0")){
                currentUser = null;
                start();
            }

            // Chercher à travers les users
            for (Client client : clientRepository.getClients()){
                if (client.getUsername().equals(username)){
                    validUser = true;
                    currentUser = client;
                }
            }

            // Chercher à travers les fournisseurs
            for (Fournisseur fourn : fournisseurRepository.getFournisseurs()){
                if (fourn.getUsername().equals(username)){
                    validUser = true;
                    currentUser = fourn;
                }
            }
        }

        while (!validPassword){
            System.out.print("Mot de passe: ");
            String password = keyb.nextLine();

            if (password.equals("0")){
                currentUser = null;
                start();
            }

            if (!currentUser.getPassword().equals(password)){
                System.out.print("\nMot de passe erroné.");
            } else {validPassword = true;}
        }

    }

    // -------------------------- Client --------------------------
    // Version pour fournisseur vers le milieu / fin de ce document

    /**
     * Logique du menu Principal d'utilisateur
     * Selon le choix du client, menu flotte, menu activites, menu marketplace, afficher les notifications
     * ou retour au menu de connexion.
     * @param client
     */
    public void menuPrincipalClient(Client client){

        String pick = menuClient.displayPagePrincipal(client);

        switch (pick) {
            case "1":
                MenuFlotte(client);
                break;
            case "2":
                this.menuActivitesPrincipal(client);
                break;
            case "3":
                menuMarketPlacePrincipal(client);
            case "4":

            case "-":
                this.start();

        }

    }


    // -------------------------- MarketPlace --------------------------

    /**
     * Logique du menu MarketPlace.
     * Selon le choix du client, le menu de recherche de composantes/fournisseurs sera atteint ou
     * retour au menu principal.
     *
     * @param client Le client.
     */
    public void menuMarketPlacePrincipal(Client client){

        String pick = menuClient.displayPageMarket(client);
        ArrayList<Composante> composantes;
        ArrayList<Fournisseur> fournisseurs;

        switch (pick){
            case "1":
                composantes = composanteRepository.getComposantes();
                this.menuRechercheComposante(client, composantes);
            case "2":
                fournisseurs = fournisseurRepository.getFournisseurs();
                this.menuRechercheFournisseurs(client, fournisseurs);
            case "-":
                this.menuPrincipalClient(client);
        }
    }

    /**
     * Logique du menu de Recherche de Composante.
     * Selon le choix du client, affichage de la fiche d'une activité, filtrage
     * des activités ou retour au menu de recherche de composantes.
     *
     * @param client Le client
     * @param composantes Liste de Composante à afficher/filtrer.
     */
    public void menuRechercheComposante(Client client, ArrayList<Composante> composantes){

        String pick = menuClient.displayPageRechercheComposante(composantes);


        switch (pick){

            case "!":
                Composante.filterComposantes(composantes, ComposanteFilter.NAME);
                this.menuRechercheComposante(client, composantes);
            case "#":
                Composante.filterComposantes(composantes, ComposanteFilter.TYPE);
                this.menuRechercheComposante(client, composantes);
            case "*":
                Composante.filterComposantes(composantes, ComposanteFilter.FOURNISSEUR);
                this.menuRechercheComposante(client, composantes);
            case "-":
                this.menuMarketPlacePrincipal(client);
            default:
                Composante choosenComposante = composantes.get(Integer.parseInt(pick)-1);

                // For now checking if this is a sold composante with composante.getFournisseur()
                if (choosenComposante.getFournisseur() == null){
                    this.menuFicheComposante(choosenComposante);
                } else {
                    this.menuFicheAchatComposante(choosenComposante);
                }
                menuRechercheComposante(client, composanteRepository.getComposantes());
        }

    }

    /**
     * Logique d'une fiche d'une composante.
     *
     * @param composante Composante duqelle afficher la fiche.
     */
    public void menuFicheComposante(Composante composante){
        menuClient.displayPageFicheComposante(composante);
    }

    /**
     * Logique d'une fiche d'achat d'une composante.
     *
     * @param composante Composante duqelle afficher la fiche.
     */
    public void menuFicheAchatComposante(Composante composante){

        String pick = menuClient.displayPageFicheAchatComposante(composante);

        switch (pick){
            case "+":
            //composanteController.achatComposante(this.currentUser, composante);
            case "-":
                return;
        }
    }

    /**
     * Logique du menu de recherche de Fournisseurs.
     * Selon le choix du client, afficher la fiche d'un fournisseur, filtrage
     * des fournisseurs ou retour au menu MarketPlace.
     *
     * @param client Le client.
     * @param fournisseurs Liste de Fournisseur à afficher/filtrer.
     */
    public void menuRechercheFournisseurs(Client client, ArrayList<Fournisseur> fournisseurs){

        String pick = menuClient.displayPageRechercherFournisseur(fournisseurs);

        switch (pick){

            case "!":
                Fournisseur.filterFournisseurs(fournisseurs, FournisseurFilter.NOM);
                menuRechercheFournisseurs(client, fournisseurs);
            case "#":
                Fournisseur.filterFournisseurs(fournisseurs, FournisseurFilter.TYPECOMPOSANTES);
                menuRechercheFournisseurs(client, fournisseurs);
            case "-":
                this.menuMarketPlacePrincipal(client);
            default:
                Fournisseur choosenFournisseur = fournisseurs.get(Integer.parseInt(pick));
                this.menuFicheFournisseur(client, choosenFournisseur);
                menuRechercheFournisseurs(client,  fournisseurs);
        }
    }

    /**
     * Logique d'une fiche d'un fournisseur.
     * Selon le choix du client, afficher les composantes du founisseur ou retour
     * au menu de recherche de fournisseur.
     *
     * @param client Le client
     * @param fournisseur Fournisseur duquel afficher la fiche.
     */
    public void menuFicheFournisseur(Client client, Fournisseur fournisseur){

        String pick = menuClient.displayPageFicheFournisseur(fournisseur);

        switch (pick) {
            case "+":
                this.menuRechercheComposante(client, fournisseur.getComposantes());
                this.menuFicheFournisseur(client, fournisseur);
            case "-":
        }
    }



    // -------------------------- Activite --------------------------

    /**
     * Logique du menu principal des activités.
     * Selon le choix du client, afficher le menu de recherche d'activité du repertoir/des
     * activités du client ou retour au menu principal.
     *
     * @param client le Client.
     */
    public void menuActivitesPrincipal(Client client){

        String pick = menuClient.displayPageActivite(client);
        switch (pick){

            // ---------------- Mes Activites ----------------
            case "1":
                this.menuRechercheActivites(client, activiteController.activitesNametoList(client.getActivites()));
            // ---------------- Toutes Activites ----------------
            case "2":
                this.menuRechercheActivites(client,
                        activiteRepository.getActivites());
            case "-":
                this.menuPrincipalClient(client);
        }
        }

    /**
     * Logique du menu de recherche d'activité.
     * Selon le choix du client, affichage d'une fiche d'activité, filtrage ou
     * retour au menu principal des activités.
     *
     * @param client
     * @param activites
     */
    public void menuRechercheActivites(Client client, ArrayList<Activite> activites){

        String pick;

        pick = menuClient.displayPageRechercheActivite(activites);

        switch (pick){
            // ---- FILTERS ----
            case "!":
                Activite.sortActivites(activites, ActiviteFilter.NOM);
                this.menuRechercheActivites(client, activites);
            case "#":
                Activite.sortActivites(activites, ActiviteFilter.DATE);
                this.menuRechercheActivites(client, activites);
            case "*":
                Activite.sortActivites(activites, ActiviteFilter.POPULARITE);
                this.menuRechercheActivites(client, activites);
            // ---- QUITTER ----
            case "-":
                this.menuActivitesPrincipal(client);
            // ---- CHOOSED AN ACTIVITE ----
            default:
                Activite choosenActivite = activites.get(Integer.parseInt(pick)-1);

                boolean alreadySubscribed = false;
                for(Activite act : activiteController.activitesNametoList(client.getActivites())){
                    if (act.equals(choosenActivite)){
                        alreadySubscribed = true;
                        break;
                    }
                }

                this.MenuFicheActivite(client, choosenActivite, alreadySubscribed);
                this.menuRechercheActivites(client, activites);
        }

    }

    /**
     * Logique d'une fiche d'activité.
     * Selon le choix du client, si il est deja inscrit, il peut se désinscrire, si il n'est pas inscrit, il peut
     * s'inscrire ou retour au menu de recherche d'activité.
     *
     * @param client Le client.
     * @param activite Activité duquelle afficher la fiche.
     * @param alreadySubscribed Client déja inscrit ou non.
     */
    public void MenuFicheActivite(Client client, Activite activite, boolean alreadySubscribed){

        String pick;

        pick = menuClient.displayPageFicheActivite(activite, alreadySubscribed);

        switch (pick){
        case "1":
            if (alreadySubscribed){
                activiteController.desinscriptionClient(client, activite);
                MenuFicheActivite(client, activite, false);
            } else {
                boolean succes = activiteController.inscriptionClient(client, activite);

                if (succes){
                    MenuFicheActivite(client, activite, true);
                } else {
                    menuClient.displayPagecomposanteManquantes(activite);
                    MenuFicheActivite(client, activite, false);
                }
            }
        case "-":
        }
    }

    /**
     * Logique du menu Flotte
     *
     * @param client Le client.
     */
    public void MenuFlotte(Client client){
        String pick;
        FlotteController flotteController = FlotteController.getInstance(client);

        pick = menuClient.displayPageFlotte(client);

        switch (pick){
            case "1" :
                Robot robot = flotteController.createRobot(client.getComposantes());
                if (!(robot==null)) {
                    flotteController.enregistrerRobot(robot);
                }

                MenuFlotte(client);
                break;

            case "2" :
                Scanner scanner = new Scanner(System.in);
                System.out.println("Entrez le nom du robot que vous voulez supprimer");
                String inputName = scanner.nextLine();

                flotteController.removeRobot(inputName);
                MenuFlotte(client);
                break;


            case "3" :
                Scanner subscanner = new Scanner(System.in);
                System.out.println("souhaitez vous une vue Generale ou une vue complete?");
                System.out.println("1. vue generale");
                System.out.println("2. vue complete");
                String subpick = subscanner.nextLine();
                if (subpick.equals("1")){
                    flotteController.vueGenerale(client.getFlotte());
                }
                else if (subpick.equals("2")){
                    flotteController.vueComplete(client.getFlotte());
                }
                else {
                    System.out.println("prenez un choix valide sinon consequences");
                }
                MenuFlotte(client);

            case "-":
                menuPrincipalClient(client);
        }
    }


    // -------------------------- Fournisseur --------------------------
    /**
     * Affiche le menu principal pour le Fournisseur et gère les choix de l'utilisateur.
     *
     * @param fournisseur le Fournisseur
     */
    public void menuPrincipalFournisseur(Fournisseur fournisseur){

        String pick = menuFournisseur.displayPagePrincipal(fournisseur);

        switch (pick) {
            case "1":
                menuProfil(fournisseur);
                break;
            case "2":
                menuGestionComposantes(fournisseur);
                break;
            case "3":
                menuEnregisterComposante(fournisseur);
                break;
            case "-":
                this.start();
        }

    }
    //menuProfil est mentionné dans DM3, mais pas à compléter.
    /**
     * Affiche le menu de gestion du profil pour le Fournisseur et gère les choix de l'utilisateur.
     *
     * @param fournisseur le Fournisseur
     */
    public void menuProfil(Fournisseur fournisseur){
        String pick;
        pick = menuFournisseur.displayPageProfil(fournisseur);

        switch (pick){
            case "1" :
                //nouveau nom d'utilisateur
                break;

            case "2" :
                //nouveau mot de passe
                break;

            case "3" :
                //nouveau courriel
                break;

            case "-":
                //retour aux menus
                menuPrincipalFournisseur(fournisseur);
                break;
        }
    }

    /**
     * Affiche le menu de gestion des composantes pour le Fournisseur et gère les choix de l'utilisateur.
     *
     * @param fournisseur le Fournisseur
     */
    public void menuGestionComposantes(Fournisseur fournisseur) {
        String pick;
        pick = menuFournisseur.displayPageGestionComposante(fournisseur);

        switch (pick) {
            case "1":
                // Affiche toutes les composantes du fournisseur
                for (Composante comp : fournisseur.getComposantes()) {
                    System.out.println(comp);
                }
                menuGestionComposantes(fournisseur);
            case "2":
                // Supprime une composante au choix
                Scanner scan = new Scanner(System.in);
                System.out.print("Entrez le nom de la composante à supprimer: ");
                String nomASupprimer = scan.nextLine();
                fournisseur.supprimerComposante(nomASupprimer);
                System.out.println("Composante supprimée avec succès!");
                menuGestionComposantes(fournisseur);
            case "3":
                // Modifier une composante
                Scanner scan2 = new Scanner(System.in);

                for (Composante comp : fournisseur.getComposantes()) {
                    System.out.println(comp);
                }
                System.out.println();
                System.out.print("Entrez le nom de la composante à modifier: ");
                String nomAModifier = scan2.nextLine();

                ArrayList<String> composanteNames = new ArrayList<>();


                for(Composante composante : fournisseur.getComposantes()){
                    composanteNames.add(composante.getNom());
                }

                if(!composanteNames.contains(nomAModifier)){
                    System.out.println("Composante non existante!");
                    menuGestionComposantes(fournisseur);
                }

                fournisseur.supprimerComposante(nomAModifier);
                System.out.print("Entrez le nouveau nom de la composante: ");
                String nouveauNom = scan2.nextLine();
                System.out.print("Entrez le nouveau type de la composante: ");
                String nouveauType = scan2.nextLine();
                System.out.print("Entrez la nouvelle description de la composante: ");
                String nouvelleDescription = scan2.nextLine();
                String nouveauPrix = "";

                while (true) {
                    System.out.print("Entrez le nouveau prix de la composante: (Integer)");
                    nouveauPrix = scan2.nextLine();

                    try {
                        int number = Integer.parseInt(nouveauPrix);
                        break; // Exit the loop if the input is a valid integer
                    } catch (NumberFormatException e) {
                        System.out.println("That's not a valid integer. Please try again.");
                    }
                }

                fournisseur.ajouterComposante(new Composante(nouveauNom, nouveauType, nouvelleDescription, nouveauPrix));
                System.out.println("Composante modifiée avec succès!");
                menuGestionComposantes(fournisseur);
            case "-":
                menuPrincipalFournisseur(fournisseur);
        }
    }


    /**
     * Affiche le menu d'enregistrement de composante pour le Fournisseur et gère les choix de l'utilisateur.
     *
     * @param fournisseur le Fournisseur
     */
    public void menuEnregisterComposante(Fournisseur fournisseur) {
        String pick;
        pick = menuFournisseur.displayPageEnregisterComposante(fournisseur);

        switch (pick) {
            case "1":
                // Enregistrer nouvelle composante
                Scanner scan = new Scanner(System.in);
                System.out.print("Entrez le nom de la composante: ");
                String nom = scan.nextLine();
                System.out.print("Entrez le type de la composante: ");
                String type = scan.nextLine();
                System.out.print("Entrez la description de la composante: ");
                String description = scan.nextLine();
                String prix = "";

                while (true) {
                    System.out.print("Entrez le prix de la composante: (Integer)");
                    prix = scan.nextLine();

                    try {
                        int number = Integer.parseInt(prix);
                        break; // Exit the loop if the input is a valid integer
                    } catch (NumberFormatException e) {
                        System.out.println("That's not a valid integer. Please try again.");
                    }
                }

                composanteController.enregistrerComposante(fournisseur, new Composante(nom, type, description, prix));
                System.out.println("Composante ajoutée avec succès!");
                menuEnregisterComposante(fournisseur);
            case "-":
                menuPrincipalFournisseur(fournisseur);
                break;
        }
    }
}

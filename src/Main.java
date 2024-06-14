// Prototype du logiciel de gestion des robots.
// Instructions en lignes de commande

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Classe principale du programme - Sera exécutée par le fichier .jar
 */
public class Main {
    public static void main(String[] args){

        String pick1;
        Scanner scan = new Scanner(System.in);

        System.out.println("\t-------------------------\n" +
                "\t- RoboTix        " +
                "\n\t-\n\t- 1. Se connecter\n\t- 2. S'inscrire\n\t-\n" +
                "\t- *CodeSniffers123" + (char)153 + "*" +
                "\n\t-------------------------");
        pick1 = scan.nextLine();

        while (!pick1.equals("1") && !pick1.equals("2")){
            System.out.print("Veuillez entrer un choix valide: ");
            pick1 = scan.nextLine();
        }

        String username;
        String password;

        if (pick1.equals("1")){
            System.out.print("Nom d'utilisateur: ");
            username = scan.nextLine();

            System.out.print("Mot de passe: ");
            password = scan.nextLine();
        } else {
            System.out.print("Choisissez un nom d'utilisateur: ");
            username = scan.nextLine();

            System.out.print("Choisissez un mot de passe: ");
            password = scan.nextLine();
        }

        // Hard-coding quelques robots
        Robot robot1 = new Robot(new String[]{"Robot 1", "Type 1", "290891"});
        Robot robot2 = new Robot(new String[]{"Robot 2", "Type 3", "236412"});
        Robot robot3 = new Robot(new String[]{"Robot 3", "Type 1", "826423"});

        // Hard-coding des activités
        Activite act1 = new Activite("Course de voitures", new Robot[]{robot1, robot3});
        Activite act2 = new Activite("Trajet en vol", new Robot[]{robot2});

        // Hard-coding un user
        Utilisateur user = new Client(username, password);
        user.addRobot(robot1);
        user.addRobot(robot2);
        user.addRobot(robot3);

        user.addActivite(act1);
        user.addActivite(act2);

        menuPrincipal(user);
    }

    public static void menuPrincipal(Utilisateur user){

        String pick2;
        Scanner scan2 = new Scanner(System.in);

        System.out.println("\n\n\t-------------------------\n" +
                "\t- Bonjour " + user.getUsername() + "!" +
                "\n\t-\n\t- 1. Voir ma flotte\n\t- 2. Activités\n\t- 3. MarketPlace\n" +
                "\t-\n\t-------------------------");

        pick2 = scan2.nextLine();
        while (!pick2.equals("1") && !pick2.equals("2") && !pick2.equals("3")){
            System.out.print("Veuillez entrer un choix valide: ");
            pick2 = scan2.nextLine();
        }

        switch (Integer.parseInt(pick2)){
            case 1:
                menuFlotte(user);
                break;
            case 2:
                menuActivite(user);
                break;
            default:
                menuMarket(user);
        }

    }

    public static void menuFlotte(Utilisateur user){

        String pick2;
        Scanner scan2 = new Scanner(System.in);

        System.out.println("\n\n\t-------------------------\n" +
                "\t- Flotte:" +
                "\n\t-\n\t-");
        for (Robot robot : user.robots) {
            System.out.println("\t- " + robot + ": État = Disponible. Batterie = 100%");
        }
        System.out.println("\t-\n\t-------------------------\n\t- 1. Enregistrer un robot" +
                "\n\t- 2. Créer une action\n\t- 3. Créer une tâche pour un robot\n");


        pick2 = scan2.nextLine();
        while (!pick2.equals("1") && !pick2.equals("2") && !pick2.equals("3")){
            System.out.print("Veuillez entrer un choix valide: ");
            pick2 = scan2.nextLine();
        }

        switch (Integer.parseInt(pick2)){
            case 1:
                System.out.print("Nom du nouveau robot: ");
                String name = scan2.nextLine();
                System.out.println("Nouveau robot créé: " + name + "!!");
                break;
            case 2:
                System.out.print("Description de la nouvelle activité: ");
                name = scan2.nextLine();
                System.out.println("Nouvelle activité créée: " + name + "!!");
                break;
            default:
                System.out.print("Description de la nouvelle tâche: ");
                name = scan2.nextLine();
                System.out.println("Nouvelle tâche créée: " + name + "!!");
        }
        menuPrincipal(user);

    }
    public static void menuActivite(Utilisateur user){

        String pick2;
        Scanner scan2 = new Scanner(System.in);

        System.out.println("\n\nACTIVITÉS: \n");
        for (Activite act : user.activites) {
            System.out.println("*" + act.desc + "*");
            for (Robot rob : act.robotsInclus) {
                System.out.println("\t" + rob);
            }
            System.out.println();
        }
        System.out.println("\t-\n\t-------------------------\n\t- 1. S'inscrire à une activité\n" +
                "\n\t- 2. Organiser une activité\n");

            pick2 = scan2.nextLine();
        while (!pick2.equals("1") && !pick2.equals("2")){
            System.out.print("Veuillez entrer un choix valide: ");
            pick2 = scan2.nextLine();
        }

        switch (Integer.parseInt(pick2)){
            case 1:
                String[] acts = new String[]{"1- Course annuelle de robot 2024 (21-08-2024)",
                        "2- Concours de vol plané (11-09-2024)", "3- Compétition de robot ludique! (30-09-2024)"};
                for (String act : acts){
                    System.out.println(act);
                }
                System.out.print("Entrez votre choix d'activité: ");
                String choix = scan2.nextLine();
                System.out.println("Vous êtes inscrit à: " + acts[Integer.parseInt(choix)] + "!!");
                break;
            case 2:
                System.out.print("Nom de votre événement: ");
                String name = scan2.nextLine();
                System.out.print("Description de votre événement: ");
                String desc = scan2.nextLine();
                System.out.println("Nouvel événement créé: " + name + " - " + desc + "!!");
                break;
        }
        menuPrincipal(user);

    }
    public static void menuMarket(Utilisateur user){

        String pick2;
        Scanner scan2 = new Scanner(System.in);

        System.out.println("\n\n\t-------------------------\n" +
                "\t- Marketplace:" +
                "\n\t-\n\t-");

        System.out.println("\t- 1. Acheter des pièces" +
                "\n\t- 2. Vendre des pièces\n\t- 3. Voir la liste " +
                "des fournisseurs\n\t-\n\t-------------------------\n\t");


        pick2 = scan2.nextLine();
        while (!pick2.equals("1") && !pick2.equals("2") && !pick2.equals("3")){
            System.out.print("Veuillez entrer un choix valide: ");
            pick2 = scan2.nextLine();
        }

        switch (Integer.parseInt(pick2)){
            case 1:
                String[] pieces = new String[]{"1- Moteur - 150$",
                        "2- Roues - 20$", "3- Hélice - 40$"};
                for (String act : pieces){
                    System.out.println(act);
                }
                System.out.print("Entrez la pièce que vous désirez acheter: ");
                String choix = scan2.nextLine();
                System.out.println("Vous avez acheté un: " + pieces[Integer.parseInt(choix)].substring(2) + "!!");
                break;
            case 2:
                System.out.print("Nom de votre pièce: ");
                String name = scan2.nextLine();
                System.out.print("Prix de votre pièce: ");
                String desc = scan2.nextLine();
                System.out.println("Annonce créée: " + name + " - " + desc + "$ !!");
                break;
            default:
                String[] fourn = new String[]{"Camfil Coop - 514 872 9308",
                        "Napa Pièces auto - 514 273 5655", "Gsabb11 - 514 999 9999"};
                for (String act : fourn){
                    System.out.println(act);
                }
                System.out.print("Appuyer sur 'entrée' pour quitter' ");
                String input = scan2.nextLine();
        }
        menuPrincipal(user);

    }
}

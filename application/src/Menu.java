import java.util.Scanner;

public class Menu {

    // Singleton
    private static Menu _instance;

    public static Menu getInstance(){
        if (_instance == null){
            _instance = new Menu();
        }
        return _instance;
    }

    // -------------------------- MAIN MENU --------------------------


    public void displayPageStart(){

        // Here we choose between connexion ou inscription

    }

    public void displayPageConnexion(){

    }

    public void displayPageInscriptionClient(){

    }

    public void displayPageInscriptionFournisseur(){

    }


    public void displayPagePrincipalClient(Client user) {

        String pick2;
        Scanner scan2 = new Scanner(System.in);

        System.out.println("\n\n\t-------------------------\n" +
                "\t- Bonjour " + user.getUsername() + "!" +
                "\n\t-\n\t- 1. Voir ma flotte\n\t- 2. Activités\n\t- 3. MarketPlace" +
                "\n\t- 4. Trouver des intérêts\n\t- 5. Parcourir des utilisateurs" +
                "\n\t- 6. Se déconnecter" +
                "\t\n\t( Vos points: " + user.points + " - Votre classement: 1er )" +
                "\n\t-------------------------");

        pick2 = scan2.nextLine();
        while (Integer.parseInt(pick2) < 1 || Integer.parseInt(pick2) > 6) {
            System.out.print("Veuillez entrer un choix valide: ");
            pick2 = scan2.nextLine();
        }

        switch (Integer.parseInt(pick2)) {
            case 1:
                displayPageFlotte(user);
                break;
            case 2:
                displayPageActivite(user);
                break;
            case 3:
                displayPageMarket(user);
            case 4:
                displayPageInterets(user);
            case 5:
                displayPageAbonnements(user);
            case 6:
                interets.clear();
                users.clear();
                main(null);
        }

    }


    public void displayPagePrincipalFournisseur(){

    }

    // -------------------------- ROBOTS --------------------------

    public void displayPageFlotte(Utilisateur user) {

        String pick2;
        Scanner scan2 = new Scanner(System.in);

        System.out.println("\n\n\t-------------------------\n" +
                "\t- Flotte:" +
                "\n\t-\n\t-");
        for (Robot robot : user.robots) {
            String etat = "";
            if (robot.brise) {
                etat = "brisé. ";
            } else
                etat = "en bon état. ";
            System.out.println("\t- " + robot + ": Robot " + etat + "Batterie = " + robot.batterie + "%");
        }
        System.out.println("\t-\n\t-------------------------\n\t- 1. Enregistrer un robot" +
                "\n\t- 2. Créer une action\n\t- 3. Créer une tâche pour un robot\n\n" + //
                "\t- 4. Quitter\n");

        pick2 = scan2.nextLine();
        while (!pick2.equals("1") && !pick2.equals("2") && !pick2.equals("3")
                && !pick2.equals("4")) {
            System.out.print("Veuillez entrer un choix valide: ");
            pick2 = scan2.nextLine();
        }

        switch (Integer.parseInt(pick2)) {
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
            case 3:
                System.out.print("Description de la nouvelle tâche: ");
                name = scan2.nextLine();
                System.out.println("Nouvelle tâche créée: " + name + "!!");
            case 4:
                displayPagePrincipal(user);
        }
        displayPagePrincipal(user);

    }


    public void displayPageEnregistrerRobot(){

    }


    public void displayPageSupprimerRobot(){

    }

    // -------------------------- ACTIVITES --------------------------

    public void displayPageActivite(Utilisateur user) {

        String pick2;
        Scanner scan2 = new Scanner(System.in);

        System.out.println("\n\nACTIVITÉS: \n");
        for (Activite act : user.activites) {
            System.out.println("*" + act.getDesc() + "*");
            for (Robot rob : act.getRobotsInclus()) {
                System.out.println("\t" + rob);
            }
            System.out.println();
        }
        System.out.println("\t-\n\t-------------------------\n\t- 1. S'inscrire à une activité" +
                "\n\t- 2. Organiser une activité\n\t- 3. Quitter");

        pick2 = scan2.nextLine();
        while (!pick2.equals("1") && !pick2.equals("2") && !pick2.equals("3")) {
            System.out.print("Veuillez entrer un choix valide: ");
            pick2 = scan2.nextLine();
        }

        switch (Integer.parseInt(pick2)) {
            case 1:
                String[] acts = new String[] { "1- Course annuelle de robot 2024 (21-08-2024)",
                        "2- Concours de vol plané (11-09-2024)", "3- Compétition de robot ludique! (30-09-2024)" };
                for (String act : acts) {
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
            case 3:
                displayPagePrincipal(user);
        }
        displayPagePrincipal(user);

    }


    public void displayPageInscriptionActivite(Utilisateur user){

        String pick2;
        Scanner scan2 = new Scanner(System.in);

    }

    public void displayPageFilterActivite(){

    }


    public void displayPageFicheActivite(Utilisateur utilisateur, Activite activite){

        String pick2;
        Scanner scan2 = new Scanner(System.in);

    }

    // -------------------------- MARKETPLACE --------------------------

    public void displayPageMarket(Utilisateur user) {

        String pick2;
        Scanner scan2 = new Scanner(System.in);

        System.out.println("\n\n\t-------------------------\n" +
                "\t- Marketplace:" +
                "\n\t-\n\t-");

        System.out.println("\t- 1. Acheter des pièces" +
                "\n\t- 2. Vendre des pièces\n\t- 3. Voir la liste " +
                "des fournisseurs\n\t- 4. Quitter\n\t-------------------------\n\t");

        pick2 = scan2.nextLine();
        while (!pick2.equals("1") && !pick2.equals("2") && !pick2.equals("3")
                && !pick2.equals("4")) {
            System.out.print("Veuillez entrer un choix valide: ");
            pick2 = scan2.nextLine();
        }

        switch (Integer.parseInt(pick2)) {
            case 1:
                String[] pieces = new String[] { "1- Moteur - 150$",
                        "2- Roues - 20$", "3- Hélice - 40$" };
                for (String act : pieces) {
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
            case 3:
                String[] fourn = new String[] { "Camfil Coop - 514 872 9308",
                        "Napa Pièces auto - 514 273 5655", "Gsabb11 - 514 999 9999" };
                for (String act : fourn) {
                    System.out.println(act);
                }
                System.out.print("Appuyer sur 'entrée' pour quitter' ");
                String input = scan2.nextLine();
            case 4:
                displayPagePrincipal(user);
        }
        displayPagePrincipal(user);

    }

    public void displayPageFicheFournisseur(){

    }

    public void displayPageRechercherFournisseur() {
    }

    public void displayPageRechercheComposante(){

    }

    public void displayPageFicheComposante(){

    }

    public void displayPageVendreComposante(){

    }


    // -------------------------- UTILISATEURS --------------------------

    public void displayPageFicheUtilisateur(){

    }

    public void displayPageRechercherUtilisateur(){

    }

    public void displayPageModifierSonProfil(){

    }


    // -------------------------- INTERETS --------------------------

    public void displayPageInterets(Utilisateur user) {
        System.out.println("\tListe des intérêts:");
        for (Interet interet : interets) {
            System.out.println((interets.indexOf(interet) + 1) + " --- " + interet.nom);
        }
        Scanner clav = new Scanner(System.in);
        boolean fini = false;
        while (!fini) {
            System.out.print("\tPour adhérer à un intérêt, entrez son numéro (sinon, entrez 0) : ");
            int choix = clav.nextInt();
            while (choix > interets.size() || choix < 0) {
                System.out.println("Entrez un choix valide!");
                clav.reset();
                System.out.print("\tPour adhérer à un intérêt, entrez son numéro (sinon, entrez 0) : ");
                choix = clav.nextInt();
            }
            if (choix == 0) {
                fini = true;
            } else {
                if (user.interets.contains(interets.get(choix - 1))) {
                    System.out.println("Vous avez déjà cet intérêt! \n");
                } else {
                    System.out.println("Vous avez choisi l'intérêt: " + interets.get(choix - 1).getNom() + "\n");
                    user.addInteret(interets.get(choix - 1));
                }
            }
        }
        displayPagePrincipal(user);

    }

    public void displayPageAbonnements(Utilisateur user) {
        System.out.println("\tVous pourriez connaître:");
        for (Utilisateur u : users) {
            System.out.println((users.indexOf(u) + 1) + " --- " + u.getUsername());
        }
        Scanner clav = new Scanner(System.in);
        boolean fini = false;
        while (!fini) {
            System.out.print("\tPour vous abonner à un utilisateur, entrez son numéro (sinon, entrez 0) : ");
            int choix = clav.nextInt();
            while (choix > users.size() || choix < 0) {
                System.out.println("Entrez un choix valide!");
                clav.reset();
                System.out.print("\tPour vous abonner à un utilisateur, entrez son numéro (sinon, entrez 0) : ");
                choix = clav.nextInt();
            }
            if (choix == 0) {
                fini = true;
            } else {
                System.out.println("Vous êtes abonné à: " + users.get(choix - 1).getUsername() + "\n");
            }
        }
        displayPagePrincipal(user);
    }


    public void displayPageNotifications(){

    }

}

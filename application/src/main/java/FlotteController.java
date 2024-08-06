import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class FlotteController {

    // Singleton
    private static FlotteController _instance;

    private static Flotte _flotte;
    private static ArrayList<Composante> _composantes;

    public static FlotteController getInstance(Client client){
        if (_instance == null){
            _instance = new FlotteController();
            _flotte = client.getFlotte();
            _composantes = client.getComposantes();
        }
        return _instance;
    }


    private FlotteController(){

    }

    /**
     * Affichage Menu general de metrique flotte
     *
     * @param flotte La flotte
     */
    public void vueGenerale(Flotte flotte){
        String message = "";
        for (Robot r : flotte.getRobots()){
            message += "Robot: "+r.getNom() +" type: " +r.getType() + " batterie restante: "+r.getBatterie() +"\n";
        }
        System.out.println(message);
    }

    /**
     * Affichage Menu complete de metrique flotte
     *
     * @param flotte La flotte
     */
    public void vueComplete(Flotte flotte){
        String message = "";
        for (Robot r : flotte.getRobots()){
            message+= "numéro de série: "+r.getNumSerie() +" position: " +r.getPosition() + " vitesse: "+r.getVitesse() + " consommation CPU et mémoire: " + r.getConsommationCPU() +"\n";
        }
        System.out.println(message);
    }

    /**
     * Retrait d'un robot de la flotte du Client
     *
     * @param name nom du robot
     */
    public void removeRobot(String name){
        boolean isSuppr = false;
        ArrayList<Robot> listeRobots = _flotte.getRobots();

        Robot theRobot = null;

        for (int  i=0; i<listeRobots.size();i++ ){
            if(listeRobots.get(i).getNom().equals(name)){
                theRobot = listeRobots.get(i);
                listeRobots.remove(i);
                isSuppr=true;
            }
        }

        if (isSuppr) {

            if (!(theRobot==null)) {
                Client client = (Client) MainController.getInstance().getCurrentUser();
                client.getComposantes().addAll(theRobot.getComposantes());
            }
            _flotte.setRobots(listeRobots);
            AfficherValidationSupprRobot();
        }
        else {
            System.out.println("Ce robot n'éxiste pas");
        }
    }

    /**
     * Enregistrement d'un robot a la flotte du client.
     *
     * @param robot
     */
    public void enregistrerRobot(Robot robot){
        _flotte.addRobot(robot);
        System.out.println("robot enregistré avec succès!");
    }

    /**
     * Creation d'un robot a partir de composantes et d'information pour le robot.
     *
     * @param composantes Liste de composantes.
     * @return Le robot.
     */
    public Robot createRobot(ArrayList<Composante> composantes){
        String nom ="";
        String type;
        String numSerie;
        int batterie;
        boolean prise;
        boolean cpuStable;

        String pick="";

        Scanner scan = new Scanner(System.in);

        System.out.println("Comment appelleriez  vous votre robot?");
        do {
            nom = scan.nextLine();
            if (nom.length()==0){
                System.out.println("Entrez un nom valide");
            }
        } while (nom.length()==0);

        System.out.println("Quel sera son type?");
        do {
            type = scan.nextLine();
            if (type.length()==0){
                System.out.println("Entrez un type valide");
            }
        } while (nom.length()==0);


        System.out.println("Entrez votre numéro de sèrie");

        do {
            numSerie = scan.nextLine();
            if (numSerie.length()==0){
                System.out.println("Entre un numéro de sèrie valide");
            }
        } while (nom.length()==0);

        if(_composantes.size()<2){
            System.out.println("Vous n'avez pas assez de composantes pour créer un robot ");

        }
        else {
            System.out.println("Quelles composantes souhaitez vous intégrer? (Veuillez entrer le nom de la composante");


            ArrayList<Composante> composantesChoisies = new ArrayList<Composante>();
            ArrayList<Composante> CopieInit = new ArrayList<Composante>(_composantes);


            while (true) {
                for (int i = 0; i < _composantes.size(); i++) {
                    System.out.println((i + 1) + ". " + _composantes.get(i).getNom());
                }

                System.out.println("- Créer le robot");

                pick = scan.nextLine();
                while (pick != "-") {

                    if (isInList(_composantes, pick)) {
                        Composante compo = removeComp(_composantes, pick);
                        composantesChoisies.add(compo);
                        for (int i = 0; i < _composantes.size(); i++) {
                            System.out.println((i + 1) + ". " + _composantes.get(i).getNom());
                        }
                        System.out.println("- Créer le robot");
                    } else {
                        if (pick.equals("-")) {
                            break;
                        } else {
                            System.out.println("Entrez un choix valide");
                        }
                    }
                    pick = scan.nextLine();

                }
                boolean presenceCPU = false;
                boolean presenceComp = false;


                for (Composante comp : composantesChoisies) {

                    if (comp.getNom().equals("CPU")) {
                        presenceCPU = true;
                    }
                    if (!comp.getNom().equals("CPU")) {
                        presenceComp = true;
                    }
                }

                System.out.println((composantesChoisies.size() >= 2 && presenceCPU && presenceComp));

                if (composantesChoisies.size() >= 2 && presenceCPU && presenceComp) {
                    String[] infosRobot = {nom, type, numSerie};
                    Robot robot = new Robot(infosRobot, 100, false, composantesChoisies);
                    _flotte.addRobot(robot);
                    AfficherValidationCreationRobot();
                    return robot;


                } else {
                    System.out.println("Choix de composantes invalide");

                    _composantes.clear();
                    _composantes.addAll(CopieInit);

                    System.out.println(CopieInit.size());
                    composantesChoisies = new ArrayList<Composante>();


                }
            }
        }
        return null;
    }

    /**
     * Retrait d'une composantes parmi une liste de composante.
     *
     * Choix selon le nom de la composante.
     *
     * @param arr Liste de composante.
     * @param str Nom de la composante a retirer.
     * @return La composante retirer.
     */
    private Composante removeComp (ArrayList<Composante> arr,String str ){

        for (int i=0;i<arr.size(); i++ ){
            Composante comp = arr.get(i);
            if (comp.getNom().equals(str)) {
                arr.remove(i);
                return comp;
            }

        }
        return null;
    }

    /**
     * Verifie si une composante fait partis d'une liste de composante.
     * verification selon le nom de la composante..
     *
     * @param arr Liste de composante.
     * @param str Nom de la composante a retirer.
     * @return true or false selon la presence de la composante.
     */
    private boolean isInList(ArrayList<Composante> arr,String str){
        boolean isIn = false;

        for (Composante c : arr){
            if (c.getNom().equals(str)) {
                isIn = true;
            }
        }
        return isIn;
    }

    /**
     * Getter d'un robot parmis une flotte de robot.
     * Choix selon le nom du robot.
     *
     * @param name Nom du robot
     * @return Le robot.
     */
    public Robot getRobot(String name){
        Robot robot = null;

        for (Robot rob : _flotte.getRobots()){
            if (rob.getNom().equals(name)){
                robot = rob;
            }
        }
        // Do Something
        return robot;
    }

    /**
     * Affichage de validation de la creation d'un robot
     */
    public void AfficherValidationCreationRobot(){
        System.out.println("Robot ajouté avec succes!");
    }

    /**
     * Affichage de validation de suppression d'un robot.
     */
    public void AfficherValidationSupprRobot(){
        System.out.println("Robot supprimé avec succes!");
    }

}

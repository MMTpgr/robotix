import java.util.ArrayList;

public class Flotte {

    private ArrayList<Robot> robots = new ArrayList<>();

    private  ArrayList<String> metriques = new ArrayList<>();

    // -------------------------- GETTER SETTER --------------------------

    public ArrayList<Robot> getRobots() {
        return robots;
    }

    public void addRobot(Robot robot) {this.robots.add(robot);}

    public void setRobots(ArrayList<Robot> robots) {
        this.robots = robots;
    }

    public ArrayList<String> getMetriques() {
        return metriques;
    }

    public void setMetriques(ArrayList<String> metriques) {
        this.metriques = metriques;
    }


    // -------------------------- UTILS METHODS --------------------------

}

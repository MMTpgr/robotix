import java.util.ArrayList;

public class Action {

    private String name;
    private Robot robotAssignee;
    private ArrayList<Composante> composantesRequises;

    public String getName() {
        return name;
    }

    public Action(String name, ArrayList<Composante> composantesRequises)
    {
        this.name = name;
        this.composantesRequises = composantesRequises;
    }

    // -------------------------- GETTER SETTER --------------------------
    public void setName(String name) {
        this.name = name;
    }

    public Robot getRobotAssignee() {
        return robotAssignee;
    }

    public void setRobotAssignee(Robot robotAssignee) {
        this.robotAssignee = robotAssignee;
    }

    public ArrayList<Composante> getComposantesRequises() {
        return composantesRequises;
    }

    public void setComposantesRequises(ArrayList<Composante> composantesRequises) {
        this.composantesRequises = composantesRequises;
    }
}

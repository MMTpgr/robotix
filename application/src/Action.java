import java.util.ArrayList;

public class Action {

    private String name;
    private String type;
    private Robot robotAssignee;
    private ArrayList<Composante> composantesRequises;

    public String getName() {
        return name;
    }

    public Action(String name, String type, ArrayList<Composante> composantesRequises)
    {
        this.name = name;
        this.type = type;
        this.composantesRequises = composantesRequises;
    }

    // -------------------------- GETTER SETTER --------------------------
    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

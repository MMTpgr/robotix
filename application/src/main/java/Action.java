import java.util.ArrayList;

public class Action {

    private String name;
    private Robot robotAssignee;
    private ArrayList<ComposanteType> composantesRequises;

    public String getName() {
        return name;
    }

    public Action(String name, ArrayList<ComposanteType> composantesRequises)
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

    public ArrayList<ComposanteType> getComposantesRequises() {
        return composantesRequises;
    }

    public void setComposantesRequises(ArrayList<ComposanteType> composantesRequises) {
        this.composantesRequises = composantesRequises;
    }
}

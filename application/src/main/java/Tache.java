import java.util.ArrayList;
import java.util.LinkedList;

public class Tache {

    private String name;

    private Robot robot;

    ArrayList<Action> actions;


    public Tache(String name){
        this.name = name;
        this.actions = new ArrayList<>();
    }

    public ArrayList<ComposanteType> getComposantesRequises() {

        ArrayList<ComposanteType> composantesRequise = new ArrayList<>();

            for (Action action: this.actions){
                composantesRequise.addAll(action.getComposantesRequises());
            }

        return composantesRequise;
    }

    public void addAction(Action action, int order){

        for (Action a : this.actions){
            if (a.getName().equals(action.getName())){
                System.out.println("Tache avec nom: " + action.getName() + " deja presente. Renommer la tache.");
                return;
            }
        }
        this.actions.add(order, action);
    }

    public void removeAction(String name){

        Action action = this.getActionByName(name);

        if ((action==null)){
            System.out.println("Tache non presente.");
            return;
        }

        this.actions.remove(action);
    }


    public Action getActionByName(String name){
        Action action = null;

        for (Action a : this.actions){
            if (a.getName().equals(name)){
                action = a;
                break;
            }
        }
        return action;
    }

    // Getter Setter --------------------------
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Robot getRobot() {
        return robot;
    }

    public void setRobot(Robot robot) {
        this.robot = robot;
    }

    public ArrayList<Action> getActions() {
        return actions;
    }

    public void setActions(ArrayList<Action> actions) {
        this.actions = actions;
    }
}

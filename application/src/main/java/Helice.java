public class Helice extends Composante{

    public Helice(){
        this.actionsPossibles = new String[]{"Tourner"};
        this.type = ComposanteType.HELICE;
    }

    public String getNom(){
        return "HELICE";
    }
}

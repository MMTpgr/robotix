public class Micro extends Composante{
    public Micro() {
        this.actionsPossibles = new String[]{"Enregistrer"};
        this.type = ComposanteType.MICRO;
    }

    public String getNom(){
        return "MICRO";
    }
}

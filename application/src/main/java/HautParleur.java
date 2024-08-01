public class HautParleur extends Composante{
    public HautParleur(){
        this.actionsPossibles = new String[]{"Effet sonore"};
        this.type = ComposanteType.HAUTPARLEUR;
    }

    public String getNom(){
        return "HAUTPARLEUR";
    }
}

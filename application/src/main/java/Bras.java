public class Bras extends Composante {
    public Bras(){
        this.actionsPossibles = new String[]{"Attraper", "Dancer", "Appuyer", "Saluer"};
        this.type = ComposanteType.BRAS;
    }

    public String getNom(){
        return "BRAS";
    }
}

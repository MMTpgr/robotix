public class Bras extends Composante {
    private ComposanteType type = ComposanteType.BRAS;
    public Bras(){
        this.actionsPossibles = new String[]{"Attraper", "Dancer", "Appuyer", "Saluer"};
    }
}

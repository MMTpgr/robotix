public class Ecran extends Composante{

    public Ecran(){
        this.actionsPossibles = new String[]{"Afficher"};
        this.type = ComposanteType.ECRAN;
    }

    public String getNom(){
        return "ECRAN";
    }
}

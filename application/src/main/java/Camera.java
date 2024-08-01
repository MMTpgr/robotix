public class Camera extends Composante {
    public Camera(){
        this.actionsPossibles = new String[]{"Filmer", "Prendre une photo"};
        this.type = ComposanteType.CAMERA;
    }

    public String getNom(){
        return "CAMERA";
    }
}

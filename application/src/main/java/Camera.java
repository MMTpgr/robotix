public class Camera extends Composante {
    private ComposanteType type = ComposanteType.CAMERA;
    public Camera(){
        this.actionsPossibles = new String[]{"Filmer", "Prendre une photo"};
    }
}

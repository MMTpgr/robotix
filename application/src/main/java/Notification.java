public class Notification {

    private String from;

    private String message;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return this.getFrom() + ": " + this.getMessage();
    }

    public Notification(String from, String message){
        this.from = from;
        this.message = message;
    }

    public Notification(String message){
        this.message = message;
        this.from = "UNKNOWN";
    }
}

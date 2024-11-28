package models;

public class Message {
    private String text;
    private boolean sentByMe;

    // Constructor vacío necesario para Firebase
    public Message() {
    }

    public Message(String text, boolean sentByMe) {
        this.text = text;
        this.sentByMe = sentByMe;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) { // Setter necesario para Firebase
        this.text = text;
    }

    public boolean isSentByMe() {
        return sentByMe;
    }

    public void setSentByMe(boolean sentByMe) { // Setter necesario para Firebase
        this.sentByMe = sentByMe;
    }

    @Override
    public String toString() {
        return "Message{" +
                "text='" + text + '\'' +
                ", sentByMe=" + sentByMe +
                '}';
    }
}

package models;

public class Chat {
    private String id;
    private String nickName;
    private String lastMessage;
    private String email;
    private String phoneNumber;
    private boolean unread; // Indica si hay mensajes no leídos en el chat

    // Constructor completo
    public Chat(String id, String nickName, String lastMessage, String email, String phoneNumber, boolean unread) {
        this.id = id;
        this.nickName = nickName;
        this.lastMessage = lastMessage;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.unread = unread;
    }

    // Constructor vacío
    public Chat() {
    }

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isUnread() {
        return unread;
    }

    public void setUnread(boolean unread) {
        this.unread = unread;
    }

    @Override
    public String toString() {
        return "Chat{" +
                "id='" + id + '\'' +
                ", nickName='" + nickName + '\'' +
                ", lastMessage='" + lastMessage + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", unread=" + unread +
                '}';
    }
}

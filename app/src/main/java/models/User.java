package models;

public class User {

    private String idUsuario;
    private String correo;
    private String contraseña;
    private String Nickname;

    public User() {
    }

    public User(String idUsuario, String correo, String contraseña, String nickname) {
        this.idUsuario = idUsuario;
        this.correo = correo;
        this.contraseña = contraseña;
        Nickname = nickname;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getNickname() {
        return Nickname;
    }

    public void setNickname(String nickname) {
        Nickname = nickname;
    }

    @Override
    public String toString() {
        return "User{" +
                "idUsuario='" + idUsuario + '\'' +
                ", correo='" + correo + '\'' +
                ", contraseña='" + contraseña + '\'' +
                ", Nickname='" + Nickname + '\'' +
                '}';
    }
}
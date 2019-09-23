public class ChatMessage {

    private String usuario;

    private String mensaje;

    public ChatMessage(String usuario, String mensaje){
        this.mensaje = mensaje;
        this.usuario = usuario;
    }

    public String getMensaje() {
        return mensaje;
    }

    public String getUsuario() {
        return usuario;
    }
}

import java.util.ArrayList;
import java.util.List;

public class ChatList {
    private List<ChatMessage> listaMensajes = new ArrayList<>();

    public void agregarMensaje(String usuario, String mensaje){
        listaMensajes.add(new ChatMessage(usuario, mensaje));
    }

    public String getMensaje(int posicion){
        StringBuilder usuarioMensaje = new StringBuilder();
        usuarioMensaje.append(listaMensajes.get(posicion).getUsuario());
        usuarioMensaje.append(": ");
        usuarioMensaje.append(listaMensajes.get(posicion).getMensaje());
        return usuarioMensaje.toString();
    }

    public int numeroMensajes(){
        int numero = 0;
        if(!(listaMensajes.isEmpty())){
            numero = listaMensajes.size();
        }
        return numero;
    }
}

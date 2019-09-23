import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public final class Main {

    private static final int Puerto = 9000;

    private static ChatList mensajes = new ChatList();

    public static void main(final String[] args) throws IOException{

        final ServerSocket servidor = new ServerSocket(Puerto);

        while(true){
            try(final Socket cliente = servidor.accept()){
                respuesta(cliente);
            }catch (IOException e){
                throw e;
            }
        }
    }

    public static void respuesta(Socket cliente) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
        PrintWriter out = new PrintWriter(cliente.getOutputStream());
        out.println("HTTP/1.1 200 OK");
        out.println("Server: DSM v0.0.1");
        out.println("Content-Type: text/html; charset=UTF-8");
        out.println("\r\n");
        out.println("<textarea rows=\"10\" cols=\"70\" readonly=\"readonly\">");
        for(int i = 0; i < mensajes.numeroMensajes(); i++){
            out.println(mensajes.getMensaje(i)+"<br/>");
        }
        out.println("</textarea>");
        out.println("<form method='POST'>");
        out.println("Nick<input name ='name'/>");
        out.println("Mensaje<input name ='msg'/>");
        out.println("<input type=\"submit\" value=\"Submit\"/>");
        out.println("</form>");
        out.flush();
        String mensaje;
        while((mensaje = in.readLine()) != null){
            if(mensaje.startsWith("name")){
                guardarMensaje(mensaje);
            }
        }
    }

    public static void guardarMensaje(String mensaje){
        char[] contenedor = mensaje.toCharArray();
        int contador = -1;
        String nombreUsuario = null;
        String mensajeUsuario = null;
        StringBuilder borrador = new StringBuilder();
        for(int i = 0; i < mensaje.length(); i++){
            if(contenedor[i] == '='){
                contador = i+1;
            }
            if(i == contador){
                if(contenedor[i] == '&'){
                    nombreUsuario = borrador.toString();
                    borrador = null;
                    contador = -1;
                }else{
                    if(contenedor[i] == '+'){
                        borrador.append(' ');
                    }else{
                        borrador.append(contenedor[i]);
                    }
                }
                contador++;
            }
        }
        mensajeUsuario = borrador.toString();
        mensajes.agregarMensaje(nombreUsuario, mensajeUsuario);
    }
}

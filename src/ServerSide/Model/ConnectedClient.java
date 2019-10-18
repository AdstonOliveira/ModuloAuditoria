package ServerSide.Model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author adston
 */
public class ConnectedClient {

    public ConnectedClient(Socket socket) {
        this.socket = socket;
        this.init();
    }

    private Socket socket;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;

    private void init(){
        try {
            this.oos = new ObjectOutputStream(this.socket.getOutputStream());
            this.ois = new ObjectInputStream(this.socket.getInputStream());
        } catch (IOException ex) {
            System.out.println("Erro ao abrir Streams L26-L27");
            Logger.getLogger(ConnectedClient.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public ObjectOutputStream getOos() {
        return oos;
    }

    public void setOos(ObjectOutputStream oos) {
        this.oos = oos;
    }

    public ObjectInputStream getOis() {
        return ois;
    }

    public void setOis(ObjectInputStream ois) {
        this.ois = ois;
    }
    
    
    
}

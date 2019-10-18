package ClientSide.Model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author adston
 */
public class Connected {

    private final ClientSocket client;
    private String IP;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    
    public Connected(ClientSocket client){
        this.client = client;
        this.IP = this.client.getSocket().getInetAddress().getHostAddress();
        
        try {
            this.ois = new ObjectInputStream(this.client.getSocket().getInputStream());
            this.oos = new ObjectOutputStream(this.client.getSocket().getOutputStream());
            
        } catch (IOException ex) {
            Logger.getLogger(Connected.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    

    public boolean isValid(){
        return this.client != null;
    }
    
    public String getName(){
        return this.client.getName();
    }
    
    public Socket getSocket() {
        return this.client.getSocket();
    }

    public ClientSocket getClient() {
        return client;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }
    
    
}
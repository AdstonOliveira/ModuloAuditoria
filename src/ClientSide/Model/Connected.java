package ClientSide.Model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 * @author adston
 */
public class Connected {

    private final ClientSocket client;
    private PrintStream channelSendMessage;
    private ObjectOutputStream sendObjet;
    private ObjectInputStream receiveObjet;
    
    
    public Connected(ClientSocket client){
        this.client = client;
        this.initMe();
    }
    
//    public String getIP(){
//        return this.client.getIP();
//    }
    private void initMe(){
        try {
            this.sendObjet = new ObjectOutputStream(this.getClient().getSocket().getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(Connected.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    public boolean isValid(){
        return this.client != null;
    }
    
    public String getName(){
        return this.client.getName();
    }
    
    public void sendMessage( String msg ){ 
        this.channelSendMessage.println( msg ); 
    }

    public Socket getSocket() {
        return this.client.getSocket();
    }

    public PrintStream getChannelSendMessage() {
        return channelSendMessage;
    }

    public void setChannelSendMessage(PrintStream channelSendMessage) {
        this.channelSendMessage = channelSendMessage;
    }

    public ClientSocket getClient() {
        return client;
    }
    
    
    
}

package ClientSide.Model;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import javax.swing.JOptionPane;
/**
 * @author adston
 */
public class Connected {

    private final Client client;
    private PrintStream channelSendMessage;
    
    public Connected(Client client){
        this.client = client;
        this.getStream();
    }
    public String getIP(){
        return this.client.getIP();
    }
    
    private void getStream(){
        try {
            this.channelSendMessage = new PrintStream( this.client.getSocket().getOutputStream() );
            System.out.println("PrintStream aberto");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao conectar: " + ex,"Fail",0);
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
    
    
    
}

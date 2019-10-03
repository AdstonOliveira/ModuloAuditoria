package Model.ClientSide;

import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.JOptionPane;

/**
 * @author adston
 */
public class Client {
    private final String name;
    private Socket socket;
    private String IP;

    public Client( String name, Socket socket ){
        this.name = name;
        this.socket = socket;
        this.IP = this.getMyIP();
    }
    
    public String getMyIP(){
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException ex) {
            JOptionPane.showMessageDialog(null, "Não foi possivel obter o IP da maquina","Erro ao obter IP",1);
        }
        
        return "fail";
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public String getName() {
        return name;
    }
    
    
}

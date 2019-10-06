package ClientSide.Model;

import ServerSide.Model.ServerBlockchain;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLSocket;
import javax.swing.JOptionPane;
/**
 * @author adston
 */
public class Client {
    
    private String name;
    private SSLSocket socket;
    private String myIp;
    
    
    public Client(SSLSocket socket){
        this.socket = socket;
    }
    
    public Client( String name){
        this.name = name;
        this.myIp = this.getMyIP();
        this.myServerSide();
        this.ConnectTo();
    }
    
    public final void myServerSide(){
        new ServerBlockchain();
    }
    
    public void ConnectTo(){
        SocketSSL ssl = new SocketSSL("localhost", 1050);
        this.socket = ssl.createSocket();
        System.out.println("Cliente Context: ");
        ssl.showMe();
        Connected connected = new Connected(this);
    }
    
    public void connectTo(String address, int port){
        try {
            this.socket = (SSLSocket) new Socket(address, port);
            Connected connected = new Connected(this);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public String getMyIP(){
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException ex) {
            JOptionPane.showMessageDialog(null, "Não foi possivel obter o IP da maquina","Erro ao obter IP",1);
        }
        
        return "fail";
    }

    
    
    
    
    
    
    public void myDetails(){
        System.out.println("Nome: " + this.name + " Meu Ip: " + this.myIp);
    }
    
    public Socket getSocket() {
        return socket;
    }

    public void setSocket(SSLSocket socket) {
        this.socket = socket;
    }

    public String getIP() {
        return myIp;
    }

    public void setIP(String IP) {
        this.myIp = IP;
    }

    public String getName() {
        return name;
    }
    
}

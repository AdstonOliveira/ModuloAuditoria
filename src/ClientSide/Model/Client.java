package ClientSide.Model;

import ServerSide.Model.Blockchain;
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
    private ServerBlockchain mySideServer;
    private Blockchain blockchain;
    
    /** Uso no servidor
     * @param socket*/
    public Client(SSLSocket socket){
        this.socket = socket;
    }
    /*Uso no Cliente*/
    public Client( String name ){
        this.name = name;
        this.myIp = this.getMyIP();
        
        this.myServerSide();
        this.ConnectTo();
        this.loadBlockchain();
    }
    
    public final void myServerSide(){
        this.mySideServer = new ServerBlockchain();
    }
    public boolean checkPort(String porta){
        if(porta ==null || porta.length() < 1)
            return false;
        
        char[] p = porta.toCharArray();
        
            for(char c : p){
                if(!Character.isDigit(c)){
                    return false;
                }
            }
        return true;
    }
    public void ConnectTo(){
        String endereco = JOptionPane.showInputDialog("Insira o endereco para se conectar");
        if(endereco == null || endereco.equalsIgnoreCase(""))
            endereco = "localhost";
        
        String portaT = JOptionPane.showInputDialog("Insira a porta");
        int porta = 1050;
        
        if(checkPort(portaT))
            porta = Integer.valueOf(portaT);
        
        
        
        
        SocketSSL ssl = new SocketSSL(endereco, porta);
        this.socket = ssl.createSocket();
        System.out.println("Cliente Context: ");
        ssl.showMe();
        Connected connected = new Connected(this);
    }
    
    public void loadBlockchain(){
        this.blockchain = new Blockchain(); // carregar blockchain salva aqui
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

    public String getMyIp() {
        return myIp;
    }

    public void setMyIp(String myIp) {
        this.myIp = myIp;
    }

    public ServerBlockchain getMySideServer() {
        return mySideServer;
    }

    public void setMySideServer(ServerBlockchain mySideServer) {
        this.mySideServer = mySideServer;
    }

    public Blockchain getBlockchain() {
        return blockchain;
    }

    public void setBlockchain(Blockchain blockchain) {
        this.blockchain = blockchain;
    }
    
}

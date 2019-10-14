package ClientSide.Model;

import ServerSide.Model.Blockchain;
import ServerSide.Model.ServerBlockchainSocket;
import Tools.Util;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * @author adston
 */
public class ClientSocket {

    private String name;
    private Socket socket;
    private ServerBlockchainSocket sbs;
    private Blockchain myBlockchain;
    
    private int PORT = 1050;
    private String serverIP = "localhost";
    
    public ClientSocket(){}
    public ClientSocket(Socket socket){ this.socket = socket; }
    public ClientSocket(String name){ this.name = name; this.startServer();}
    
    private void chanceConnection(){
        /*Bloco para alteracao de destino*/
        int defOpt = JOptionPane.showConfirmDialog(null, "Manter padrao? (localhost - 1050)","Config Padrao",0);
            if(defOpt == 1){
                String ip = JOptionPane.showInputDialog(null,"Insira o endereco destino: ","Maquina destino");
                if( ip != null && !ip.equalsIgnoreCase("") )
                    this.serverIP = ip;
                
                String port = JOptionPane.showInputDialog(null,"Insira a porta destino: ","IP destino");
                
                if(Util.checkPort(port))
                    this.PORT = Integer.valueOf(port);
            }
    }
    
    public void startServer(){
        this.sbs = new ServerBlockchainSocket();
    }           
    
    public boolean connectTo(){
        this.chanceConnection();
        
        try {
            this.socket = new Socket(this.serverIP, this.PORT);
            return true;
        } catch (IOException ex) {
            Logger.getLogger(ClientSocket.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public void sendTransaction(Transaction t){
        System.out.println("Implementar envio");
    }
    
    
    
    public static void main(String[] args) {
        ClientSocket c = new ClientSocket();
        c.connectTo();
    }

    
    
    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public ServerBlockchainSocket getSbs() {
        return sbs;
    }

    public void setSbs(ServerBlockchainSocket sbs) {
        this.sbs = sbs;
    }

    public Blockchain getMyBlockchain() {
        return myBlockchain;
    }

    public void setMyBlockchain(Blockchain myBlockchain) {
        this.myBlockchain = myBlockchain;
    }

    public int getPORT() {
        return PORT;
    }

    public void setPORT(int PORT) {
        this.PORT = PORT;
    }

    public String getServerIP() {
        return serverIP;
    }

    public void setServerIP(String serverIP) {
        this.serverIP = serverIP;
    }




    
}

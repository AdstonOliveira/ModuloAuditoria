package ClientSide.Model;

import ClientSide.Model.Thread.ThListenClient;
import ServerSide.Model.Block;
import ServerSide.Model.Blockchain;
import ServerSide.Model.ServerBlockchainSocket;
import Tools.Util;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
    private ObjectOutputStream os;
    
    private int PORT = 1050;
    private String serverIP = "localhost";
    
    private ObjectInputStream is;
    
    
    public ClientSocket(){}
    public ClientSocket(Socket socket){ this.socket = socket; }
    public ClientSocket(String name){ this.name = name; }
    
    private void chanceConnection(){
        /*Bloco para alteracao de destino*/
        int defOpt = JOptionPane.showConfirmDialog(null, "Manter padrao? (localhost - 1050)","Config Padrao",0);
            if(defOpt == 1){
                String ip = JOptionPane.showInputDialog(null,"Insira o endereco destino: ","10.0.0.1");
                if( ip != null && !ip.equalsIgnoreCase("") )
                    this.serverIP = ip;
                
                String port = JOptionPane.showInputDialog(null,"Insira a porta destino: ","1050");
                
                if(Util.checkPort(port))
                    this.PORT = Integer.valueOf(port);
            }
    }
    
    public void startServer(){
        this.sbs = new ServerBlockchainSocket();
    }           
    
    public boolean connectTo(){
        this.chanceConnection();
        this.startServer();

        try {
            this.socket = new Socket(this.serverIP, this.PORT);
            
            if( this.socket.isConnected() ){
                this.os = new ObjectOutputStream( this.socket.getOutputStream() );
                
                Thread listen = new Thread( new ThListenClient(this) );
                listen.start();
            return true;
            }

            return false;
        } catch (IOException ex) {
            Logger.getLogger(ClientSocket.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public void sendTransaction(Transaction t){
//        ObjectOutputStream os;
        try {
//            os = new ObjectOutputStream( this.socket.getOutputStream() );
            os.writeObject(t);
            os.flush();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void senBlock(Block block) throws IOException{
        this.os.writeObject(block);
        os.flush();
    }
    
    /* teste
    public static void main(String[] args) {
        ClientSocket c = new ClientSocket();
        c.connectTo();
    }
*/

    public ObjectOutputStream getOs() {
        return os;
    }

    public void setOs(ObjectOutputStream os) {
        this.os = os;
    }

    public ObjectInputStream getIs() {
        return is;
    }

    public void setIs(ObjectInputStream is) {
        this.is = is;
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

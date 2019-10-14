package ServerSide.Model;

import ServerSide.Threads.SimpleServerListen;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author adston
 */
public class ServerBlockchainSocket {
    private final Blockchain blockchain;
    private SimpleServerListen listen;
    private ServerSocket server;
    private int port = 1050;
    private Connecteds connecteds;

    public ServerBlockchainSocket(Blockchain blockchain) {
        this.blockchain = blockchain;
    }
    
    public ServerBlockchainSocket(){
        this.blockchain = new Blockchain();
        this.connecteds = new Connecteds();
        
        this.startMe();
    }
    
    public void startMe(){
        try {
            this.server = new ServerSocket(port);
            this.server.setReuseAddress(true);
            
            this.listen = new SimpleServerListen(this);
            Thread t = new Thread(this.listen);
            t.start();
        } catch (IOException ex) {
            Logger.getLogger(ServerBlockchainSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
        Thread t = new Thread(this.listen);
    }
    
    
    
    
    
    

    public SimpleServerListen getListen() {
        return listen;
    }

    public void setListen(SimpleServerListen listen) {
        this.listen = listen;
    }

    public ServerSocket getServer() {
        return server;
    }

    public void setServer(ServerSocket server) {
        this.server = server;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public Connecteds getConnecteds() {
        return connecteds;
    }

    public void setConnecteds(Connecteds connecteds) {
        this.connecteds = connecteds;
    }








    
}

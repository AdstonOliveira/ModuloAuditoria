package ServerSide.Model;
import ServerSide.Threads.ServerListen;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * @author adston
 */
public class ServerBlockchain {
    private final Blockchain blockchain;
    private ServerListen listen;
    private int port = 1050;
    
    public ServerBlockchain(){
        this.blockchain = new Blockchain();
        this.initListen();
    }
    public ServerBlockchain(int port){
        this.port = port;
        this.blockchain = new Blockchain();
        this.initListen();
    }
    
    public final void initListen(){
        try {
            this.listen = new ServerListen(this.port, false);
        } catch (NoSuchAlgorithmException ex) {
            System.out.println("Erro abrir server listen. linha 28");
            Logger.getLogger(ServerBlockchain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public static void main(String[] args) {
        ServerBlockchain b = new ServerBlockchain();
    }
    
    
    
    
}

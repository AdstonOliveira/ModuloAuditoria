package ServerSide.Threads;
import ServerSide.Model.ConnectedClient;
import ServerSide.Model.ServerBlockchainSocket;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * @author adston
 */
public class SimpleServerListen implements Runnable{
    private ServerBlockchainSocket server;

    public SimpleServerListen(ServerBlockchainSocket server){
        this.server = server;
    }

    @Override
    public void run() {
        System.out.println("Aguardando conexoes ...");
        while(true){
            try {
                Socket socket = this.server.getServer().accept();
                System.out.println("Cliente Conectado ...");

                ConnectedClient c = new ConnectedClient(socket);
                c.getOos().writeObject( this.server.getBlockchain() );

                this.server.getConnecteds().addNew(c);
                new Thread( new ThServerRead( c, this.server ) ).start();
                
            } catch (IOException ex) {
                Logger.getLogger(SimpleServerListen.class.getName()).log(Level.SEVERE, null, ex);
            }
        }


    }
    
    
}

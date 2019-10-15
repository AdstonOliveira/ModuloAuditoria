package ServerSide.Threads;
import ClientSide.Model.ClientSocket;
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
        System.out.println("Aguardando conexoes");
        while(true){
            try {
                Socket socket = this.server.getServer().accept();
                System.out.println("Conectou ...");
                ClientSocket c = new ClientSocket(socket);
                System.out.println("In server: " + c);
                this.server.getConnecteds().addNew(c);
                
                new Thread( new ThServerRead(c) ).start();
                
            } catch (IOException ex) {
                Logger.getLogger(SimpleServerListen.class.getName()).log(Level.SEVERE, null, ex);
            }
        }


    }
    
    
}

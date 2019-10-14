package ServerSide.Threads;

import ClientSide.Model.Client;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLSocket;
/**
 * @author adston
 */
public class ThServerListen implements Runnable{
    private SSLServerSocket server;
    private ServerListen sl;
    
        public ThServerListen(ServerListen sl, SSLServerSocket server){
            this.server = server;
            this.sl = sl;
        }
        
        @Override
        public void run() {
            System.out.println("Aguardando conexoes \n");
            while(true){
                try {
                    SSLSocket socket;
                    socket = (SSLSocket) this.server.accept();
                    Client client = new Client(socket);
                    this.sl.getServer().getConnecteds().addNew(client);
                    System.out.println("Conectou: IP:" + socket.getInetAddress());
                    /*enviar para espera*/
                    Thread t = new Thread(new ThServerRead(socket));
                    t.start();
                    
                    System.out.println("Enderecos conectados: \n");
                    this.sl.getServer().getConnecteds().getIPS();
                } catch (IOException ex) {
                    Logger.getLogger(ServerListen.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }

        }
        
    
}

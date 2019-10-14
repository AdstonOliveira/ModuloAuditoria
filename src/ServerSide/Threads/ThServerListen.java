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
                    System.out.println("Conectou: IP:" + socket.getInetAddress());
                    
                    Client client = new Client(socket);
//                    this.sl.getServer().getConnecteds().addNew(client);
                    
                    /*enviar para espera*/
                    System.out.println("Ta esperando msg");
                    Thread t = new ThServerRead(socket);
                    t.start();
                    System.out.println("Enderecos conectados: \n");
                    this.sl.getServer().getConnecteds().getIPS();
                } catch (IOException ex) {
                    System.out.println("fail");
                    Logger.getLogger(ServerListen.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }

        }
        
    
}

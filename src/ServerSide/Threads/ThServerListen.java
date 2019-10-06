package ServerSide.Threads;

import ServerSide.Threads.ServerListen;
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
        
        public ThServerListen(SSLServerSocket server){
            this.server = server;
        }
        
        @Override
        public void run() {
            System.out.println("Aguardando conexoes \n");
            while(true){
                try {
                    SSLSocket client;
                    client = (SSLSocket) server.accept();
                    
//                    Client c = new Client();
                    System.out.println("Conectou");
                } catch (IOException ex) {
                    Logger.getLogger(ServerListen.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }

        }
        
    
}

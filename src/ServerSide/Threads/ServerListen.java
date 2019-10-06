package ServerSide.Threads;

import Tools.Security.Key_Store;
import Tools.Security.SSL_Context;
import java.io.IOException;
import java.net.SocketException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ServerSocketFactory;
import javax.net.ssl.SSLServerSocket;

/**
 * @author adston
 * Classe que inicializa a escuta do lado servidor
 * Processo automatizado, basta iniciar o objeto com ou sem parametros
 */
public class ServerListen {
    private Key_Store ks;
    private final int PORT;
    private final boolean auth_client;
    private SSL_Context ssl_context;
    
    public ServerListen() throws NoSuchAlgorithmException{
        this.ks = new Key_Store();
        this.PORT = 1050;
        this.auth_client = false;
        
        this.initMe();
    }

    public ServerListen(int PORT, boolean auth_client) throws NoSuchAlgorithmException {
        this.ks = new Key_Store();
        this.PORT = PORT;
        this.auth_client = auth_client;
        this.initMe();
    }
    
    public SSL_Context getSSL(){
        return this.ssl_context;
    }
    
    public SSLServerSocket CreateServer() throws NoSuchAlgorithmException, KeyManagementException{
        this.ks = new Key_Store();
        this.ks.initKMF();
        
        this.ssl_context = new SSL_Context();
        this.ssl_context.getSSLContext("SSLv3");
        this.ssl_context.initContext(this.ks.getKmf());
        
        ServerSocketFactory ssf = this.ssl_context.getSsl_context().getServerSocketFactory();
        this.ssl_context.showPropSSLContext(this.ssl_context.getSsl_context());
        
        SSLServerSocket sss = null;
        try {
            
            sss = (SSLServerSocket) ssf.createServerSocket(PORT);
            
            try {
                sss.setReuseAddress(true);
            } catch (SocketException ex) {
                Logger.getLogger(ServerListen.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if (this.auth_client)
                sss.setNeedClientAuth(this.auth_client);
            
        } catch (IOException ex) {
            Logger.getLogger(ServerListen.class.getName()).log(Level.SEVERE, null, ex);
        }
        
          
        return sss;
    }
    
    public final void initMe(){
        SSLServerSocket server;
        try {
            server = this.CreateServer();
            Thread t = new Thread(  new ThServerListen(server) );
            t.start();
        } catch (NoSuchAlgorithmException | KeyManagementException ex) {
            Logger.getLogger(ServerListen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

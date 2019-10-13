package ServerSide.Threads;

import ServerSide.Model.ServerBlockchain;
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
    private ServerBlockchain server;
    
    public ServerListen(ServerBlockchain server) throws NoSuchAlgorithmException{
        this.ks = new Key_Store("Server KeyStore");
        this.server = server;
        this.PORT = 1050;
        this.auth_client = false;
        
        this.initMe();
    }

    public ServerListen(ServerBlockchain server, int PORT, boolean auth_client) throws NoSuchAlgorithmException {
        this.ks = new Key_Store("Server KeyStore");
        this.server = server;
        this.PORT = PORT;
        this.auth_client = auth_client;
        
        this.initMe();
    }
    
    public SSL_Context getSSL(){
        return this.ssl_context;
    }
    
    public SSLServerSocket CreateServer() throws NoSuchAlgorithmException, KeyManagementException{
//        this.ks = new Key_Store();
        this.ks.initKMF();
        
        this.ssl_context = new SSL_Context();
        this.ssl_context.getSSLContext("SSLv3");
        this.ssl_context.initContext(this.ks.getKmf());
        
        ServerSocketFactory ssf = this.ssl_context.getSsl_context().getServerSocketFactory();
        this.ssl_context.showPropSSLContext(this.ssl_context.getSsl_context());
        
        SSLServerSocket sss = null;
        
        try {
            sss = (SSLServerSocket) ssf.createServerSocket(PORT);
        } catch (IOException ex) {
            System.out.println("Erro linha 58. Criar serverSocket");
            Logger.getLogger(ServerListen.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(sss !=null){
            try {
                sss.setReuseAddress(true);
            } catch (SocketException ex) {
                System.out.println("Erro linha 66. Reuse address");
                Logger.getLogger(ServerListen.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (this.auth_client)
                sss.setNeedClientAuth(this.auth_client);
        }
          
        return sss;
    }
    
    public final void initMe(){
        SSLServerSocket socketServer;
        try {
            socketServer = this.CreateServer();
            Thread t = new Thread(  new ThServerListen(socketServer) );
            t.start();
        } catch (NoSuchAlgorithmException | KeyManagementException ex) {
            System.out.println("Erro metodo init ServerListen");
            Logger.getLogger(ServerListen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

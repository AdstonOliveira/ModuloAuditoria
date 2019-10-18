package ClientSide.Model;

import Tools.Security.Key_Store;
import Tools.Security.SSL_Context;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

/**
 * @author adston
 */
public class SocketSSL {
    private final int port;
    private SSLSocket socket;
    private Key_Store ks;
    private SSL_Context ssl_context;
    private final String host;
    
    public SocketSSL(){
        this.port = 1050;
        this.host = "localhost";
    }
    public SocketSSL(String host, int port){
        if( port!=0 )
            this.port = port;
        else
            this.port = 1050;
        
        if(  host!=null && !host.equalsIgnoreCase(""))
            this.host = host;
        else
            this.host = "localhost";
        
    }
    
    public SSLSocket createSocket(){
        try {
            this.ks = new Key_Store("Cliente KeyStore");
            this.ks.initKMF();
            this.ssl_context = new SSL_Context();
            this.ssl_context.getSSLContext("SSLv3");
            this.ssl_context.initContext(this.ks.getKmf());
            
            SSLSocketFactory ssf = this.ssl_context.getSsl_context().getSocketFactory();
            this.socket = (SSLSocket) ssf.createSocket(this.host,this.port); 
      
            return this.socket;
        } catch (NoSuchAlgorithmException | KeyManagementException | IOException ex) {
            Logger.getLogger(SocketSSL.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    public void showMe(){
        this.ssl_context.showPropSSLContext(this.ssl_context.getSsl_context());
    }
}

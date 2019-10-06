package Tools.Security;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSessionContext;

/**
 * @author adston
 */
public class SSL_Context {
    private SSLContext ssl_context;
    private SSLSessionContext sslSessionContext;
    
    public SSLContext getSSLContext(String instance) throws NoSuchAlgorithmException{
        this.ssl_context = SSLContext.getInstance(instance);
        return this.ssl_context;
    }
    
    public void initContext(KeyManagerFactory km) throws KeyManagementException{
        this.ssl_context.init( km.getKeyManagers() , null, null );
    }
    
    
    public void showPropSSLContext(SSLContext contextoSSL){
 
        System.out.println("-------Informaçoes de contexto SSL-------");
        String protocol = contextoSSL.getProtocol();
        System.out.println("Protocolo : "+protocol);

        Provider provider = contextoSSL.getProvider();
        System.out.println("Nome do provedor : "+provider.getName());
        System.out.println("Versao do provedor : "+provider.getVersion());
        System.out.println("Info: " + provider.getInfo());
        this.sslSessionContext = contextoSSL.getServerSessionContext();
   
    }

    public SSLContext getSsl_context() {
        return ssl_context;
    }

    public void setSsl_context(SSLContext ssl_context) {
        this.ssl_context = ssl_context;
    }
    
}

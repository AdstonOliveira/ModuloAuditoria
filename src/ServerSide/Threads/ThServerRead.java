package ServerSide.Threads;
import ClientSide.Model.Transaction;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLSocket;

/**
 * @author adston
 * Efetua a leitura dos dados enviados
 */
public class ThServerRead implements Runnable{
    private SSLSocket socket;
    private ObjectInputStream is;
    
    public ThServerRead(SSLSocket socket){
        this.socket = socket;
        this.initMe();
    }

    public final void initMe(){
        try {
            this.is = new ObjectInputStream(this.socket.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(ThServerRead.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    @Override
    public void run() {
        System.out.println("Aguardando mensagens...");
        while(true){
            try {
                String protocol = is.readUTF();
                if(protocol.equalsIgnoreCase("toValid")){
                    Transaction t = (Transaction) is.readObject();
                    System.out.println("Recebido " + t.getHash());
                }
            } catch (IOException ex) {
                Logger.getLogger(ThServerRead.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ThServerRead.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            
            
        }


    }
    
}

package ServerSide.Threads;
import ClientSide.Model.ClientSocket;
import ClientSide.Model.Transaction;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author adston
 * Efetua a leitura dos dados enviados
 */
public class ThServerRead extends Thread{
    private ClientSocket socket;
    private ObjectInputStream is;
    
    public ThServerRead(ClientSocket socket){
        this.socket = socket;
    }

    public final void initMe(){
        System.out.println("Iniciou leitura");
    }
    
    @Override
    public void run() {
        this.initMe();
        
        System.out.println("Aguardando mensagens ...");
        System.out.println("Socket Th: "+socket);
        
            try {
                this.is = new ObjectInputStream( this.socket.getSocket().getInputStream() );
                System.out.println("Tentei ler");
        }   catch (IOException ex) {
                Logger.getLogger(ThServerRead.class.getName()).log(Level.SEVERE, null, ex);
            }
        try {
            
            Object tmp = this.is.readObject();
            if(tmp instanceof Transaction){
                System.out.println("Essa fita mesmo ...");
            }
        } catch (IOException ex) {
            Logger.getLogger(ThServerRead.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ThServerRead.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    }
    
}

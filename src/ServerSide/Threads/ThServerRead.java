package ServerSide.Threads;
import java.net.Socket;

/**
 * @author adston
 * Efetua a leitura dos dados enviados
 */
public abstract class ThServerRead implements Runnable{
    
    public ThServerRead(Socket socket){
        
    }
    
    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

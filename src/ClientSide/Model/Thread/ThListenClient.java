package ClientSide.Model.Thread;

import ClientSide.Model.ClientSocket;
import ServerSide.Model.Block;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * @author adston
 */
public class ThListenClient implements Runnable{
    private ClientSocket cs;
//    private ObjectInputStream is;
    
    public ThListenClient(ClientSocket cs){
        this.cs = cs;
    }
    
    
    @Override
    public void run() {

        try {
            this.cs.setIs(new ObjectInputStream( this.cs.getSocket().getInputStream() ));
            
        } catch (IOException ex) {
            Logger.getLogger(ThListenClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Object tmp;
            try {
                tmp = this.cs.getIs().readObject();
                
                if(tmp instanceof Block){
                    Block b = (Block) tmp;
                    
                    if( b.getHash().equalsIgnoreCase("nao calculado") ){
                        ThMinningBlock mb = new ThMinningBlock(b);
                        Thread t = new Thread(mb);
                        t.start();
                        
                        t.join();
                        System.out.println("Terminou a mineiração no cliente");
                        this.cs.senBlock(b);
                    }
                }
                
            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(ThListenClient.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
            Logger.getLogger(ThListenClient.class.getName()).log(Level.SEVERE, null, ex);
        }
                
    }
    
}

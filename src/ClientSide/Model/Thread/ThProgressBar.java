package ClientSide.Model.Thread;

import ClientSide.View.cliente.Dash;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author adston
 */
public class ThProgressBar implements Runnable{

    private volatile boolean stop = false;
    
    @Override
   synchronized  public void run() {
        int i = 0;
        while(!stop){
        
            if(i == 10)
                i = 0;
            
            try {
                sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(ThProgressBar.class.getName()).log(Level.SEVERE, null, ex);
            };
            
            Dash.minningBlock.setValue(i);
            i++;
        }
        
    }
    
    public void stopThis(){
        this.stop = true;
        
        Dash.minningBlock.setValue(0);
    }
    
}

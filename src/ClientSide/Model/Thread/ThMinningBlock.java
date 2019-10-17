package ClientSide.Model.Thread;
import ServerSide.Model.Block;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author adston
 */
public class ThMinningBlock implements Runnable{
    public ThMinningBlock(Block block){
        this.block = block;
    }
    private Block block;
    public volatile boolean finish = false;
    
    @Override
    public void run(){
        ThProgressBar pb = new ThProgressBar();
        Thread t = new Thread(pb);
        t.start();
        
        while(!finish){
            String target = new String( new char[block.getDifficulty()] ).replace('\0', '0');
            int nonce = block.getNonce();
            
                while ( !block.getHash().substring(0, block.getDifficulty()).equals(target) ) { // Divide o hash da posicao 0, ate a qta 0
                    block.setNonce(nonce++);
                    block.setHash( block.calculateHash() ); // o nonce serve para a quantidade de hash gerados...
//                    block.setTimeStamp(System.currentTimeMillis());
                } // Gera varios hash's, ate que algum contenha a qtde desejadas de 0 no inicio

                pb.stopThis();
//                t.interrupt();
            finish = true;
        }
        
        try {
            this.finalize();
        } catch (Throwable ex) {
            Logger.getLogger(ThMinningBlock.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}

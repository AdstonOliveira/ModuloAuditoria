package ClientSide.Model.Thread;

import ClientSide.Model.ClientSocket;
import ServerSide.Model.Block;
import ServerSide.Model.Blockchain;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * @author adston
 */
public class ThListenClient implements Runnable{
    private ClientSocket cs;
    
    public ThListenClient(ClientSocket cs){
        this.cs = cs;
    }
    
    @Override
    public void run() {
        
        try {
            this.cs.setIs( new ObjectInputStream( this.cs.getSocket().getInputStream() ));
        } catch (IOException ex) {
            Logger.getLogger(ThListenClient.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Cliente: Erro ao abrir OIS cliente");
        }
        
        Object tmp;
        while(true){
            try {
                tmp = this.cs.getIs().readObject();
                System.out.println("Cliente: Aguardando objetos do servidor ...");
                
                if(tmp instanceof Block){
                    System.out.println("Cliente: Bloco recebido");
                    Block b = (Block) tmp;
                    
                    if( !b.isValid() ){
                        this.noValidate(b);
                    }
                    
                }
                
                if(tmp instanceof Blockchain){
                    System.out.println("Cliente: Recebi um blockchain do servidor");
                    Blockchain b = (Blockchain) tmp;
                    this.cs.setMyBlockchain(b);
                }
            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(ThListenClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    public boolean noValidate(Block b){
        if( !b.isValid() ){
            String hashToCompare = b.getHash();
            String hashTransaction = b.getHash_transactions();
                    
                b.setHash("Mineirando no cliente");
                b.hashTransactions();
                    
                Thread th = new Thread( new ThMinningBlock(b) );
                th.start();
                    
                    try {
                        th.join();
                        if( b.getHash().equals(hashToCompare) && b.getHash_transactions().equals(hashTransaction)){
                            b.setIsValid(true);
                            this.cs.getOs().writeObject(b);
                            return true;
                        }else if( !b.getHash().equals(hashToCompare) ){
                            System.out.println("Hash não confere");
                            b.setIsValid(false);
                            return false;
                        }else if(!b.getHash_transactions().equals(hashTransaction)){
                            System.out.println("hash da transacao nao bate");
                            b.setIsValid(false);
                            return false;
                        }
                        
                    } catch (InterruptedException | IOException ex) {
                        Logger.getLogger(ThListenClient.class.getName()).log(Level.SEVERE, null, ex);
                        return false;
                    }
        }
        return false;
    }
    
    
}

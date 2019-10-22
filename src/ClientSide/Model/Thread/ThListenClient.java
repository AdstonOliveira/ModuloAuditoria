package ClientSide.Model.Thread;

import ClientSide.Model.ClientSocket;
import ClientSide.Model.ValidateTransaction;
import DAO.DAOBlock;
import ServerSide.Model.Block;
import ServerSide.Model.Blockchain;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.SocketException;
import java.sql.SQLException;
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
        boolean stop = false;
        Object tmp;
        while( !stop ){
            try {
                tmp = this.cs.getIs().readObject();
                System.out.println("Cliente: Aguardando objetos do servidor ...");
                if(tmp instanceof String){
                    String msg = (String) tmp;
                    javax.swing.JOptionPane.showMessageDialog(null, msg);
                }
                
                if(tmp instanceof Block){
                    System.out.println("Cliente: Bloco recebido");
                    Block b = (Block) tmp;
                    
                    if( !b.isValid() ){
                        if(!this.noValidate(b)){
                            System.out.println("Esta transacao não é valida");
                        }
                    }
                }
                
                if(tmp instanceof Blockchain){
                    System.out.println("Cliente: Recebi um blockchain do servidor");
                    Blockchain b = (Blockchain) tmp;
                    
                    if( b.getSize() > DAOBlock.sizeBlockchain() ){
                        System.out.println("Blockchain recebida maior ou igual a atual");
                        if( b.isChainValid() ){
                            this.cs.setMyBlockchain(b);
                            this.cs.getMyBlockchain().saveMe();
                        }else{
                            System.out.println("Blockchain invalida, mantendo a atual");
                            this.cs.setMyBlockchain( new Blockchain( DAOBlock.getBlockchain(), this.cs.getSbs() ) );
                            this.cs.getOs().writeObject(this.cs.getMyBlockchain());
                        }
                        
                    }else{
                        System.out.println("Blockchain menor que a atual");
                        this.cs.setMyBlockchain( new Blockchain( DAOBlock.getBlockchain(), this.cs.getSbs() ) );
                        this.cs.getOs().writeObject(this.cs.getMyBlockchain());
                    }
                }
                
                stop = true;
            } catch (IOException | ClassNotFoundException | SQLException | InterruptedException ex) {
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
                ValidateTransaction vt = new ValidateTransaction( b.getLastTransaction() );
                if(vt.validate()){
                
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
        }
        return false;
    }
    
    
}

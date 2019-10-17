package ServerSide.Threads;
import ClientSide.Model.Connected;
import ClientSide.Model.Transaction;
import ServerSide.Model.Block;
import ServerSide.Model.ServerBlockchainSocket;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 * @author adston
 * Efetua a leitura dos dados enviados
 */
public class ThServerRead extends Thread{
    
    private Connected conn;
    private ServerBlockchainSocket serverBlockchain;
    
    public ThServerRead(Connected conn, ServerBlockchainSocket blockchain){
        this.conn = conn;
        this.serverBlockchain = blockchain;
    }

    @Override
    public void run() {
        System.out.println("Lendo objetos recebidos ...\n" + this.conn.getIP() );
        
        while( true ){
            Object tmp;
            try {
                tmp = this.conn.getOis().readObject();

                    if(tmp instanceof Transaction){
                        Transaction t = (Transaction) tmp;
                        
                        this.serverBlockchain.getBlockchain().addTransaction(t);
                        System.out.println("Transacao recebida");
                    }

                    if(tmp instanceof Block){
                        Block b = (Block) tmp;
                        if( !b.getHash().equalsIgnoreCase("nao calculado") ){
                            this.serverBlockchain.getBlockchain().minning(b);
                            JOptionPane.showMessageDialog(null, b.toString());
                        }
                    }

            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(ThServerRead.class.getName()).log(Level.SEVERE, null, ex);
            }
        }    
    }
    
}
package ServerSide.Threads;
import ClientSide.Model.ClientSocket;
import ClientSide.Model.Transaction;
import ServerSide.Model.Block;
import ServerSide.Model.ServerBlockchainSocket;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * @author adston
 * Efetua a leitura dos dados enviados
 */
public class ThServerRead extends Thread{
    private ClientSocket socket;
    private ObjectInputStream is;
    private ServerBlockchainSocket serverBlockchain;
    
    public ThServerRead(ClientSocket socket, ServerBlockchainSocket blockchain){
        this.socket = socket;
        this.serverBlockchain = blockchain;
    }

    @Override
    public void run() {
        System.out.println("Iniciando leitura ...");
        
            try {
                this.is = new ObjectInputStream( this.socket.getSocket().getInputStream() );
            } catch (IOException ex) {
                Logger.getLogger(ThServerRead.class.getName()).log(Level.SEVERE, null, ex);
            }
        while( true ){
            Object tmp;
            try {
                tmp = this.is.readObject();

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
package ServerSide.Threads;
import ClientSide.Model.Transaction;
import ServerSide.Model.Block;
import ServerSide.Model.ConnectedClient;
import ServerSide.Model.ServerBlockchainSocket;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * @author adston
 * Efetua a leitura dos dados enviados
 */
public class ThServerRead extends Thread{
    
    private ConnectedClient conn;
    private ServerBlockchainSocket serverBlockchain;
    
    public ThServerRead(ConnectedClient conn, ServerBlockchainSocket blockchain){
        this.conn = conn;
        this.serverBlockchain = blockchain;
    }

    @Override
    public void run() {
        System.out.println("Server: Lendo objetos recebidos ...\n" + this.conn.getSocket().getInetAddress() );
        
        while( true && this.conn.getSocket().isConnected() ){
            Object tmp;
            try {
                tmp = this.conn.getOis().readObject();

                    if(tmp instanceof Transaction){
                        Transaction t = (Transaction) tmp;
                        
                        System.out.println("Transacao Recebida no Server: " + tmp.toString() );
                        this.serverBlockchain.getBlockchain().addTransaction(t);
                    }

                    if( tmp instanceof Block ){
                        Block b = (Block) tmp;
                        
                        System.out.println("Bloco esperando validação");
                        this.serverBlockchain.getBlockchain().getPool().addToAvalition(b);
                        
                    }

            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(ThServerRead.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.interrupt();
    }
    
}
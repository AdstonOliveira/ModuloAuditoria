package ServerSide.Threads;
import ClientSide.Model.Transaction;
import ClientSide.Model.ValidateTransaction;
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
        boolean run = true;
        while( true && run ){
            Object tmp;
            try {
                tmp = this.conn.getOis().readObject();

                    if(tmp instanceof Transaction){
                        Transaction t = (Transaction) tmp;
                        
                        System.out.println("Transacao Recebida no Server: " + tmp.toString() );
                        ValidateTransaction vt = new ValidateTransaction(t);
                        
                        if( vt.validate() )
                            this.serverBlockchain.getBlockchain().addTransaction(t);
                        else{
                            this.conn.getOos().writeObject("Transacao inválida - Não passou na validação");
                        }
                    }
                    
                    if( tmp instanceof Block ){
                        Block b = (Block) tmp;
                        
                        System.out.println("Bloco esperando validação");
                        this.serverBlockchain.getBlockchain().getPool().addToAvalition(b);
                        
                    }

            } catch (IOException | ClassNotFoundException ex) {
                run = false;
                this.serverBlockchain.getConnecteds().remove(this.conn);
                this.interrupt();
                Logger.getLogger(ThServerRead.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        this.interrupt();
    }
    
}
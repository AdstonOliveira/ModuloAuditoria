package DAO;
import ClientSide.Model.I_Transaction;
import ClientSide.Model.Transaction;
import ServerSide.Model.Block;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
/**
 * @author adston
 */
public class DAOTransaction extends DAO{

    public static boolean deleteAll(){
        return DAO.deleteAll("transacao");
    }
    
    public static boolean saveTransaction(Transaction transaction, Connection connn){
        conn = connn;
        
        PreparedStatement stmt2 = null;

        try {
            stmt2 = conn.prepareStatement("insert into TRANSACAO(t_timestamp, transaction_hash, hash_transaction_file, path_file, block_hash) "
                    + "values (?, ?, ?, ?, ?)");
            stmt2.setTimestamp(1, transaction.getTimestamp() );
            stmt2.setString(2, transaction.getTransaction_hash() );
            stmt2.setString(3, transaction.getHash_transaction_file() );
            
            stmt2.setString(4, transaction.getTransaction_file().getPath() );
            stmt2.setString(5, transaction.getBlockHash() );
            
            stmt2.execute();
//            conn.commit();
//            DAO.closeConnection(conn, stmt);
            System.out.println("Transacao salva");
            
            return true;
        } catch (SQLException ex) {
           try {
               conn.rollback();
               System.out.println("Rollback transaction");
           } catch (SQLException ex1) {
               System.out.println("Erro ao realizar roolback");
               System.out.println(ex1);
           }
            System.out.println("Erro ao salvar Transacao ...");
            System.out.println(ex);
        }
        return false;
    }
    public static boolean saveTransaction(Block block, Connection connn){
        conn = connn;
        boolean exec = true;
        for(I_Transaction t : block.getDados() ){
            Transaction transaction = (Transaction) t;
            t.setBlockHash(block.getHash());
            exec = saveTransaction(transaction, conn) && exec;
        }
        return exec;
    }
    
    
    
}

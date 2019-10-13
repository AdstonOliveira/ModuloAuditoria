package DAO;
import ClientSide.Model.Transaction;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * @author adston
 */
public class DAOTransaction extends DAO{
    
    public static boolean saveTransaction(Transaction transaction){
        conn = DAO.getConnection();
        
        PreparedStatement stmt = null;
        
        try {
            stmt = conn.prepareStatement("insert into adm.transaction("
                    + "timestamp, transaction_hash, previous_transaction_hash, hash_transaction_file, path_file) "
                    + "values (?, ?, ?, ?, ?)");
            stmt.setLong(1, transaction.getTimestamp());
            stmt.setString(2, transaction.getTransaction_hash() );
            stmt.setString(3, transaction.getPrevious_transaction_hash() );
            stmt.setString(4, transaction.getHash_transaction_file() );
            stmt.setString(5, transaction.getTransaction_file().getPath() );
            
            stmt.execute();
            DAO.closeConnection(conn, stmt);
            System.out.println("Transacao salva");
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            DAO.closeConnection(conn, stmt);
        }
        return false;
        }
    
    
    
}

package DAO;
import ClientSide.Model.Transaction;
import ServerSide.Model.Block;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
/**
 * @author adston
 */
public class DAOTransaction extends DAO{

    public static boolean deleteAll(){
        return DAO.deleteAll("transacao");
    }
    
    public static boolean saveTransaction(Transaction transaction){
        conn = DAO.getConnection();
        PreparedStatement stmt2 = null;

        try {
            stmt2 = conn.prepareStatement("insert into TRANSACAO"
                    + "(t_timestamp, transaction_hash, hash_transaction_file, path_file,block_id ) "
                    + "values (?, ?, ?, ?, ?)");
            
            stmt2.setTimestamp(1, transaction.getTimestamp() );
            stmt2.setString(2, transaction.getTransaction_hash() );
            stmt2.setString(3, transaction.getHash_transaction_file() );
            
            stmt2.setString(4, ".//files//"+transaction.getTransaction_file().getName() );
            stmt2.setInt(5, transaction.getBlock_id() );
            
            stmt2.execute();

            System.out.println("Transacao salva");
            DAO.closeConnection(conn, stmt2);
            return true;
        } catch (SQLException ex) {
            System.out.println("Erro ao salvar Transacao ...");
            System.out.println(ex);
        }
        return false;
    }
    
    public static boolean saveTransaction(Block block){
        boolean exec = true;
        JOptionPane.showMessageDialog(null, block.toString());
        for(Transaction t : block.getTransactions() ){
            Transaction transaction = t;
            
            t.setBlockHash( block.getHash() );
            
            JOptionPane.showMessageDialog(null, t.getBlockId() + "\n"+t.getHash_block());
            exec = saveTransaction(transaction) && exec;
        }
        return exec;
    }
    
    
    
}

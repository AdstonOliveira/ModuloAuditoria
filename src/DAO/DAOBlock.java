package DAO;

import ClientSide.Model.Transaction;
import ServerSide.Model.Block;
import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * @author adston
 */
public class DAOBlock extends DAO{
    
    
    public static boolean saveBlock(Block block){
        if(!block.isValid())
            return false;
        
        conn = DAO.getConnection();
        PreparedStatement stmt = null;
        
        try {
            conn.setAutoCommit(false);
            
            stmt = conn.prepareStatement("insert into Block(B_TIMESTAMP, BLOCK_HASH, PREVIOUS_HASH, AMOUNT_TRANSACTIONS,"
                    + "NOUNCE, DIFFICULTY) values (?,?,?,?,?,?)");
            stmt.setTimestamp(1, (block.getTimeStamp()) );
            stmt.setString(2, block.getHash() );
            stmt.setString(3, block.getPreviousHash() );
            stmt.setInt(4, block.getAmount_transactions() );
            stmt.setInt(5, block.getNonce() );
            stmt.setInt(6, block.getDifficulty() );
            
           stmt.execute();
           System.out.println("Bloco salvo");
           
           if( !DAOTransaction.saveTransaction(block, conn) )
               conn.rollback();
           else
               conn.commit();
           
           conn.setAutoCommit(true);
           DAO.closeConnection(conn, stmt);
            return true;
        } catch (SQLException ex) {
            try {
                System.out.println("Executando o rollback Bloco");
                conn.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(DAOBlock.class.getName()).log(Level.SEVERE, null, ex1);
            }
            
        System.out.println("Erro ao executar a inserção do bloco.");
        System.out.println(ex);
//            Logger.getLogger(DAOBlock.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    public static Set<Block> getBlockchain() throws SQLException{
        conn = DAO.getConnection();
        Set<Block> blockchain = new LinkedHashSet();
        
        try {
            PreparedStatement stmt = conn.prepareStatement("Select * from Block right join Transacao on Block.hash like Transacao.block_hash");
            ResultSet rs = stmt.executeQuery();
            
            while( rs.next() ){
                String path_file = rs.getString("PATH_FILE");
                File file = new File(path_file);
                Transaction t = new Transaction(file);
                
                   t.setHash(rs.getString("TRANSACTION_HASH"));
                   t.setTimestamp( ( rs.getTimestamp("t_timestamp") ) );
                   t.setHash_transaction_file( rs.getString("HASH_TRANSACTION_FILE"));
                   
                Block block = new Block();
                    block.setAmount_transactions(rs.getInt("AMOUNT_TRANSACTIONS"));
                    block.setTimeStamp(rs.getTimestamp("B_TIMESTAMP"));
                    block.setHash(rs.getString("BLOCK_HASH"));
                    block.setPreviousHash(rs.getString("PREEVIOUS_HASH"));
                    block.setNonce(rs.getInt("NOUNCE"));
                    block.setDifficulty(rs.getInt("DIFFICULTY"));
                    
                t.setBlockHash(block.getHash());
                block.add_transation(t);
                blockchain.add(block);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOBlock.class.getName()).log(Level.SEVERE, null, ex);
        }
        return blockchain;
    } 
    
    
    
    
}

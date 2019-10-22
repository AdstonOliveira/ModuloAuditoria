package DAO;

import ClientSide.Model.Transaction;
import ServerSide.Model.Block;
import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 * @author adston
 */
public class DAOBlock extends DAO{
    
    public static int getLastId(){
        conn = DAO.getConnection();
        int id = 0;
        try {
            PreparedStatement stmt = conn.prepareStatement("select * from getlastid");
            ResultSet rs = stmt.executeQuery();

            if( rs.next() )
                id = rs.getInt("id");
            
            DAO.closeConnection(conn, stmt);
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOBlock.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return id;
    }
    
    public static String getLastHash(){
        int id = getLastId();
        conn = DAO.getConnection();
        PreparedStatement stmt;
        try {
            stmt = conn.prepareStatement("select block_hash from block where id = ?");
            stmt.setInt(1, id);
            
            ResultSet rs = stmt.executeQuery();
            
            if( rs.next() ){
                String value = rs.getString("block_hash");
                DAO.closeConnection(conn, stmt);
                return value;
            }

        } catch (SQLException ex) {
            DAO.closeConnection(conn);
            Logger.getLogger(DAOBlock.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        DAO.closeConnection(conn);
        return "First Block";
    }
    
    
    public static boolean saveBlock(Block block){
        if( !block.isValid() )
            return false;
        
        conn = DAO.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = conn.prepareStatement("insert into "
                    + "Block(B_TIMESTAMP, BLOCK_HASH, PREVIOUS_HASH, AMOUNT_TRANSACTIONS,"
                    + "NOUNCE, DIFFICULTY) values (?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            
            stmt.setTimestamp(1, (block.getTimeStamp()) );
            stmt.setString(2, block.getHash() );
            stmt.setString(3, block.getPreviousHash() );
            
            stmt.setInt(4, block.getAmount_transactions() );
            stmt.setInt(5, block.getNonce() );
            stmt.setInt(6, block.getDifficulty() );
            
            stmt.execute();

            ResultSet rs = stmt.getGeneratedKeys();
            int blockId = 0;
            if( rs.next() ){
                blockId = rs.getInt(1);
            }
            JOptionPane.showMessageDialog(null, blockId);
            
            DAO.closeConnection(conn, stmt);
            
            for(Transaction t :block.getDados())
                t.setBlockId(blockId);
            
            DAOTransaction.saveTransaction(block);

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
        }
        
        return false;
    }
    
    public static Set<Block> getBlockchain() throws SQLException{
        conn = DAO.getConnection();
        Set<Block> blockchain = new LinkedHashSet();
        
        try {
            PreparedStatement stmt = conn.prepareStatement("Select * from Block "
                    + "left join Transacao on Block.id = Transacao.block_id");
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
                    block.setPreviousHash(rs.getString("PREVIOUS_HASH"));
                    block.setNonce(rs.getInt("NOUNCE"));
                    block.setDifficulty(rs.getInt("DIFFICULTY"));
                    block.setId(rs.getInt("id"));
                    
                t.setBlockHash(block.getHash());
                t.setBlock_id(block.getId());
                
                block.add_transation(t);
                blockchain.add(block);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOBlock.class.getName()).log(Level.SEVERE, null, ex);
        }
        return blockchain;
    } 
    
    public static List<Block> listBlocks(){
        conn = DAO.getConnection();
        List<Block> blockchain = new ArrayList();
        
        try {
            PreparedStatement stmt = conn.prepareStatement("Select * from Block");
            ResultSet rs = stmt.executeQuery();
            
            while( rs.next() ){
                Block block = new Block();
                    block.setAmount_transactions(rs.getInt("AMOUNT_TRANSACTIONS"));
                    block.setTimeStamp(rs.getTimestamp("B_TIMESTAMP"));
                    block.setHash(rs.getString("BLOCK_HASH"));
                    block.setPreviousHash(rs.getString("PREVIOUS_HASH"));
                    block.setNonce(rs.getInt("NOUNCE"));
                    block.setDifficulty(rs.getInt("DIFFICULTY"));
                    block.setId(rs.getInt("id"));
                    
                blockchain.add(block);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOBlock.class.getName()).log(Level.SEVERE, null, ex);
        }
        return blockchain;
    } 
    
    public static boolean deleteAll(){
        return DAO.deleteAll("Block");
    }
    
    
}

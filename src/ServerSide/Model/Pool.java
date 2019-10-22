package ServerSide.Model;
import ClientSide.Model.Thread.ThMinningBlock;
import ClientSide.Model.Transaction;
import DAO.DAOBlock;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * @author adston
 */
public class Pool{
    
    public Pool(Blockchain blockchain){
        this.pool = new ArrayList();
        this.blockchain = blockchain;
    }
    
    private final Blockchain blockchain;
    private ArrayList<Block> pool;
    private AvaliationList al = new AvaliationList(this);
    
    private int id = DAOBlock.getLastId();
    
    
    public void addOnBlockchain(Block block){
        if( !this.blockchain.addOnBlockchain(block) ){
            System.out.println("Ocorreu um erro ao adicionar a Blockchain");
        }
    }
    
    public boolean addTransaction(Transaction t){
        Block b = this.createNewBlock();
        t.setBlock_id(id);
        id++;
        
        boolean add = b.add_transation(t);
        
        System.out.println("Server: mineirando no servidor");
        b.hashTransactions();
        
        Thread th = new Thread( new ThMinningBlock(b) );
        System.out.println("Servidor: mineirando ...");
        th.start();
    
        try {
            th.join();
            if( this.al.addOnMaster(b) ){
                this.sendToValidaton(b);
                
                System.out.println("Bloco Enviado para validação dos clientes");
                return true;
            }else{
                System.out.println("Este block já existe");
                return false;
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Pool.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;

    }
    
    
    private void sendToValidaton(Block tempBlock){
        this.blockchain.getSbs().getConnecteds().sendToValidation(tempBlock);
    }
    
    public String showBlock(){
        String content = "";
        for(Block b : this.pool){
            content += b.toString() + "\n";
        }
        
        return content;
    }

    public void addToAvalition(Block b){
        this.al.add(b);
    }
    
    public Block createNewBlock(){
        Block block = new Block();
        block.setTimeStamp( new Timestamp(System.currentTimeMillis()) );
        
        if( this.pool.size() > 0 )
            block.setPreviousHash();
        
        return block;
    }
    
    public AvaliationList getAl() {
        return al;
    }

    public void setAl(AvaliationList al) {
        this.al = al;
    }
    
    
    
    public void addBlock(Block block){
        this.pool.add(block);
    }
    
    public int getSize(){
        return this.pool.size();
    }
    
    public Block getLast(){
        if(this.getSize() > 0){
            return pool.get(this.getSize()-1);
        }else{
            return null;
        }
    }

    public ArrayList<Block> getPool() {
        return pool;
    }

    public void setPool(ArrayList<Block> pool) {
        this.pool = pool;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Blockchain getBlockchain() {
        return blockchain;
    }
    
    
    
    
}

package ServerSide.Model;

import ClientSide.Model.Transaction;
import DAO.DAOBlock;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.LinkedHashSet;
import java.util.Set;
/**
 * @author adston
 */
public class Blockchain implements Serializable{
    
    public Blockchain(ServerBlockchainSocket sbs){
        this.pool = new Pool(this);
//        this.blockchain = new ArrayList();
        this.blockchain = new LinkedHashSet();
        this.tempBlock = this.createNewBlock();
        this.sbs = sbs;
    }
    
//    private final List<Block> blockchain;
    private Set<Block> blockchain;
    private transient final Pool pool;
    private Block tempBlock;
    private transient final ServerBlockchainSocket sbs;
            
    public void minning(Block minning){
        this.pool.addToAvalition(minning);
    }
    
    
    
    
    public void addTransaction( Transaction transaction ){
        this.pool.addTransaction(transaction);
        System.out.println("Server: Adicionado ao pool");
        /*boolean add = this.tempBlock.add_transation(transaction);
        
        if( !add ){
            this.pool.add(this.tempBlock);
            
            this.tempBlock = this.createNewBlock();
            this.addTransaction(transaction);
        }*/

    }
    
    public Block createNewBlock(){
        Block block = new Block();
        block.setTimeStamp( new Timestamp(System.currentTimeMillis()) );
        
        if( this.blockchain.size() > 0 )
            block.setPreviousHash( this.getLast().getHash() );
        
        return block;
    }
    
    public boolean addOnBlockchain(Block block){
        if(this.getSize() > 0)
           block.setPreviousHash( this.getLast().getHash() );
        
           if(DAOBlock.saveBlock(block)){
                if( this.blockchain.add(block) ){
                    System.out.println("Server: Block = "+block.toString());
                    return true;
                }else{
                    System.out.println("Server: Block ja existe");
                    return false;
                }
           }
           System.out.println("Server: Nao foi possivel adicionar");
           return false;
    }
    
    public Block getLast(){
        if(this.getSize()>0)
            return this.blockchain.stream().skip(this.blockchain.size()-1).findFirst().get();
        
    return null;
    }
    
    public int getSize(){
        return this.blockchain.size();
    }

    @Override
    public String toString() {
        return "Blockchain{" + "blockchain Blocos: "+this.showBlock()
                +"\nPool Blocos= " + this.pool.showBlock() 
                + "\nTemp Blocos: "+ tempBlock.toString() +'}';
    }
    
    public String showBlock(){
        String content = "";
        for(Block b : this.blockchain){
            content += b.toString() + "\n";
        }
        return content;
    }

    public Block getTempBlock() {
        return tempBlock;
    }

    public void setTempBlock(Block tempBlock) {
        this.tempBlock = tempBlock;
    }

    public Set<Block> getBlockchain() {
        return blockchain;
    }

    public Pool getPool() {
        return pool;
    }

    public ServerBlockchainSocket getSbs() {
        return sbs;
    }
    
    
}

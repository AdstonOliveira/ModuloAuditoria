package ServerSide.Model;

import ClientSide.Model.Transaction;
import java.io.Serializable;
import java.util.ArrayList;
/**
 *
 * @author adston
 */
public class Blockchain implements Serializable{
    
    public Blockchain(ServerBlockchainSocket sbs){
        this.pool = new Pool(this);
        this.blockchain = new ArrayList();
        this.tempBlock = this.createNewBlock();
        this.sbs = sbs;
    }
    private final ArrayList<Block> blockchain;
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
        block.setTimeStamp( System.currentTimeMillis() );
        
        if( this.blockchain.size() > 0 )
            block.setPreviousHash( this.getLast().getHash() );
        
        return block;
    }
    
    public boolean addOnBlockchain(Block block){
        if(this.getSize() > 0){
           block.setPreviousHash( this.getLast().getHash() );
           //implantar distribuição
//           if( block.mineBlock(block.getDifficulty()) ){
                this.blockchain.add(block);
                System.out.println(block.toString());
                return true;
//           }
        }
        return false;
    }
    
    public Block getLast(){
        if(this.getSize()>0){
            return this.blockchain.get(this.getSize()-1);
        }
        return this.blockchain.get(0);
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

    public ArrayList<Block> getBlockchain() {
        return blockchain;
    }

    public Pool getPool() {
        return pool;
    }

    public ServerBlockchainSocket getSbs() {
        return sbs;
    }
    
    
}

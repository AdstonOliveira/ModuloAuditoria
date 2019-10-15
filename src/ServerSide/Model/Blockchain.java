package ServerSide.Model;

import ClientSide.Model.Transaction;
import java.util.ArrayList;
/**
 *
 * @author adston
 */
public class Blockchain {
    
    public Blockchain(ServerBlockchainSocket sbs){
        this.pool = new Pool(this);
        this.blockchain = new ArrayList();
        this.tempBlock = new Block(1);
        this.sbs = sbs;
    }
    
    private final ArrayList<Block> blockchain;
    private final Pool pool;
    private Block tempBlock;
    private final ServerBlockchainSocket sbs;
            
    public void addTransaction(Transaction transaction){
        boolean add = this.tempBlock.add_transation(transaction);
        
        if( !add ){
            this.pool.add(this.tempBlock);
            this.tempBlock = new Block(1);
            this.addTransaction(transaction);
        }

    }
    
    
    public boolean addOnBlockchain(Block block){
        if(this.getSize() > 0){
           block.setPreviousHash( this.getLast().getHash() );
           //implantar distribuição
//           if( block.mineBlock(block.getDifficulty()) ){
                this.blockchain.add(block);
                return true;
//           }
           
        }else{
            block.setPreviousHash("Genesis Block");
//            if( block.mineBlock(block.getDifficulty()) ){
                this.blockchain.add(block);
                return true;
//            }
        }
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

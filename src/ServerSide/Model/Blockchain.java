package ServerSide.Model;

import ClientSide.Model.Thread.ThMinningBlock;
import ClientSide.Model.Transaction;
import DAO.DAOBlock;
import java.io.Serializable;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * @author adston
 */
public class Blockchain implements Serializable{
    
    public Blockchain(ServerBlockchainSocket sbs){
        this.sbs = sbs;
        try {
            this.blockchain = DAOBlock.getBlockchain();
            System.out.println("Blockchain server side: " + this.blockchain.size());
        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao carregar a blockchain");
            Logger.getLogger(Blockchain.class.getName()).log(Level.SEVERE, null, ex);
            
        }

        this.pool = new Pool(this);
        
    }
    
    public Blockchain(List<Block> list, ServerBlockchainSocket sbs){
        this.blockchain = list;
        System.out.println( "Blockchain client size: " + this.blockchain.size() );
        this.sbs = sbs;
        this.pool = new Pool(this);
    }
    
    private List<Block> blockchain;
//    private Set<Block> blockchain;
    private transient final Pool pool;
//    private Block tempBlock;
    private transient final ServerBlockchainSocket sbs;
            
    public void minning(Block minning){
        this.pool.addToAvalition(minning);
    }
    
    
    public void addTransaction( Transaction transaction ){
        this.pool.addTransaction(transaction);
        System.out.println("Server: Adicionado ao pool");
  
    }
    
    public Block createNewBlock(){
        Block block = new Block();
        block.setTimeStamp( new Timestamp(System.currentTimeMillis()) );
        
        if( this.blockchain.size() > 0 )
            block.setPreviousHash( this.getLast().getHash() );
        
        return block;
    }
    
    public boolean addOnBlockchain( Block block ){
        if(this.getSize() > 0)
           block.setPreviousHash();
        
           if( DAOBlock.saveBlock(block) ){
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
        if( this.getSize() > 0 )
            return this.blockchain.stream().skip(this.blockchain.size()-1).findFirst().get();
        else
            return this.createNewBlock();
            
    }
    
    public int getSize(){
        return this.blockchain.size();
    }

    @Override
    public String toString() {
        return "Blockchain{" + "blockchain Blocos: "+this.showBlock()
                +"\nPool Blocos= " + this.pool.showBlock() +'}';
    }
    
    public String showBlock(){
        
        String content = "";
        for (Iterator<Block> it = this.blockchain.iterator(); it.hasNext();) {
            Block b = it.next();
            content += b.toString() + "\n";
        }
        return content;
    }

    
  public boolean isChainValid() throws InterruptedException {
        //loop through blockchain to check hashes:
        if( this.blockchain.size() == 1 ){
            Block a = this.blockchain.get(0);
            
            if(!a.getPreviousHash().equalsIgnoreCase("First Block")){
                System.out.println("Este bloco deveria ser o primeiro ");
                return false;
            }
            
            String hash = a.getHash();
            Thread t = new Thread( new ThMinningBlock(a) );
            t.start();
            t.join();
            
            String compareHash = a.getHash();
            return hash.equals(compareHash);
        }else{
            for(int i = 0; i < this.blockchain.size()-1; i++){
                Block init = this.blockchain.get(i);
                Block next = this.blockchain.get(i+1);
                
                    if( !next.getPreviousHash().equals(init.getHash()) ){
                        System.out.println("Cadeia nao  esta correta");
                        return false;
                    }
                {
                    String hash = init.getHash();
                    Thread t = new Thread( new ThMinningBlock(init) );
                        t.start();
                        t.join();
                    String compare = init.getHash();

                    if( !hash.equalsIgnoreCase(compare) ){
                        System.out.println("A mineiracao nao bate pro bloco: " + init);
                        return false;
                    }

                }
                {
                    if( i == this.blockchain.size()-2 ){
                        String hashN = next.getHash();
                        Thread tN = new Thread( new ThMinningBlock(next) );
                        tN.start();
                        tN.join();
                        String compareN = next.getHash();

                        if( !hashN.equalsIgnoreCase(compareN) ){
                            System.out.println("A mineiracao nao bate pro bloco: " + next);
                            return false;
                        }
                    }
                }
            }
        }
        
        System.out.println("Blockchain valida");
        return true;
    }  
  
    public List<Block> getBlockchain() {
        return blockchain;
    }

    public Pool getPool() {
        return pool;
    }

    public ServerBlockchainSocket getSbs() {
        return sbs;
    }
    
    public void saveMe(){
        for(Block b : this.blockchain){
           b.saveMe();
        }
    }
    

    
}

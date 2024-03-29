package ServerSide.Model;
import ClientSide.Model.Thread.ThMinningBlock;
import ClientSide.Model.Transaction;
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
    
    public void addOnBlockchain(Block block){
        if( !this.blockchain.addOnBlockchain(block) ){
            System.out.println("Ocorreu um erro ao adicionar a Blockchain");
        }
    }
    
    public void addTransaction(Transaction t){
        Block b = this.createNewBlock();
        boolean add = b.add_transation(t);
        System.out.println("Server: mineirando no servidor");
        
        Thread th = new Thread( new ThMinningBlock(b) );
        th.start(); 
    
        try {
            th.join();
            System.out.println("Servidor finalizou primeira mineiracao");
            this.sendToValidaton(b);
            System.out.println("Bloco Enviado ao cliente");
        } catch (InterruptedException ex) {
            Logger.getLogger(Pool.class.getName()).log(Level.SEVERE, null, ex);
        }
        

        
        this.al.add( this.getLast() );
        this.al.compareAll();

    }
    
    private void sendToValidaton(Block tempBlock){
//        this.pool.add(tempBlock);
        
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
        if(this.al.getToAvaliation().size() >= this.blockchain.getSbs().getConnecteds().size()/2){
            if( this.al.compareAll() ){
                this.blockchain.addOnBlockchain(b);
                System.out.println("Adicionei o bloco a blockchain");
            }
        }
    }
    public Block createNewBlock(){
        Block block = new Block();
        block.setTimeStamp( System.currentTimeMillis() );
        
        if( this.pool.size() > 0 )
            block.setPreviousHash( this.getLast().getHash() );
        
        this.pool.add(block);
        
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
    
    
}

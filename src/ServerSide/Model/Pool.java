package ServerSide.Model;
import java.util.ArrayList;
/**
 * @author adston
 */
public class Pool {
    public Pool(Blockchain blockchain){
        this.pool = new ArrayList();
        this.blockchain = blockchain;
    }
    
    private final Blockchain blockchain;
    private final ArrayList<Block> pool;
    private AvaliationList al = new AvaliationList(this);
    
    public void addOnBlockchain(Block block){
        if( !this.blockchain.addOnBlockchain(block) ){
            System.out.println("Ocorreu um erro ao adicionar a Blockchain");
        }
    }
    
    public void add(Block tempBlock){
        this.pool.add(tempBlock);
        
        System.out.println("Enviando para consenso ...");
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
            if(this.al.compareAll()){
                this.blockchain.addOnBlockchain(b);
                System.out.println("Adicionei a blockchain");
            }
        }
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

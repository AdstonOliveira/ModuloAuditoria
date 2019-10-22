package ServerSide.Model;

import java.util.ArrayList;

/**
 * @author adston
 */
public class AvaliationList {
    private final Pool pool;
    private ArrayList<Block> toAvaliation = new ArrayList();
    private ArrayList<Block> blockMaster = new ArrayList();
    
    public AvaliationList(Pool pool){
        this.pool = pool;
    }
    
    public boolean addOnMaster( Block b ){
        
        if(this.blockMaster.size() > 0){
            for(Block m : blockMaster)
                
                if( b.getHash().equals( m.getHash() ) ){
                    System.out.println("Bloco ja existe no master");
                    
                    return false;  
                }
            
        }
        this.blockMaster.add(b);
        return true;
    }
    
    public void add(Block b){
        System.out.println("Server: Recebido para avaliar");
        this.toAvaliation.add(b);
        Block master = this.getOrigin(b);
        ArrayList<Block> toEvaluation = toEvaluation(b);
        
        if( toEvaluation.size() == this.pool.getBlockchain().getSbs().getConnecteds().size() ){
            
            if( this.compareAll( master, toEvaluation) ){
                this.pool.addOnBlockchain(master);
            }else{
                System.out.println("O bloco nao é valido! não será adicionado a blockchain");
            }
        }
    }

    public ArrayList<Block> toEvaluation( Block toCompare ){
        ArrayList<Block> equals = new ArrayList();
        Block master = this.getOrigin(toCompare);
        
        for(Block b : this.toAvaliation){
            if(b.getId() == master.getId())
                equals.add(b);
            }

        
        return equals;
    }
    
    
    
    public Block getOrigin(Block b){
        
        for( Block m : this.blockMaster ){
            if( b.getId() == m.getId() )
                return b;
        }
        
        return null;
    }
    
    
    public boolean compareAll( Block origin, ArrayList<Block> toAvaliation ){
        int count = 0;
        
        for( Block e : toAvaliation ){
            if( e.getId() == origin.getId() ){
                if( e.getHash().equals( origin.getHash() ) ){
                    count++;
                }else
                    count--;
            
                
             this.toAvaliation.remove(e);
            }
        }
        if( count > this.pool.getBlockchain().getSbs().getConnecteds().size() / 2 ){
            System.out.println("A maioria validou o bloco");
            this.toAvaliation.clear();
            return true;
        }
        return false;
    }

    public ArrayList<Block> getToAvaliation() {
        return toAvaliation;
    }

    public void setToAvaliation(ArrayList<Block> toAvaliation) {
        this.toAvaliation = toAvaliation;
    }
    
    
    
    
}

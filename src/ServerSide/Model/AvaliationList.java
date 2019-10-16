package ServerSide.Model;

import java.util.ArrayList;

/**
 * @author adston
 */
public class AvaliationList {
    private final Pool pool;
    private ArrayList<Block> toAvaliation = new ArrayList();
    
    public AvaliationList(Pool pool){
        this.pool = pool;
    }
    
    public void add(Block b){
        System.out.println("Recebido para avaliar");
        this.toAvaliation.add(b);
    }
    
    public boolean compareAll(){
        boolean equal = false;
        for(int i = 0; i < this.toAvaliation.size(); i++){
            for(int j = i; j < this.toAvaliation.size()-1; j++){
                if(this.toAvaliation.get(i).getHash().equals( this.toAvaliation.get(j).getHash() ) ){
                    equal = true;
                }else{
                    equal=false;
                }
            }
        }
        
        return equal;
    }

    public ArrayList<Block> getToAvaliation() {
        return toAvaliation;
    }

    public void setToAvaliation(ArrayList<Block> toAvaliation) {
        this.toAvaliation = toAvaliation;
    }
    
    
    
    
}

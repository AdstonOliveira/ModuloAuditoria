package ServerSide.Model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author adston
 */
public class toAvaliation implements Serializable{
    
    private final Block block;
    private final int connectedsNodes;
    private ArrayList<Boolean> validate;
    
    public toAvaliation( Block b, int nodes ){
        this.validate = new ArrayList();
        this.block = b;
        this.connectedsNodes = nodes;
    }
    
    /**
     * @param valid Informe se foi validado */
    public void addAvaliation( boolean valid ){
        this.validate.add(valid);
    }
    
    
    
    public boolean isValid(){
        int valid = 0;
        int inValid = 0;
            
        for( int i = 0; i <= this.validate.size(); i++ ){
            if(this.validate.get(i) == true){
                valid ++;
                if(valid > connectedsNodes / 2)
                    return true;
                
            }else{
                
                inValid ++;
                if( inValid > connectedsNodes / 2 )
                    return false;
                
            }
        }

        return false;
    
    }
    
    
    
    
}

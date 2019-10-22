package ClientSide.Model;

import Tools.Util;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author adston
 */
public class ValidateTransaction {
    private Transaction t;

    public ValidateTransaction(Transaction t){
        this.t = t;
    }


    public boolean validate(){
       try {
            //Verifica se o arquivo condiz com o hash
            File f = t.writeFileFromArray();
//            JOptionPane.showMessageDialog(null, f, "Adulterar?", 0);
            String newHash = Util.applySHA512( f );
            
            
            if( newHash.equals( t.getHash_transaction_file() ) ){
                
                return this.checkHashTransaction();
            }else{
                return false;
            }
            
        } catch (IOException ex) {
            Logger.getLogger(Transaction.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Falha ao gerar hash do arquivo");
        } 
       
        return false;
    }
    
   public boolean checkHashTransaction(){
       String hash = t.getHash();
       t.hashTransaction();
       String novo = t.getHash();
       
       return hash.equals( novo );
   }




    
}

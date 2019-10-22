package ClientSide.Model;

import Tools.Util;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

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
            
            System.out.println("Primeiro hashT: "+ t.getHash());
            
            if( newHash.equals( t.getHash_transaction_file() ) ){
                System.out.println("Arquivos são iguais");
                System.out.println("File Hash gerado: " + newHash);
                return this.checkHashTransaction();
            }else{
                System.out.println("Arquivos são diferentes");
                System.out.println("File Hash gerado: " + newHash);
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

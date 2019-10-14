package ClientSide.Model.Serialize;

import ClientSide.Model.Transaction;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author adston
 */
public class SerializeTransaction {
 
    private String path = "c://transactions//";
    private File serialized;
    
    public boolean serializeTransaction(Transaction t){
        File directory = new File(path);
        File transaction = null;
        
        if(!directory.exists())
            directory.mkdir();
        
        transaction = new File( directory + "\\"+ t.getHash() );
            if(!transaction.exists()){
                try {
                    transaction.createNewFile();
                } catch (IOException ex) {
                    Logger.getLogger(SerializeTransaction.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                transaction.delete();
            }
            
            ObjectOutputStream objOutput;
                try {
                    objOutput = new ObjectOutputStream(new FileOutputStream(transaction));
                    objOutput.writeObject(t);
                    objOutput.close();
                    serialized = transaction;
                return true;
                } catch (IOException ex) {
                    Logger.getLogger(SerializeTransaction.class.getName()).log(Level.SEVERE, null, ex);
                return false;
                }
    }
    
    /*
    public static ArrayList<Object> lerArquivoBinario(String nomeArq) {
      ArrayList<Object> lista = new ArrayList();
      try {
        File arq = new File(nomeArq);
        if (arq.exists()) {
           ObjectInputStream objInput = new ObjectInputStream(new FileInputStream(arq));
           lista = (ArrayList<Object>)objInput.readObject();
           objInput.close();
        }
      } catch(IOException erro1) {
          System.out.printf("Erro: %s", erro1.getMessage());
      } catch(ClassNotFoundException erro2) {
          System.out.printf("Erro: %s", erro2.getMessage());
      }
    
      return(lista);
    }
    */

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public File getSerialized() {
        return serialized;
    }

    public void setSerialized(File serialized) {
        this.serialized = serialized;
    }
        
            
        
        
    
}

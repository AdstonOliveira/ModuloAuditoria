package Model;

import Tools.RandID;
import Tools.StringUtil;
import java.io.File;
import java.io.IOException;
import javax.swing.JOptionPane;
/**
 * @author adston
 */
public final class Transaction implements I_Transaction{
    
    private int id;
    private String sender;
    private String hash;
    private String previous_hash = "Primeira Transacao do bloco";
    private File transacao;
    private String hash_file;

    public Transaction(){
        
    }
    public Transaction(String teste){
        this.id = RandID.newID();
        this.sender = "abc";
        this.hash = "bcd";
    }
    /** Inicia a transação com um arquivo gerando seu hash
     */
    public Transaction(File file){
        
        this.id = RandID.newID();
        this.sender = "Em testes";
        this.transacao = file;
        
        try {
            this.hash_file = StringUtil.applySHA(file);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex, sender, id);
        }
        
        this.hashTransaction();
        
        JOptionPane.showMessageDialog(null, "Transaction: " + this.toString() );
    }
    
    @Override
    public String toString() {
        return "Transaction{" + "id=" + id + ", sender=" + sender + ", hash_file=" + this.hash_file + ", hash_T=" 
                + this.hash +" previous TransactionHash: "+ this.previous_hash +"\n"+'}';
    }
    
    
    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getSender() {
        return this.sender;
    }

    @Override
    public void setSender(String sender) {
        this.sender = sender;
    }

    @Override
    public void setHash(String hash) {
        this.hash = hash;
    }

    @Override
    public String getHash() {
        return this.hash;
    }
    
    /** Cria um hash para a transacao atual */
    public void hashTransaction(){
        String value = Integer.toString(this.id) + this.sender + this.previous_hash + this.hash_file;
        
        this.hash = StringUtil.applySha256(value);
    }
    
    public String getPrevious(){
        return this.previous_hash;
    }
    public void setPrevious(String previous_hash){
        this.previous_hash = previous_hash;
    }
   
}

package ClientSide.Model;

import Tools.RandID;
import Tools.Util;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import javax.swing.JOptionPane;
/**
 * @author adston
 */
public final class Transaction implements I_Transaction, Serializable{
    
    private transient Client client;
    private long timestamp;
    private int id;
    private String transaction_hash;
    private String previous_transaction_hash = "FirstInBlock";
    private File transaction_file;
    private String hash_transaction_file;

    /** Inicia a transação com um arquivo gerando seu hash_transaction
     * @param client Informar cliente criador
     * @param file Informar Arquivo a enviar */
    
    public Transaction(Client client, File file){
        
        this.client = client;
        this.id = RandID.newID(); // Precisa verificar os numeros no banco
        this.transaction_file = file;
        
        try { // Cria o hash_transaction do arquivo
            this.hash_transaction_file = Util.applySHA512(this.transaction_file);
            this.hashTransaction();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao criar hash da transacao", "Hash Fail",0);
        }finally{
            System.out.println(this.toString());
        }
        
        
    }
    
    @Override
    public String toString() {
        return "Transaction{\n" + "id=" + id + ", sender=" + this.client.getName() + ",\nHash_file=" 
                + this.hash_transaction_file + ",\nhash_T=" 
                + this.transaction_hash +" previous TransactionHash: "+ this.previous_transaction_hash +"\n"+'}';
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
        return this.client.getName();
    }

    @Override
    public void setSender(String sender) {
        System.out.println("Nome do cliente nao pode ser alterado");
    }

    @Override
    public void setHash(String hash) {
        this.transaction_hash = hash;
    }

    @Override
    public String getHash() {
        return this.transaction_hash;
    }
    
    /* Cria um hash_transaction para a transacao atual */
    public void hashTransaction(){
        
        String value = Integer.toString(this.id) + this.client.getName() + this.previous_transaction_hash 
                + this.hash_transaction_file;
        
        this.transaction_hash = Util.applySha512(value);
        System.out.println("Transaction Hash: " + this.transaction_hash);
    }
    
    public String getPrevious(){
        return this.previous_transaction_hash;
    }
    public void setPrevious(String previous_hash){
        this.previous_transaction_hash = previous_hash;
    }
   
}

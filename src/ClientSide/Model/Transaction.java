package ClientSide.Model;

import DAO.DAOTransaction;
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
    private static final long serialVersionUID = 1L;
    
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
    /* Cria um hash_transaction para a transacao atual */
    public void hashTransaction(){
        this.timestamp = System.currentTimeMillis();
        
        String value = Integer.toString(this.id) + this.client.getName() + this.timestamp + this.previous_transaction_hash 
                + this.hash_transaction_file;
        
        this.transaction_hash = Util.applySha512(value);
        DAOTransaction.saveTransaction(this);
        
        System.out.println("Transaction Hash: " + this.transaction_hash);
    }
    
    @Override
    public String toString() {
        return "Transaction{\n" + "id= " + id + ", sender= " + this.client.getName()
                +" Data registro: " + Util.formatDate(timestamp)
                + ",\nHash_file= " + this.hash_transaction_file 
                + ",\nhash_T= " + this.transaction_hash 
                +"\nprevious TransactionHash: "+ this.previous_transaction_hash 
                +"\n"+'}';
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
    
    public String getPrevious(){
        return this.previous_transaction_hash;
    }
    public void setPrevious(String previous_hash){
        this.previous_transaction_hash = previous_hash;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getTransaction_hash() {
        return transaction_hash;
    }

    public void setTransaction_hash(String transaction_hash) {
        this.transaction_hash = transaction_hash;
    }

    public String getPrevious_transaction_hash() {
        return previous_transaction_hash;
    }

    public void setPrevious_transaction_hash(String previous_transaction_hash) {
        this.previous_transaction_hash = previous_transaction_hash;
    }

    public File getTransaction_file() {
        return transaction_file;
    }

    public void setTransaction_file(File transaction_file) {
        this.transaction_file = transaction_file;
    }

    public String getHash_transaction_file() {
        return hash_transaction_file;
    }

    public void setHash_transaction_file(String hash_transaction_file) {
        this.hash_transaction_file = hash_transaction_file;
    }
   
}

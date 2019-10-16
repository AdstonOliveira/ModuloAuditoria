package ClientSide.Model;

import ClientSide.Model.Serialize.SerializeTransaction;
import Tools.Util;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 * @author adston
 */
public final class Transaction implements I_Transaction, Serializable{
    private static final long serialVersionUID = 1L;
    private transient SerializeTransaction st;
    
    private transient ClientSocket client;
    private long timestamp;
    
    private int id;
    private String transaction_hash;
    private String previous_transaction_hash = "FirstInBlock";
    
    private transient File transaction_file;
    private String hash_transaction_file;
    private byte[] file_content;
    /** Inicia a transação com um arquivo gerando seu hash_transaction
     * @param client Informar cliente criador
     * @param file Informar Arquivo a enviar */
    public Transaction(ClientSocket client, File file){
        
        this.client = client;
//        this.id = RandID.newID(); // Precisa verificar os numeros no banco
        this.transaction_file = file;
        this.FileToArray();
        
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
        
        String value = this.client.getName() + this.timestamp + this.previous_transaction_hash 
                + this.hash_transaction_file;
        
        this.transaction_hash = Util.applySha512(value);
        
        System.out.println("Transaction Hash: " + this.transaction_hash);
    }
    
    public void FileToArray(){
        int len = (int) this.transaction_file.length();
            this.file_content = new byte[len];
            
            try {
                FileInputStream inFile = new FileInputStream(this.transaction_file);
                inFile.read(this.file_content,0 , len);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Transaction.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Transaction.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }
    //Cria um arquivo com o conteudo do array
    public void writeFileFromArray(){
        String path = "c://tmp_";
        File directory = new File(path);
        
        if(!directory.isDirectory())
            directory.mkdir();
        
        File sourceFile = new File(path + "//"+this.hash_transaction_file);
        
        FileOutputStream file = null;
        try {
            file = new FileOutputStream(sourceFile);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Transaction.class.getName()).log(Level.SEVERE, null, ex);
        }
         BufferedOutputStream output; 
        output = new BufferedOutputStream(file);
        try {
            output.flush();
            output.write(this.file_content, 0, this.file_content.length); 
            output.flush();
            output.close();
            System.out.println("Created");
        } catch (IOException ex) {
            Logger.getLogger(Transaction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public String toString() {
        return "Transaction{\n" + "file_size=(kb) " + this.file_content.length + ", sender= " + this.client.getName()
                +" Data registro: " + Util.formatDate(timestamp)
                + ",\nHash_file= " + this.hash_transaction_file 
                + ",\nhash_T= " + this.transaction_hash 
                +"\nprevious TransactionHash: "+ this.previous_transaction_hash 
                +"\n"+'}';
    }
    
    public boolean serializeMe(){
        this.st = new SerializeTransaction();
        if( this.st.serializeTransaction(this) ){
            System.out.println(st.getSerialized().getAbsoluteFile());
            return true;
        }
        return false;
    }

    public SerializeTransaction getSt() {
        return st;
    }

    public void setSt(SerializeTransaction st) {
        this.st = st;
    }

    public byte[] getFile_content() {
        return file_content;
    }

    public void setFile_content(byte[] file_content) {
        this.file_content = file_content;
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

    public ClientSocket getClient() {
        return client;
    }

    public void setClient(ClientSocket client) {
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

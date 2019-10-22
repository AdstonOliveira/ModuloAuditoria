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
import java.sql.Timestamp;
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
    private Timestamp timestamp;

    private String hash_block;
    private String transaction_hash;
    private String previous_transaction_hash = "FirstInBlock";
    
    private File transaction_file;
    private String hash_transaction_file;
    private byte[] file_content;
    
    private String sender;
    private int block_id;
    
    /** Inicia a transação com um arquivo gerando seu hash_transaction
     * @param client Informar cliente criador
     * @param file Informar Arquivo a enviar */
    public Transaction(ClientSocket client, File file){
        this.client = client;
        this.sender = this.client.getName();
        this.mountFile(file);
        
    }
    
    public Transaction(File file){
        this.mountFile(file);
    }
    
    
    
    
    public void mountFile(File file){
        this.transaction_file = file;
        this.FileToArray();
        
        try { // Cria o hash_transaction do arquivo
            this.hash_transaction_file = Util.applySHA512(this.transaction_file);
            this.timestamp = new Timestamp( System.currentTimeMillis() );
            this.hashTransaction();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao criar hash do arquivo", "Hash Fail",0);
        }
        
    }
    
    
    /* Cria um hash_transaction para a transacao atual */
    public void hashTransaction(){
        String value = this.sender + this.timestamp + this.previous_transaction_hash 
                + this.hash_transaction_file;
        
        this.transaction_hash = Util.applySha512(value);
    
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
    public File writeFileFromArray(){
        String path = ".//files";
        File directory = new File(path);
        
        if(!directory.isDirectory())
            directory.mkdir();
        
        File sourceFile = new File(path + "//"+this.transaction_file.getName());
        
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
        
        
        
        return sourceFile;
    }
    
    @Override
    public String toString() {
        return "Transaction{id: + " + this.block_id + "\n" + "file_size=(kb) " + this.file_content.length + ", sender= "+this.sender
                +" \nData registro: " + this.timestamp
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


    @Override
    public void setBlockId(int id){
        this.block_id = id;
    }
    
    @Override
    public int getBlockId(){
        return this.block_id;   
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
    public String getSender() {
        return this.sender;
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

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
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

    public String getHash_block() {
        return hash_block;
    }

    public void setHash_block(String hash_block) {
        this.hash_block = hash_block;
    }

    public int getBlock_id() {
        return block_id;
    }

    public void setBlock_id(int block_id) {
        this.block_id = block_id;
    }
    
    @Override
    public String getBlockHash() {
        return this.hash_block;
    }

    @Override
    public void setBlockHash(String hashBlock) {
        this.hash_block = hashBlock;
    }
    
   
}

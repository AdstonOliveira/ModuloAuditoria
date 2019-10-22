package ServerSide.Model;
import ClientSide.Model.I_Transaction;
import ClientSide.Model.Transaction;
import ClientSide.Model.Thread.ThMinningBlock;
import DAO.DAOBlock;
import Tools.Util;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * @author adston
 */
public class Block implements Serializable{
    public Block(){
    }
    /** inicializa um novo bloco com qtde de transações definida*/
    public Block(int amount_transactions){
        this.amount_transactions = amount_transactions;
    }

    private Timestamp timeStamp; //data atual 
    private String hash = "nao calculado"; // Hash do atual
    private String previousHash = "First Block";
    private final ArrayList<Transaction> transactions = new ArrayList(); //Dado a ser adicionado ao bloco
    private String hash_transactions;
    
    public boolean isValid = false;
    
    private int id;
    private int nonce = 0; // quantidades hash gerados
    private int amount_transactions = 1; //Quantidade de transações suportadas neste bloco
    private int difficulty = 5;

    @Override
    public String toString() {
        return "Previous hash"+this.previousHash 
                + "\nTimestamp: "+this.timeStamp + " ID: "+this.id 
                + "\nNounce: "+Integer.toString(this.nonce) 
                + "\nHash: "+this.hash_transactions + "\nAmount: "+this.amount_transactions + " Dificulty: "+this.difficulty;
    }
    /** Exibe cada transação contida no bloco
     * @return  */
    public String showTransactions(){
        String content = "";
        for(I_Transaction t : transactions){
            Transaction transaction = (Transaction) t;
            content += "\n"+transaction.toString();
        }
        return content;
    }
    
    
    public boolean calculate_hash(){
        return this.mineBlock();
        //Enviar alerta para consenso
    }
    
    /** Processo de mineiração do atual bloco
    * A dificuldade esta relacionada a qtde de 0's no inicio do hash
    * Cria o hash das transações para então criar seu proprio hash
    */
    public boolean mineBlock( ) {
        this.hashTransactions();
        
        Thread t = new Thread( new ThMinningBlock(this) );
        t.start();
        try {
            t.join();
            return true;
        } catch (InterruptedException ex) {
            Logger.getLogger(Block.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public String calculateHash() {
        
        String value = this.previousHash + this.timeStamp + this.id 
                + Integer.toString(this.nonce) + this.hash_transactions + this.amount_transactions + this.difficulty;
        
        String calculatedhash = Util.applySha512( value );
    
        return calculatedhash;
    }
    
    /** Adiciona cada transação ao atual bloco, adicionando o hash da transação 
     * anterior a atual
     * @param transaction
     * @return */
    
    public boolean add_transation( Transaction transaction ){
        if( !this.isFull() ){
            if( this.transactions.size() > 0 )
                transaction.setPrevious( this.getLastTransaction().getHash() );
            
            this.transactions.add(transaction);

            return true;    
        }
        
        return false;
    }
    
    /** Cria o hash das transações*/
    public String hashTransactions(){
        String thash = "";
        
            for(I_Transaction It :  this.transactions){
                Transaction t = (Transaction) It;
                   System.out.println("Transaction Hash: " + t.getHash());
                   thash += t.getHash();
            }
            
        this.hash_transactions = Util.applySha512(thash);
        return hash;
    }

    
    public boolean isFull(){
        return this.transactions.size() == this.amount_transactions;
    }

    public String getHash_transactions() {
        return hash_transactions;
    }

    public void setHash_transactions(String hash_transactions) {
        this.hash_transactions = hash_transactions;
    }

    public int getAmount_transactions() {
        return amount_transactions;
    }

    public void setIsValid(boolean isValid) {
        this.isValid = isValid;
    }

    
    public void setAmount_transactions(int amount_transactions) {
        this.amount_transactions = amount_transactions;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public void setPreviousHash(String previousHash) {
        this.previousHash = previousHash;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }
    
    public Timestamp getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Timestamp timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public ArrayList<Transaction> getDados() {
        return transactions;
    }

    public int getNonce() {
        return nonce;
    }

    public void setNonce(int nonce) {
        this.nonce = nonce;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    public void getNewId(){
        this.id = DAOBlock.getLastId() + 1;
    }
    
    public Transaction getLastTransaction(){
        if( this.transactions.size() > 0 ){
            Transaction t = (Transaction) this.transactions.get(this.transactions.size()-1);
            return t;
        }
        return null;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public boolean isValid() {
        return isValid;
    }
    
    public void setPreviousHash(){
        this.previousHash = DAOBlock.getLastHash();
    }
    
    
    
    
    public boolean validBlock(){
        String value = this.previousHash + (this.timeStamp)  + this.id
                + Integer.toString(this.nonce) + this.hash_transactions + this.amount_transactions + this.difficulty;
        
        String calculatedhash = Util.applySha512( value );
        
         return this.hash.equalsIgnoreCase(calculatedhash);
        
    }

    
}

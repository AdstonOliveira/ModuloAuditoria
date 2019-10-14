package ServerSide.Model;
import ClientSide.Model.I_Transaction;
import ClientSide.Model.Transaction;
import ClientSide.Model.Thread.ThMinningBlock;
import Tools.Util;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
/**
 * @author adston
 */
public class Block {
    public Block(){
    }
    /** inicializa um novo bloco com qtde de transações definida*/
    public Block(int amount_transactions){
        this.amount_transactions = amount_transactions;
    }

    private long timeStamp; //data atual 
    private String hash = "nao calculado"; // Hash do atual
    private String previousHash = "First Block";
    private final ArrayList<I_Transaction> transactions = new ArrayList(); //Dado a ser adicionado ao bloco
    private String hash_transactions;
    
    private int nonce = 0; // quantidades hash gerados
    private int amount_transactions = 1; //Quantidade de transações suportadas neste bloco
    private int difficulty = 5;

    @Override
    public String toString() {
        return "Block{\n" + "timeStamp= " + new Date(timeStamp) + "\nhash= " + hash + ",\npreviousHash= " 
                + previousHash + "\nTransações: " + this.showTransactions() + '}';
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
//        t.run();
        return true;
    }
    
    public String calculateHash() {
        this.timeStamp = System.currentTimeMillis();
        
        String value = this.previousHash + Long.toString(this.timeStamp) 
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

            JOptionPane.showMessageDialog(null,"Adicionando ao Bloco\n"+
                    "Total Transaçoes Registradas: " + this.transactions.size() );
            return true;    
        }
//             this.calculate_hash();
            return false;
    }
    /** Cria o hash das transações*/
    private String hashTransactions(){
        String thash = "";
            for(I_Transaction It :  this.transactions){
                Transaction t = (Transaction) It;
                    System.out.println("Transaction Hash: " + t.getHash());
                   thash += t.getHash();
            }
            
        this.hash_transactions = Util.applySha512(thash);
        System.out.println("hash for all Transactions\n" + this.hash_transactions);
        return hash;
    }
    
    public boolean isFull(){
        return this.transactions.size() == this.amount_transactions;
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
    
    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public ArrayList<I_Transaction> getDados() {
        return transactions;
    }

    public int getNonce() {
        return nonce;
    }

    public void setNonce(int nonce) {
        this.nonce = nonce;
    }
    
    public Transaction getLastTransaction(){
        if( this.transactions.size() > 0 ){
            Transaction t = (Transaction) this.transactions.get(this.transactions.size()-1);
            return t;
        }
        return null;
    }
    
}

package Model.ServerSide;
import Model.ClientSide.I_Transaction;
import Model.ClientSide.Transaction;
import Model.Thread.ThMinningBlock;
import Tools.RandID;
import Tools.Util;
import java.util.ArrayList;
import javax.swing.JOptionPane;
/**
 * @author adston
 */
public class Block {
    public Block(){
        this.autoID();
    }
    /** inicializa um novo bloco com qtde de transações definida*/
    public Block(int amount_transactions){
        this.autoID();
        this.amount_transactions = amount_transactions;
    }
    private int id;
    private long timeStamp; //data atual 
    private String hash = "nao calculado"; // Hash do atual
    private String previousHash = "First Block";
    private final ArrayList<I_Transaction> data = new ArrayList(); //Dado a ser adicionado ao bloco
    private String hash_transactions;
    
    private int nonce = 0; // quantidades hash gerados
    private int amount_transactions = 1; //Quantidade de transações suportadas neste bloco
    private int difficulty = 5;

    @Override
    public String toString() {
        return "Block{" + "id=" + id + ", timeStamp=" + timeStamp + ", hash=" + hash + ", previousHash=" 
                + previousHash + "\nTransações: " + this.showTransactions() + '}';
    }
    /** Exibe cada transação contida no bloco */
    public String showTransactions(){
        String content = "";
        for(I_Transaction t : data){
            Transaction transaction = (Transaction) t;
            content += transaction.toString();
        }
        return content;
    }
    
    
    public int getId() {
        return id;
    }
    
    private void autoID(){
        this.id = RandID.newID();
        
    }
    public void setId(int id) {
        this.id = id;
    }
    
    public boolean isFull(){
        return this.data.size() == this.amount_transactions;
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
    
    public boolean calculate_hash(){
        this.mineBlock(this.difficulty);
        //Enviar alerta para consenso
        return true;
    }
    
    /** Processo de mineiração do atual bloco
     * A dificuldade esta relacionada a qtde de 0's no inicio do hash
     */
    
    public boolean mineBlock( int difficulty ) {
        /*Prova de trabalho tentando diferentes valores de variáveis ​​no bloco até que seu hash comece 
        com um certo número de 0s.*/
        //Cria um array com tamanho da dificuldade 
//        JOptionPane.showMessageDialog(null, "Aguarde. O Bloco esta sendo mineirado","Minnig Block",1);
//        
//        String target = new String( new char[difficulty] ).replace('\0', '0');
//        
//            while ( !hash.substring(0, difficulty).equals(target) ) { // Divide o hash da posicao 0, ate a qta 0
//                this.nonce++;
//                this.hash = calculateHash(); // o nonce serve para a quantidade de hash gerados...
//            } // Gera varios hash's, ate que algum contenha a qtde desejadas de 0 no inicio
//
//            JOptionPane.showMessageDialog(null, "Bloco mineirado!!!: " + this.toString() );
//            return true;
        Thread t = new Thread(new ThMinningBlock(this));
        t.start();
        return true;

    }
    
    /** Cria o hash das transações para então criar seu proprio hash*/
    public String calculateHash() {
        this.hashTransactions();
        
        String value = this.id + this.previousHash + Long.toString(this.timeStamp) 
                + Integer.toString(this.nonce) + this.hash_transactions + this.amount_transactions + this.difficulty;
        
        String calculatedhash = Util.applySha256( value );
    
        return calculatedhash;
    }
    
    /** Adiciona cada transação ao atual bloco, adicionando o hash da transação 
     * anterior a atual
     */
    public boolean add_transation( Transaction transaction ){
        if( !this.isFull() ){
            if( this.data.size() > 0)
                transaction.setPrevious( this.getLastTransaction().getHash() );
            
            this.data.add(transaction);

            JOptionPane.showMessageDialog(null,"Adicionando ao Bloco : "+this.getId() 
                    +"\n Transações já adicionadas: " + this.data.size() );
            return true;    
        }else{
//             this.calculate_hash();

        return false;
        }
        
    }
    /** Cria o hash das transações*/
    private String hashTransactions(){
        String hash = "";
        
            for(I_Transaction It :  this.data){
                Transaction t = (Transaction) It;
                   hash += t.getHash();
            }
            
        this.hash_transactions = Util.applySha256(hash);
        System.out.println("hash all Transactions\n" + hash);
        return hash;
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
        return data;
    }

//    public void setDados(ArrayList<I_Transaction> dados) {
//        this.data = dados;
//    }

    public int getNonce() {
        return nonce;
    }

    public void setNonce(int nonce) {
        this.nonce = nonce;
    }
    
    public Transaction getLastTransaction(){
        if(this.data.size() > 0 ){
            Transaction t = (Transaction) this.data.get(this.data.size()-1);
            return t;
        }
        
        return null;
    }
    
    
    
    
    
}

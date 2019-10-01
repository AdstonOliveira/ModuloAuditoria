package Model;

import Tools.RandID;
import Tools.StringUtil;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * @author adston
 */
public class Block {
    public Block(){
        this.autoID();
    }
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
    private int amount_transactions = 5; //Quantidade de transações suportadas neste bloco
    private int difficulty = 5;

    @Override
    public String toString() {
        return "Block{" + "id=" + id + ", timeStamp=" + timeStamp + ", hash=" + hash + ", previousHash=" 
                + previousHash + "\nTransações: " + this.showTransactions() + '}';
    }
    
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
    
    
    public void mineBlock( int difficulty ) {
        /*Prova de trabalho tentando diferentes valores de variáveis ​​no bloco até que seu hash comece 
        com um certo número de 0s.*/
        //Cria um array com tamanho da dificuldade 
        String target = new String( new char[difficulty] ).replace('\0', '0');
        
            while ( !hash.substring(0, difficulty).equals(target) ) { // Divide o hash da posicao 0, ate a qta 0
                this.nonce++;
                this.hash = calculateHash(); // o nonce serve para a quantidade de hash gerados...
            } // Gera varios hash's, ate que algum contenha a qtde desejadas de 0 no inicio

            
            JOptionPane.showMessageDialog(null, "Bloco mineirado!!!: " + this.toString() );
//            JOptionPane.showMessageDialog(null, "Print in Mine " + Block.printBlock(this) );
    }
    
    private String calculateHash() {
        this.hashTransactions();
        
        String value = this.id + this.previousHash + Long.toString(this.timeStamp) 
                + Integer.toString(this.nonce) + this.hash_transactions + this.amount_transactions + this.difficulty;
        
        String calculatedhash = StringUtil.applySha256( value );
    
        return calculatedhash;
    }
    
    public boolean add_transation( Transaction transaction ){
        if( !this.isFull() ){
            
            if( this.data.size() > 0)
                transaction.setPrevious( this.getLastTransaction().getHash() );
            
            this.data.add(transaction);

            JOptionPane.showMessageDialog(null,"Adicionando ao Bloco : "+this.getId() 
                    +"\n Transações adicionadas: " + this.data.size() + " transacoes" );
            return true;    
        }else{
            if( this.calculate_hash() ){
                JOptionPane.showMessageDialog(null, "Hash: " + this.getHash() +"\n"+this.showTransactions() );
            }
            
            return false;
        }
        
    }
    
    private String hashTransactions(){
        String hash = "";
        
            for(I_Transaction It :  this.data){
                Transaction t = (Transaction) It;
                   hash += t.getHash();
            }
            
        this.hash_transactions = StringUtil.applySha256(hash);
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

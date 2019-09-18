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
    private int id;
    private long timeStamp; //data atual 
    private String hash = "nao calculado"; // Hash do atual
    private String previousHash = "First Block";
    private final ArrayList<I_Transaction> data = new ArrayList(); //Dado a ser adicionado ao bloco
    private int nonce = 0; // quantidades hash gerados
    private final int amount_transactions = 5; //Quantidade de transações suportadas neste bloco
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

            
            JOptionPane.showMessageDialog(null, "Block Mined!!!: " + this.hash);
//            JOptionPane.showMessageDialog(null, "Print in Mine " + Block.printBlock(this) );
    }
    
    private String calculateHash() {
        String calculatedhash = 
            StringUtil.applySha256( previousHash + Long.toString(timeStamp)
                + Integer.toString(nonce)+ this.data);
    
        return calculatedhash;
    }
    
    public boolean add_transation( Transaction transaction ){
        if( !this.isFull() ){
            System.out.println("Não esta cheio");
            this.data.add(transaction);
            JOptionPane.showMessageDialog(null,"Bloco : "+this.getId() +"\n" + this.data.size() + " transacoes" );
            return true;    
        }else{
            System.out.println("Não é possivel adicionar uma nova transacao a este bloco"
                    + "\nEste bloco esta cheio, calculando hash");
            if(this.calculate_hash()){
                System.out.println("Hash: " + this.getHash());
            }
            
            return false;
        }
        
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
    
    
    
    
    
}

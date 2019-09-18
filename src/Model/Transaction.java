package Model;

import Tools.RandID;

/**
 * @author adston
 */
public class Transaction implements I_Transaction{
    
    private int id;
    private String sender;
    private String hash;
    private String transacao;

    public Transaction(){
        
    }
    public Transaction(String teste){
        this.id = RandID.newID();
        this.sender = "abc";
        this.hash = "bcd";
        this.transacao = "transacao Teste";
    }
    
    @Override
    public String toString() {
        return "Transaction{" + "id=" + id + ", sender=" + sender + ", hash=" + hash + ", transacao=" 
                + transacao + "\n"+'}';
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
    
}

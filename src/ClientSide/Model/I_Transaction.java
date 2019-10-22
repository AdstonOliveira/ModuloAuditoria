package ClientSide.Model;
/**
 * @author adston
 */
public interface I_Transaction{
//    public int id;
//    private String remetente;
//    private BoletimUrna boletim;
//    private String hash;
    
    public String getBlockHash();
    public void setBlockHash(String hashBlock);
    
    public String getSender();
    public void setSender(String sender);
    
    public String getHash();
    public void setHash(String hash);
    
    @Override
    public String toString();
    
    public void setBlockId(int id);
     public int getBlockId();
    
}

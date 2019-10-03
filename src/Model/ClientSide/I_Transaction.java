package Model.ClientSide;
/**
 * @author adston
 */
public interface I_Transaction{
//    public int id;
//    private String remetente;
//    private BoletimUrna boletim;
//    private String hash;
    
    public int getId();
    public void setId(int id);
    
    public String getSender();
    public void setSender(String sender);
    
    public String getHash();
    public void setHash(String hash);
    @Override
    public String toString();
}

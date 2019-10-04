package Tools.Security;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author adston
 */
public class Key_Store {
    private Private_Key privateKey;
    
    protected String instance = "JKS";
    protected KeyStore ks;
    protected String ks_path = "C:\\par.jks";
    protected String ks_alias = "moduloauditoria";
    protected FileInputStream fis;// = new FileInputStream("C:\\par.jks");
    protected String pwd ="ifsp100%";
    
    public Key_Store(){
        this.setFis();
    }
    
    public Key_Store(String instance, String ks_path, String ks_alias, String pwd){
        this.instance = instance; this.ks_path = ks_path; 
        this.ks_alias = ks_alias; this.pwd = pwd;
        this.setFis();
    }
    
    private boolean getKeyStore(){
        try {
            this.ks = KeyStore.getInstance(this.instance);
            System.out.println("sucess");
        } catch (KeyStoreException ex) {
            Logger.getLogger(Key_Store.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return this.ks != null;
    }
    
    private boolean setFis(){
        this.getKeyStore();
        
        try {
            this.fis = new FileInputStream(this.ks_path);
            return true;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Key_Store.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public String signHash(String toSign){
       this.privateKey = new Private_Key(this);

       return this.privateKey.signText(toSign);
    }
}

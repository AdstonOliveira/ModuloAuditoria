package Tools.Security;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.KeyManagerFactory;

/**
 * @author adston
 */
public class Key_Store {
    private Private_Key privateKey;
    
    private String instance = "JKS";
    private KeyStore ks;
    private String ks_path = "C:\\par.jks";
    private String ks_alias = "moduloauditoria";
    private FileInputStream fis;// = new FileInputStream("C:\\par.jks");
    private String pwd ="ifsp100%";
    private KeyManagerFactory kmf; 
    
    public Key_Store() throws NoSuchAlgorithmException{
        this.setFis();
        this.kmf = this.getKMFactory("SunX509");
    }
    
    public Key_Store(String instance, String ks_path, String ks_alias, String pwd, String kmf_factory) throws NoSuchAlgorithmException{
        this.instance = instance; this.ks_path = ks_path; 
        this.ks_alias = ks_alias; this.pwd = pwd;
        this.setFis();
        this.kmf = this.getKMFactory(kmf_factory);
    }
    
    private boolean getKeyStore(){
        try {
            this.ks = KeyStore.getInstance(this.instance);
        } catch (KeyStoreException ex) {
            Logger.getLogger(Key_Store.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return this.ks != null;
    }
    private void initKS() throws IOException, NoSuchAlgorithmException, CertificateException{
        this.ks.load(this.fis, this.pwd.toCharArray());
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
    
    private KeyManagerFactory getKMFactory(String algorithm) throws NoSuchAlgorithmException{
        return KeyManagerFactory.getInstance(algorithm);
    }
    public KeyManagerFactory getKmf(){
        return this.kmf;
    }
    
    public boolean initKMF(){
        if(this.kmf != null){
            try {
                this.initKS();
                this.kmf.init( this.ks, this.pwd.toCharArray() );
                return true;
            } catch (KeyStoreException ex) {
                Logger.getLogger(Key_Store.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(Key_Store.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            } catch (UnrecoverableKeyException ex) {
                Logger.getLogger(Key_Store.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            } catch (IOException ex) {
                Logger.getLogger(Key_Store.class.getName()).log(Level.SEVERE, null, ex);
            } catch (CertificateException ex) {
                Logger.getLogger(Key_Store.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;

    }

    public Private_Key getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(Private_Key privateKey) {
        this.privateKey = privateKey;
    }

    public String getInstance() {
        return instance;
    }

    public void setInstance(String instance) {
        this.instance = instance;
    }

    public KeyStore getKs() {
        return ks;
    }

    public void setKs(KeyStore ks) {
        this.ks = ks;
    }

    public String getKs_path() {
        return ks_path;
    }

    public void setKs_path(String ks_path) {
        this.ks_path = ks_path;
    }

    public String getKs_alias() {
        return ks_alias;
    }

    public void setKs_alias(String ks_alias) {
        this.ks_alias = ks_alias;
    }

    public FileInputStream getFis() {
        return fis;
    }

    public void setFis(FileInputStream fis) {
        this.fis = fis;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
    
    
    
}

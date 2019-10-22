package Tools.Security;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author adston
 */
public class Private_Key{
    private PrivateKey privateKey;

    public Private_Key(Key_Store store){
        this.getPrivateKey(store);
    }
    
    private boolean getPrivateKey(Key_Store store){
        try {
            store.getKs().load( store.getFis(), store.getPwd().toCharArray() ); // There are other ways to read the password. 
            KeyStore.ProtectionParameter keyPass = new KeyStore.PasswordProtection(store.getPwd().toCharArray());
            KeyStore.PrivateKeyEntry privKeyEntry = (KeyStore.PrivateKeyEntry) store.getKs().getEntry(store.getKs_alias(), keyPass);
            this.privateKey = privKeyEntry.getPrivateKey();
            
            return true;
        } catch (IOException | NoSuchAlgorithmException | CertificateException | UnrecoverableEntryException | KeyStoreException ex) {
            Logger.getLogger(Private_Key.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public String signText(String text){
        try {
            Signature signature = Signature.getInstance("SHA256withRSA");
            signature.initSign(this.privateKey);
                //Read the string into a buffer
                byte[] textInBytes = text.getBytes();
                //update signature with data to be signed
                signature.update(textInBytes);
                //sign the data
                byte[] signedInfo = signature.sign();
            
                StringBuffer hexString = new StringBuffer(); // This will contain hash as hexidecimal
                    //Converter to hexa
                    for (int i = 0; i < signedInfo.length; i++) {
                        String hex = Integer.toHexString(0xff & signedInfo[i]);
                            if (hex.length() == 1) {
                                hexString.append('0');
                            }
                        hexString.append(hex);
                    }
            
            return hexString.toString();
            
        } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException ex) {
            Logger.getLogger(Private_Key.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return "";
        }

    
    
    
}

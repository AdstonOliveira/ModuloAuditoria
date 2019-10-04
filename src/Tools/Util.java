package Tools;

import Tools.Security.Key_Store;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Util {
    //Applies Sha256 to string and returns the result. 
    public static String applySha256(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-512");
            //Applies sha256 to our input, 
            byte[] hash = digest.digest(input.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer(); // This will contain hash as hexidecimal

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return ( hexString.toString() );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    

    public static String applySHA(File input) throws FileNotFoundException, IOException {

        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Applies sha256 to our input, 
        InputStream is = new FileInputStream(input);
        byte[] buffer = new byte[8192];
        int read = 0;
        String output = null;
        try {
            while ((read = is.read(buffer)) > 0) {
                digest.update(buffer, 0, read);
            }
            byte[] hash = digest.digest();
            BigInteger bigInt = new BigInteger(1, hash);
            output = bigInt.toString(16);
        } catch (IOException e) {

        } finally {
            try {
                is.close();
            } catch (IOException e) {

            }
        }

//        try {
            return (output);
//        } catch (KeyStoreException ex) {
//            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (NoSuchAlgorithmException ex) {
//            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (CertificateException ex) {
//            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (UnrecoverableEntryException ex) {
//            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (InvalidKeyException ex) {
//            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (SignatureException ex) {
//            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return "nao deu";
    }

//        public static String applySha256(I_Transaction input){		
//		try {
//			MessageDigest digest = MessageDigest.getInstance("SHA-256");	        
//			//Applies sha256 to our input, 
//			byte[] hash = digest.digest(input.getBytes("UTF-8"));	        
//			StringBuffer hexString = new StringBuffer(); // This will contain hash as hexidecimal
//                        
//			for (int i = 0; i < hash.length; i++) {
//				String hex = Integer.toHexString(0xff & hash[i]);
//				if(hex.length() == 1) hexString.append('0');
//				hexString.append(hex);
//			}
//                        
//			return hexString.toString();
//		}
//		catch(Exception e) {
//			throw new RuntimeException(e);
//		}
//                }
    public static String signHash(String hash) throws KeyStoreException, FileNotFoundException, IOException, NoSuchAlgorithmException, CertificateException, UnrecoverableEntryException, InvalidKeyException, SignatureException {
       Key_Store ks = new Key_Store();
        System.out.println(ks.signHash(hash));
       
        return "";
    }

    public static String formatDate(long date) {
        Date dt = new Date(date);
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS");
        df.setTimeZone(TimeZone.getTimeZone("GMT"));
        return (df.format(dt));
    }
}

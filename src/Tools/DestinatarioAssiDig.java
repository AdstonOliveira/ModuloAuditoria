/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;

/**
 * @author adston
 */
public class DestinatarioAssiDig {
   //Implementar em Thread 
    public static String recebeMensagem(PublicKey pubKey, String mensagem, byte[] assinatura) throws
        NoSuchAlgorithmException, InvalidKeyException, SignatureException {
    
        Signature clientSig = Signature.getInstance("DSA");  
        clientSig.initVerify(pubKey);  
        clientSig.update(mensagem.getBytes());  
          
        if (clientSig.verify(assinatura)) {  
           //Mensagem corretamente assinada
          return ("A Mensagem recebida foi assinada corretamente.");
        } else {
           //Mensagem não pode ser validada
          return ("A Mensagem recebida NÃO pode ser validada.");
        }  
   }
}

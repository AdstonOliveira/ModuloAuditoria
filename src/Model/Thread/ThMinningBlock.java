package Model.Thread;

import Model.ServerSide.Block;
import javax.swing.JOptionPane;

/**
 * @author adston
 */
public class ThMinningBlock implements Runnable{
    public ThMinningBlock(Block block){
        this.block = block;
    }
    private Block block;
    public volatile boolean finish = false;
    
    @Override
    public void run(){
        
        JOptionPane.showMessageDialog(null, "Aguarde, bloco sera mineirado","Minning Block",1);
        
        while(!finish){
            String target = new String( new char[block.getDifficulty()] ).replace('\0', '0');
            int nonce = block.getNonce();
            while ( !block.getHash().substring(0, block.getDifficulty()).equals(target) ) { // Divide o hash da posicao 0, ate a qta 0
                block.setNonce(nonce++);
                block.setHash( block.calculateHash() ); // o nonce serve para a quantidade de hash gerados...
            } // Gera varios hash's, ate que algum contenha a qtde desejadas de 0 no inicio

            
            finish = true;
        }
        JOptionPane.showMessageDialog(null, "Bloco mineirado!!!: " + this.block.toString() );
    }
    
    
}

package ServerSide.Model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author adston
 */
public class Connecteds {
 
    private ArrayList<ConnectedClient> connecteds = new ArrayList();
    
    public Connecteds(){
       this.checkconnects.start();
    }
    
    public void add(ConnectedClient connected){
       this.connecteds.add(connected);
    }
    
    public ConnectedClient addNew(ConnectedClient client){
        this.add(client);

        return client;
    }
    
    public void getIPS(){
        for(ConnectedClient c : connecteds)
            System.out.println(Arrays.toString(c.getSocket().getInetAddress().getAddress()) );
        
    }
    
    public void remove(ConnectedClient cc){
        for(ConnectedClient c : this.connecteds){
            if(c.equals(cc)){
                this.connecteds.remove(c);
                System.out.println("Socket removido");
            }
        }
    }
    
    Thread checkconnects = new Thread( new Runnable() {
        @Override
        public void run() {
            while (true) {
                if (connecteds.size() > 0) {
                    for (ConnectedClient c : connecteds) {
                        if ( !c.getSocket().isConnected() ) {
                            try {
                                c.getSocket().shutdownInput();
                                c.getSocket().shutdownOutput();
                                c.getSocket().close();
                            } catch (IOException ex) {
                                Logger.getLogger(Connecteds.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            Connecteds.this.connecteds.remove(c);
                            System.out.println("Removendo desconectado ");
                        }
                    }
                }
            }
        }    
    });
    public int size(){
        return this.connecteds.size();
    }
  
    
    /** Passar o meu Socket para nao me reenviar
     * @param cliente => Socket para comparacao
     * @param msg => Mensagem a ser enviada*/
    
    public void sendToValidation(Block toValid){
        
        if( this.connecteds.size() > 0 ){
            for(ConnectedClient c : connecteds){
                System.out.println("Enviando para validacao ...");
        
                try {
                    c.getOos().writeObject(toValid);
                    c.getOos().flush();
                } catch (IOException ex) {
                    Logger.getLogger(Connecteds.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }else{
            System.err.println("Nenhum socket conectado");
        }
        
    }
    
    public void sendToAll(Object o){
        if( this.connecteds.size() > 0 ){
            for(ConnectedClient c : connecteds){
                System.out.println("Enviando para validacao ...");
        
                try {
                    c.getOos().writeObject(o);
                    c.getOos().flush();
                } catch (IOException ex) {
                    Logger.getLogger(Connecteds.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }else{
            System.err.println("Nenhum socket conectado");
        }
        
    }
    
    
    
}

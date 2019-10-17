package ServerSide.Model;

import ClientSide.Model.ClientSocket;
import ClientSide.Model.Connected;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author adston
 */
public class Connecteds {
 
    private ArrayList<Connected> connecteds = new ArrayList();
    
    public Connecteds(){
       this.checkconnects.start();
    }
    
    public void add(Connected connected){
        if( connected.isValid() )
            this.connecteds.add(connected);
    }
    
    public Connected addNew(ClientSocket client){
        Connected connected = new Connected(client);
        this.add(connected);

        return connected;
    }
    
    public void getIPS(){
        for(Connected c : connecteds)
            System.out.println( c.getIP() );
        
    }
    
    Thread checkconnects = new Thread( new Runnable() {
        @Override
        public void run() {
            while (true) {
                if (Connecteds.this.connecteds.size() > 0) {
                    for (Connected c : Connecteds.this.connecteds) {
                        if (!c.getClient().getSocket().isConnected()) {
                            try {
                                c.getClient().getSocket().close();
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
    
    /*
    private boolean duplicated( String name ){
      if(this.connecteds.size() > 0)
          for(Connected c : connecteds){
              System.out.println(c.getClient().getIP());
          }
          
      return false;
    }
    
    /** Passar o meu Socket para nao me reenviar
     * @param cliente => Socket para comparacao
     * @param msg => Mensagem a ser enviada*/
    
    public void sendToValidation(Block toValid){
        
        if( this.connecteds.size() > 0 ){
            for(Connected c : this.connecteds){
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
    
    
}

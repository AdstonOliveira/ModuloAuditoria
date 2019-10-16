package ServerSide.Model;

import ClientSide.Model.ClientSocket;
import ClientSide.Model.Connected;
import java.io.IOException;
import java.io.ObjectOutputStream;
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
    
    public void addNew(ClientSocket client){
        Connected connected = new Connected(client);
        this.add(connected);
        System.out.println("Cliente conectou ao servidor");
    }
    
    public void getIPS(){
        for(Connected c : connecteds)
            System.out.println( c.getSocket().getInetAddress().getHostAddress() );
        
    }
    
    Thread checkconnects = new Thread( ()->{
        while(true)
            if(this.connecteds.size() > 0){
                for(Connected c : this.connecteds){
                    if( !c.getSocket().isConnected() ){
                        this.connecteds.remove(c);
                        System.out.println("Removendo desconectado ");
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
        System.out.println("Entrou para envio");
        
        if( this.connecteds.size() > 0 )
            for(Connected c : this.connecteds){
                System.out.println("Enviando para validacao ...");
                ObjectOutputStream os;
        
                try {
                    os = new ObjectOutputStream( c.getSocket().getOutputStream() );
                    os.writeObject(toValid);
                    os.flush();
                } catch (IOException ex) {
                    Logger.getLogger(Connecteds.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
            System.err.println("Nenhum socket conectado");
        }
        
    }
    
    
}

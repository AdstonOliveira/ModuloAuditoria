package ServerSide.Model;

import ClientSide.Model.Client;
import ClientSide.Model.Connected;
import java.util.ArrayList;

/**
 * @author adston
 */
public class Connecteds {
 
    private ArrayList<Connected> connecteds = new ArrayList();
    
    public void add(Connected connected){
        if( connected.isValid() && !this.duplicated( connected.getName() ) )
            this.connecteds.add(connected);
        
    }
    public void addNew(Client client){
        Connected connected = new Connected(client);
        this.add(connected);
    }
    
    public void getIPS(){
        for(Connected c : connecteds)
            System.out.println( c.getSocket().getInetAddress().getHostAddress() );
        
    }
    
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
    
    public void sendAll( Client cliente, String msg ){
       if( this.connecteds.size() > 0 )
          for( Connected c : this.connecteds )
              if(c.getSocket() != cliente.getSocket())
              c.sendMessage(msg);
   }
    
}

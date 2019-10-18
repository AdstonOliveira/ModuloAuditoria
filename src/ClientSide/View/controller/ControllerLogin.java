package ClientSide.View.controller;

import ClientSide.Model.ClientSocket;
import DAO.DAO;
import ClientSide.View.cliente.Login;
/**
 * @author adston
 */
public class ControllerLogin {
    
    private final Login login = new Login();

        public void openLogin(){
            this.login.setController(this);
            this.login.setVisible(true);
        }
        
        public boolean logar(String nome, String senha){
            if( DAO.login(nome, senha) ){
               ClientSocket client = new ClientSocket(nome);
               ControllerClient controller = new ControllerClient(client);
        
               if( controller.init() ){
                    this.login.dispose();
                    return true;
               }else
                   return false;
            }
            
            return false;
        }
        
        public boolean novo(String nome, String senha){
            return DAO.insertNovo(nome, senha);
        }

    
}

package ClientSide.View.controller;

import ClientSide.Model.Client;
import ClientSide.Model.DAO;
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
               Client client = new Client(nome);
               ControllerClient controller = new ControllerClient(client);
               this.login.dispose();
               return true;
            }
            return false;
        }
        
        public boolean novo(String nome, String senha){
            return DAO.insertNovo(nome, senha);
        }

    
}

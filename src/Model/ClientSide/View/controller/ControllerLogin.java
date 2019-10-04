package Model.ClientSide.View.controller;

import Model.ClientSide.DAO;
import Model.ClientSide.View.Login;
import Model.ClientSide.View.cliente.Dash;
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
               ControllerClient controller = new ControllerClient();
               Dash dash = new Dash();
               controller.open();
               controller.addIFrame(dash);
               return true;
            }
            return false;
        }
        
        public boolean novo(String nome, String senha){
            return DAO.insertNovo(nome, senha);
        }

    
}

package Model.ClientSide.View.controller;

import Model.ClientSide.DAO;
import Model.ClientSide.View.Login;

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
            return DAO.login(nome, senha);
        }
        public boolean novo(String nome, String senha){
            return DAO.insertNovo(nome, senha);
        }

    
}

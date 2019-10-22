import ClientSide.View.controller.ControllerLogin;
import ServerSide.Model.Block;
import ServerSide.Model.ServerBlockchainSocket;


public class Main {
    
    public static void main(String[] args) {
     
        ControllerLogin cl = new ControllerLogin();
        cl.openLogin();
        
        ServerBlockchainSocket sbs = new ServerBlockchainSocket();

    }
    
}

import ClientSide.View.controller.ControllerLogin;
import File_Handling.File_Reader;
import ServerSide.Model.ServerBlockchainSocket;
import java.io.File;


public class Main {
    
    public static void main(String[] args){
     
        ControllerLogin cl = new ControllerLogin();
        cl.openLogin();
        
        ServerBlockchainSocket sbs = new ServerBlockchainSocket(); 
//     File_Reader fr = new File_Reader(new File("C:\\Users\\adston\\Documents\\BWEB_1t_AC_101020181938\\bu_1001845\\bu_1001845.csv"));
//     fr.init();
//     for(String each : fr.getHeaderB()){
//         System.out.println(each);
//     }
    }
    
}
/*
Lista de immplementação

THLISTENCLIENT - L94




*/
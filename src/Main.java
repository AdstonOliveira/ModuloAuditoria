
import View.cliente.Dash;
import View.controller.ControllerDesktop;


public class Main {
    
    public static void main(String[] args) {

        ControllerDesktop cd = new ControllerDesktop();
        cd.open();
        Dash dash = new Dash();
        cd.addIFrame(dash);
    
        
    }
    
    
}

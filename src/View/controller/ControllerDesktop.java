package View.controller;

import Tools.SelectXML;
import View.cliente.DesktopCliente;
import javax.swing.JInternalFrame;

/**
 * @author adston
 */
public class ControllerDesktop {
    private DesktopCliente desktop;
    private SelectXML selectXML;
    
    public void open(){
        this.desktop = new DesktopCliente();
        this.desktop.setVisible(true);
    }
    
    public void addIFrame(JInternalFrame iFrame){
        this.desktop.getDesktop().add(iFrame);
        iFrame.show();
    }
    
    public boolean selectXML(JInternalFrame iFrame){
        if(this.selectXML != null)
            this.selectXML = null;
        
        this.selectXML = new SelectXML();
        
        return this.selectXML.selectFile(iFrame);
    }
    
    public SelectXML getSelectXML(){
        return this.selectXML;
    }
    public void cancelOption(){
        this.selectXML.cancel();
    }
    
    
}

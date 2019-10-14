package ClientSide.View.controller;

import ClientSide.Model.Client;
import ClientSide.Model.Transaction;
import ClientSide.View.cliente.Dash;
import Tools.SelectFile;
import ClientSide.View.cliente.DesktopCliente;
import javax.swing.JInternalFrame;

/**
 * @author adston
 */
public class ControllerClient {
    private DesktopCliente desktop;
    private SelectFile selectXML;
    private Client client;
    private Dash dash;
    
    public ControllerClient(Client client){
        this.client = client;
        this.open();
    }
    
    public void open(){
        this.desktop = new DesktopCliente();
        this.desktop.setVisible(true);
        this.dash = new Dash();
        this.addIFrame(dash);
    }
    
    public void addIFrame(Dash iFrame){
        iFrame.setController(this);
        this.desktop.getDesktop().add(iFrame);
        iFrame.show();
    }
    
    public boolean selectXML(JInternalFrame iFrame){
        if(this.selectXML != null)
            this.selectXML = null;
        
        this.selectXML = new SelectFile();
        
        return this.selectXML.selectXML(iFrame);
    }
    
    public SelectFile getSelectXML(){
        return this.selectXML;
    }
    public void cancelOption(){
        this.selectXML.cancel();
    }

    public void showDetails() {
        this.client.myDetails();
    }

    
    
    public void addTransaction() {
        Transaction transaction = new Transaction( this.client, this.selectXML.getSelected() );
        transaction.serializeMe();
        transaction.writeFileFromArray();
        this.client.sendTransaction(transaction);
//        this.client.getBlockchain().addTransaction(transaction);
    }
    
    
}

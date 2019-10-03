/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
/**
 * @author adston
 */
public class SelectXML {
    public SelectXML(){
       this.jfc = new JFileChooser();   
    }
    
    private File selected;
    private final JFileChooser jfc;
    
    public boolean selectFile(javax.swing.JInternalFrame display){
        this.jfc.setFileFilter( new FileNameExtensionFilter("Somente XML", "xml") );
        this.jfc.setAcceptAllFileFilterUsed(false);
        
            if ( this.jfc.showOpenDialog(display) == JFileChooser.APPROVE_OPTION ){
                this.selected = jfc.getSelectedFile();
                return true;
            }
            
        return false;
    }
    public File getSelected(){
        if(this.selected !=null)
            return this.selected;
        
        return null;
    }
    
    public void cancel(){
        this.jfc.removeAll();
        this.selected = null;
    }
}

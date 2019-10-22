
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFileChooser;

public class Testes {
  
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
   
        File origin = new File(".//files//bu_1000235.csv");
        /*JFileChooser jfc = new JFileChooser(".//files");
        jfc.showOpenDialog(jfc);
        File t = jfc.getSelectedFile();
        System.out.println(t.getAbsolutePath());
        */
        
        BufferedReader br = new BufferedReader(new FileReader(origin));
        
        while(br.ready()){
            System.out.println(br.readLine());
        }
        
        
        
        
        
        
        
        
        
        
    }
    
    
    
    
    
    
    
}

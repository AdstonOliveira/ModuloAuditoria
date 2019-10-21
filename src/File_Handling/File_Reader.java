package File_Handling;

import ClientSide.Model.BU.Urna;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author adston
 */
public class File_Reader {

    protected File file;
    protected int lines_number;
    protected String[] header;
    protected int urna_position;
    
    
    public File_Reader( File file ){
        this.file = file;
        
//        this.init();
    }

    public boolean init(){
        this.lines();
        this.getHeader();
        this.urna_position = this.getPosition("NR_URNA_EFETIVADA");
        return this.createUrn();
    }
    
    public boolean createUrn(){
        BufferedReader br;
        Urna u = new Urna();
        try {
            br = new BufferedReader( new InputStreamReader( new FileInputStream(this.file), "ISO-8859-1") ); //new FileReader(this.file));
            br.readLine(); // Le o cabecalho
            String firstLine = br.readLine();
            String[] values = firstLine.replace("\"","").split(";");
            

            u.setUrna(Integer.valueOf( values[urna_position]) );
            
            u.setCD_CARGA_1_URNA_EFEETIVADA( values[this.getPosition("CD_CARGA_1_URNA_EFETIVADA")] );
            u.setCD_CARGA_2_URNA_EFEETIVADA( values[this.getPosition("CD_CARGA_2_URNA_EFETIVADA")] );
            u.setCD_FLASHCARD_URNA_EFETIVADA(values[this.getPosition("CD_FLASCARD_URNA_EFETIVADA")]);
            u.setDT_ABERTURA( u.DefineDate(values[this.getPosition("DT_ABERTURA")]) );
            u.setDT_ENCERRAMENTO( u.DefineDate(values[this.getPosition("DT_ENCERRAMENTO")]) );
            
            br.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(File_Reader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(File_Reader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u.saveMe();
    }
    
    
    
    protected void lines(){
        if( this.file != null ){
            LineNumberReader linhaLeitura;
            try {
                linhaLeitura = new LineNumberReader(new FileReader(this.file));
                linhaLeitura.skip(this.file.length());
                this.lines_number = linhaLeitura.getLineNumber();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(File_Reader.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(File_Reader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    protected void getHeader(){
        if(this.file != null){
            BufferedReader br;
            try {

                br = new BufferedReader(new InputStreamReader( new FileInputStream(this.file), "ISO-8859-1")); //new FileReader(this.file));
                String titleBuff = br.readLine();
                this.header = titleBuff.replace("\"","").split(";");
                br.close();

            } catch (FileNotFoundException ex) {
                Logger.getLogger(File_Reader.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(File_Reader.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(File_Reader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
    
    protected int getPosition(String search){
        if( this.header.length > 0 ){
            for(int i = 0; i < this.header.length; i++) {
                if( this.header[i].equalsIgnoreCase(search) ){
                    return i;
                }
            }
        }
        return -1;
    }
    
    
    
    
    
    

    
}

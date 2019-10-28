package File_Handling;

import ClientSide.Model.BU.BU;
import ClientSide.Model.BU.Eleicao;
import ClientSide.Model.BU.Municipio;
import ClientSide.Model.BU.Pleito;
import ClientSide.Model.BU.Urna;
import ClientSide.Model.BU.Zona;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
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
    protected String[] values;
    
    
    public File_Reader( File file ){
        this.file = file;
    }

    public boolean init(){
        this.lines();
        this.getHeader();
        this.urna_position = this.getPosition("NR_URNA_EFETIVADA");
        this.values = this.getValues();
        
        return this.createUrn();
    }
    
    public boolean createUrn(){
        Urna u = new Urna();

            u.setUrna(Integer.valueOf( values[urna_position]) );
            
            u.setCD_CARGA_1_URNA_EFEETIVADA( values[this.getPosition("CD_CARGA_1_URNA_EFETIVADA")] );
            u.setCD_CARGA_2_URNA_EFEETIVADA( values[this.getPosition("CD_CARGA_2_URNA_EFETIVADA")] );
            u.setCD_FLASHCARD_URNA_EFETIVADA(values[this.getPosition("CD_FLASCARD_URNA_EFETIVADA")]);
            u.setDT_ABERTURA( u.DefineDate(values[this.getPosition("DT_ABERTURA")]) );
            u.setDT_ENCERRAMENTO( u.DefineDate(values[this.getPosition("DT_ENCERRAMENTO")]) );
            
        if( u.saveMe() ){
            this.createBU(u);
            return true;
        }else{
            return false;
        }
    }
    
    public String[] getValues(){
        String[] values = null;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader( new FileInputStream(this.file), "ISO-8859-1") ) ;
            br.readLine(); // Le o cabecalho
            String firstLine = br.readLine();
            values = firstLine.replace("\"","").split(";");
            br.close();
            return values;
        } catch (FileNotFoundException | UnsupportedEncodingException ex) {
            Logger.getLogger(File_Reader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(File_Reader.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return values;
    }
    
    public void createMunicipio(){
        Municipio mun = new Municipio();
        mun.setCD_MUNICIPIO(Integer.valueOf(values[this.getPosition("CD_MUNICIPIO")]));
        mun.setNM_MUNICIPIO(values[this.getPosition("NM_MUNICIPIO")]);
        mun.setSG_UF(values[this.getPosition("SG_UF")]);
        
        mun.SaveMe();
        
        Zona zona = new Zona();
        zona.setCD_MUNICIPIO(mun.getCD_MUNICIPIO());
        zona.setNR_ZONA(Integer.valueOf(values[this.getPosition("NR_ZONA")]));
        zona.saveMe();
        
    }
    
    
    public void createBU(Urna u){
        BU bu = new BU();
        Pleito pleito = new Pleito();
        
        pleito.setCD_PLEITO(Integer.valueOf(values[this.getPosition("CD_PLEITO") ] ) );
        pleito.setNR_TURNO(Integer.valueOf(values[this.getPosition("NR_TURNO")]));
        pleito.setDT_PLEITO(values[this.getPosition("DT_PLEITO")]);
        
        pleito.saveMe();
        bu.setUrna( u.getUrna() );

        try {
            bu.setDT_GERACAO( ( values[this.getPosition("DT_GERACAO")] ) );
            bu.setHH_GERACAO( values[this.getPosition("HH_GERACAO")] );
            bu.setPLEITO( pleito.getCD_PLEITO() );
        } catch (ParseException ex) {
            Logger.getLogger(File_Reader.class.getName()).log(Level.SEVERE, null, ex);
       }
 
        bu.saveMe();
        this.createMunicipio();
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

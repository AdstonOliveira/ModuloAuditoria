package File_Handling;

import ClientSide.Model.BU.Candidato;
import ClientSide.Model.BU.Cargo;
import ClientSide.Model.BU.Partido;
import DAO.DAOCandidato;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author adston
 */
public class File_Reader_Candidato extends File_Reader{
    
    public File_Reader_Candidato(File file) {
        super(file);
        
        if( super.init() )
            this.createCandidatos();
        else
            System.out.println("Esta urna já foi apurada ...");
        
    }
    
    public void createCandidatos(){
        try {
            BufferedReader br = new BufferedReader( new InputStreamReader( new FileInputStream(this.file), "ISO-8859-1") );
            br.readLine(); //Descarta cabecalho
            String buff = br.readLine();

            while(buff != null){
                String[] values = buff.replace("\"", "").split(";");
                
                Cargo c = new Cargo();
                c.setCd_cargo_pergunta(Integer.valueOf(values[this.getPosition("CD_CARGO_PERGUNTA")]));
                c.setDs_cargo_pergunta(values[this.getPosition("DS_CARGO_PERGUNTA")]);
                c.saveMe();
                
                Partido p = new Partido();
                
                p.setNR_PARTIDO(Integer.valueOf( this.nullTratement( values[this.getPosition("NR_PARTIDO")] ) ) );
                p.setNM_PARTIDO(this.nullTratement(values[this.getPosition("NM_PARTIDO")]));
                p.setSG_PARTIDO(this.nullTratement(values[this.getPosition("SG_PARTIDO")]));
                p.saveMe();
                
                Candidato candidato = new Candidato();
                candidato.setCdCargoPergunta( c.getCd_cargo_pergunta() );
                candidato.setDsCargoPergunta( c.getDs_cargo_pergunta() );
                
                candidato.setCdTipoVotavel( Integer.valueOf(values[this.getPosition("CD_TIPO_VOTAVEL")]));
                candidato.setDsTipoVotavel( values[this.getPosition("DS_TIPO_VOTAVEL")]);
                
                candidato.setNmPartido( this.nullTratement(values[this.getPosition("NM_PARTIDO")] ) );
                candidato.setNrPartido(Integer.valueOf( this.nullTratement(values[this.getPosition("NR_PARTIDO")])) );
                candidato.setSgPartido( this.nullTratement(values[this.getPosition("SG_PARTIDO")] ));
                
                candidato.setNrVotavel(Integer.valueOf(values[this.getPosition("NR_VOTAVEL")]));
                candidato.setNmVotavel( values[this.getPosition("NM_VOTAVEL")] );
                
                candidato.setQtVotos(Integer.valueOf(values[this.getPosition("QT_VOTOS")]));
                
                DAOCandidato.saveCandidato(candidato);
                
                System.out.println("Candidato Salvo: " + candidato.getNmVotavel());

                buff = br.readLine();
            }
            
            br.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(File_Reader_Candidato.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(File_Reader_Candidato.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(File_Reader_Candidato.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String nullTratement(String value){
        if(value.equalsIgnoreCase("#nulo#"))
            value = "0";
        
        return value;
        
    }
}

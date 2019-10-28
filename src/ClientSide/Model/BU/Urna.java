package ClientSide.Model.BU;

import DAO.DAOUrna;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author adston
 */
public class Urna {
    private int urna;
    private String CD_CARGA_1_URNA_EFEETIVADA;
    private String CD_CARGA_2_URNA_EFEETIVADA;
    private String CD_FLASHCARD_URNA_EFETIVADA;
    private Timestamp DT_ABERTURA;
    private Timestamp DT_ENCERRAMENTO;
    
    
    public int getUrna() {
        return urna;
    }

    public void setUrna(int urna) {
        this.urna = urna;
    }


    public String getCD_CARGA_1_URNA_EFEETIVADA() {
        return CD_CARGA_1_URNA_EFEETIVADA;
    }

    public void setCD_CARGA_1_URNA_EFEETIVADA(String CD_CARGA_1_URNA_EFEETIVADA) {
        this.CD_CARGA_1_URNA_EFEETIVADA = CD_CARGA_1_URNA_EFEETIVADA;
    }

    public String getCD_CARGA_2_URNA_EFEETIVADA() {
        return CD_CARGA_2_URNA_EFEETIVADA;
    }

    public void setCD_CARGA_2_URNA_EFEETIVADA(String CD_CARGA_2_URNA_EFEETIVADA) {
        this.CD_CARGA_2_URNA_EFEETIVADA = CD_CARGA_2_URNA_EFEETIVADA;
    }

    public String getCD_FLASHCARD_URNA_EFETIVADA() {
        return CD_FLASHCARD_URNA_EFETIVADA;
    }

    public void setCD_FLASHCARD_URNA_EFETIVADA(String CD_FLASHCARD_URNA_EFETIVADA) {
        this.CD_FLASHCARD_URNA_EFETIVADA = CD_FLASHCARD_URNA_EFETIVADA;
    }

    public Timestamp getDT_ABERTURA() {
        return DT_ABERTURA;
    }

    public void setDT_ABERTURA(Timestamp DT_ABERTURA) {
        this.DT_ABERTURA = DT_ABERTURA;
    }
    public void setDT_ABERTURA(String date){
        java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        java.util.Date data = df.parse(date, new java.text.ParsePosition(0));
        
        this.DT_ABERTURA = new Timestamp( data.getTime() );
    }
    public void describe(String[] array){
        for(String a : array){
            System.out.println(a);
        }
    }
    
    public String DefineDate(String teste){
        String[] old = teste.split(" ");
        
        String[] data = old[0].split("/");
        
        String hour[] = old[1].split(":");
        
        String dateFormated = data[2] + "-" + data[1] + "-" + data[0] + " " + hour[0]+":"+hour[1]+":00";
        
        return (dateFormated);
    }
    
    public void setDT_ENCERRAMENTO(String date){
        java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        java.util.Date data = df.parse(date, new java.text.ParsePosition(0));
        
        this.DT_ENCERRAMENTO = new Timestamp(data.getTime());
    }

    public Timestamp getDT_ENCERRAMENTO() {
        return DT_ENCERRAMENTO;
    }

    public void setDT_ENCERRAMENTO(Timestamp DT_ENCERRAMENTO) {
        this.DT_ENCERRAMENTO = DT_ENCERRAMENTO;
    }

    public boolean saveMe(){
        try {
            return(DAOUrna.SaveUrna(this));
        } catch (SQLException ex) {
            Logger.getLogger(Urna.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    
    @Override
    public String toString() {
        return "Urna{" + "urna=" + urna + ", CD_CARGA_1_URNA_EFEETIVADA=" + CD_CARGA_1_URNA_EFEETIVADA + ", CD_CARGA_2_URNA_EFEETIVADA=" + CD_CARGA_2_URNA_EFEETIVADA + ", CD_FLASHCARD_URNA_EFETIVADA=" + CD_FLASHCARD_URNA_EFETIVADA + ", DT_ABERTURA=" + DT_ABERTURA + ", DT_ENCERRAMENTO=" + DT_ENCERRAMENTO + '}';
    }
    
    
}

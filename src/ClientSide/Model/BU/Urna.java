package ClientSide.Model.BU;

import java.sql.Timestamp;


/**
 * @author adston
 */
public class Urna {
    private int urna;
    private int NR_URNA_EFETIVADA;
    private int CD_CARGA_1_URNA_EFEETIVADA;
    private int CD_CARGA_2_URNA_EFEETIVADA;
    private int CD_FLASHCARD_URNA_EFETIVADA;
    private Timestamp DT_ABERTURA;
    private Timestamp DT_ENCERRAMENTO;

    public int getUrna() {
        return urna;
    }

    public void setUrna(int urna) {
        this.urna = urna;
    }

    public int getNR_URNA_EFETIVADA() {
        return NR_URNA_EFETIVADA;
    }

    public void setNR_URNA_EFETIVADA(int NR_URNA_EFETIVADA) {
        this.NR_URNA_EFETIVADA = NR_URNA_EFETIVADA;
    }

    public int getCD_CARGA_1_URNA_EFEETIVADA() {
        return CD_CARGA_1_URNA_EFEETIVADA;
    }

    public void setCD_CARGA_1_URNA_EFEETIVADA(int CD_CARGA_1_URNA_EFEETIVADA) {
        this.CD_CARGA_1_URNA_EFEETIVADA = CD_CARGA_1_URNA_EFEETIVADA;
    }

    public int getCD_CARGA_2_URNA_EFEETIVADA() {
        return CD_CARGA_2_URNA_EFEETIVADA;
    }

    public void setCD_CARGA_2_URNA_EFEETIVADA(int CD_CARGA_2_URNA_EFEETIVADA) {
        this.CD_CARGA_2_URNA_EFEETIVADA = CD_CARGA_2_URNA_EFEETIVADA;
    }

    public int getCD_FLASHCARD_URNA_EFETIVADA() {
        return CD_FLASHCARD_URNA_EFETIVADA;
    }

    public void setCD_FLASHCARD_URNA_EFETIVADA(int CD_FLASHCARD_URNA_EFETIVADA) {
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
        
        this.DT_ABERTURA = new Timestamp(data.getTime());
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
    
    
    
}

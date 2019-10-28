package ClientSide.Model.BU;

import DAO.DAOBu;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author adston
 */
public class BU {
    
private int Urna;
private Date DT_GERACAO;
private Time HH_GERACAO;
private int PLEITO;

    public int getPLEITO() {
        return PLEITO;
    }

    public void setPLEITO(int PLEITO) {
        this.PLEITO = PLEITO;
    }

    public int getUrna() {
        return Urna;
    }

    public void setUrna(int Urna) {
        this.Urna = Urna;
    }

    public Date getDT_GERACAO() {
        return DT_GERACAO;
    }

    public void setDT_GERACAO(Date DT_GERACAO) {
        this.DT_GERACAO = DT_GERACAO;
    }
    
    public void setDT_GERACAO(String dt_geracao) throws ParseException{
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        this.DT_GERACAO = (Date) formatter.parse(dt_geracao);
        
    }

    public Time getHH_GERACAO() {
        return HH_GERACAO;
    }

    public void setHH_GERACAO(Time HH_GERACAO) {
        this.HH_GERACAO = HH_GERACAO;
    }
    
    public void setHH_GERACAO(String hh_geracao) throws ParseException{
        java.text.SimpleDateFormat formatador = new java.text.SimpleDateFormat("HH:mm:ss"); 
        java.util.Date data = formatador.parse(hh_geracao); 
        Time time = new Time(data.getTime());
        this.HH_GERACAO = time;
    }

    public boolean saveMe(){
        return DAOBu.saveBU(this);
    }


}

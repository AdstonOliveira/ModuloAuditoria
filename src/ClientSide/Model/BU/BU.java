package ClientSide.Model.BU;

import java.sql.Time;
import java.util.Date;

/**
 * @author adston
 */
public class BU {
    
private int Urna;
private Date DT_GERACAO;
private Time HH_GERACAO;
private int CD_ELEICAO;

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

    public Time getHH_GERACAO() {
        return HH_GERACAO;
    }

    public void setHH_GERACAO(Time HH_GERACAO) {
        this.HH_GERACAO = HH_GERACAO;
    }

    public int getCD_ELEICAO() {
        return CD_ELEICAO;
    }

    public void setCD_ELEICAO(int CD_ELEICAO) {
        this.CD_ELEICAO = CD_ELEICAO;
    }



}

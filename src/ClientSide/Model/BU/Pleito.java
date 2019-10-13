/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClientSide.Model.BU;

import java.util.Date;

/**
 *
 * @author adston
 */
class Pleito {
    private int CD_PLEITO;
    private Date DT_PLEITO;
    private int NR_TURNO;

    public Pleito() {
    }

    public Pleito(int CD_PLEITO, Date DT_PLEITO, int NR_TURNO) {
        this.CD_PLEITO = CD_PLEITO;
        this.DT_PLEITO = DT_PLEITO;
        this.NR_TURNO = NR_TURNO;
    }
    
    public int getCD_PLEITO() {
        return CD_PLEITO;
    }

    public void setCD_PLEITO(int CD_PLEITO) {
        this.CD_PLEITO = CD_PLEITO;
    }

    public Date getDT_PLEITO() {
        return DT_PLEITO;
    }

    public void setDT_PLEITO(Date DT_PLEITO) {
        this.DT_PLEITO = DT_PLEITO;
    }

    public int getNR_TURNO() {
        return NR_TURNO;
    }

    public void setNR_TURNO(int NR_TURNO) {
        this.NR_TURNO = NR_TURNO;
    }




}

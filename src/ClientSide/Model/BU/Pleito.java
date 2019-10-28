/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClientSide.Model.BU;

import DAO.DAOPleito;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author adston
 */
public class Pleito {
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
    public void setDT_PLEITO(String dt_pleito){
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            this.DT_PLEITO = (Date) formatter.parse(dt_pleito);
        } catch (ParseException ex) {
            Logger.getLogger(Pleito.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getNR_TURNO() {
        return NR_TURNO;
    }

    public void setNR_TURNO(int NR_TURNO) {
        this.NR_TURNO = NR_TURNO;
    }
    
    public boolean saveMe(){
        return DAOPleito.savePleito(this);
    }




}

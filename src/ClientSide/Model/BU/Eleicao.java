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
class Eleicao {
    private int CD_ELEICAO;
    private String DS_ELEICAO;
    private Date DT_ELEICAO;
    private int CD_PLEITO;

    public Eleicao() {
    }
    
    public Eleicao(int CD_ELEICAO, String DS_ELEICAO, Date DT_ELEICAO, int CD_PLEITO) {
        this.CD_ELEICAO = CD_ELEICAO;
        this.DS_ELEICAO = DS_ELEICAO;
        this.DT_ELEICAO = DT_ELEICAO;
        this.CD_PLEITO = CD_PLEITO;
    }

    
    
    
    
    
    public int getCD_ELEICAO() {
        return CD_ELEICAO;
    }

    public void setCD_ELEICAO(int CD_ELEICAO) {
        this.CD_ELEICAO = CD_ELEICAO;
    }

    public String getDS_ELEICAO() {
        return DS_ELEICAO;
    }

    public void setDS_ELEICAO(String DS_ELEICAO) {
        this.DS_ELEICAO = DS_ELEICAO;
    }

    public Date getDT_ELEICAO() {
        return DT_ELEICAO;
    }

    public void setDT_ELEICAO(Date DT_ELEICAO) {
        this.DT_ELEICAO = DT_ELEICAO;
    }

    public int getCD_PLEITO() {
        return CD_PLEITO;
    }

    public void setCD_PLEITO(int CD_PLEITO) {
        this.CD_PLEITO = CD_PLEITO;
    }
    
    
    
}

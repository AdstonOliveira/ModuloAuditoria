/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClientSide.Model.BU;

import DAO.DAOSecao;

/**
 *
 * @author adston
 */
public class Secao {
    private int SECAO;
    private int NR_ZONA;
    private int LOCAL_VOTACAO;
    private int URNA;

    public Secao() {
    }

    public Secao(int SECAO, int NR_ZONA, int local) {
        this.SECAO = SECAO;
        this.NR_ZONA = NR_ZONA;
        this.LOCAL_VOTACAO = local;
    }

    public int getURNA() {
        return URNA;
    }

    public void setURNA(int URNA) {
        this.URNA = URNA;
    }

    
    
    public int getLOCAL_VOTACAO() {
        return LOCAL_VOTACAO;
    }

    public void setLOCAL_VOTACAO(int LOCAL_VOTACAO) {
        this.LOCAL_VOTACAO = LOCAL_VOTACAO;
    }

    
    
    public int getSECAO() {
        return SECAO;
    }

    public void setSECAO(int SECAO) {
        this.SECAO = SECAO;
    }

    public int getNR_ZONA() {
        return NR_ZONA;
    }

    public void setNR_ZONA(int NR_ZONA) {
        this.NR_ZONA = NR_ZONA;
    }
    
    public boolean saveMe(){
        return DAOSecao.saveSecao(this);
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClientSide.Model.BU;

/**
 *
 * @author adston
 */
class Secao {
    private int SECAO;
    private int NR_ZONA;

    public Secao() {
    }

    public Secao(int SECAO, int NR_ZONA) {
        this.SECAO = SECAO;
        this.NR_ZONA = NR_ZONA;
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
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClientSide.Model.BU;

import DAO.DAOZona;

/**
 *
 * @author adston
 */
public class Zona {
    private int NR_ZONA;
    private int CD_MUNICIPIO;

    public Zona() {
    }

    public Zona(int NR_ZONA, int CD_MUNICIPIO) {
        this.NR_ZONA = NR_ZONA;
        this.CD_MUNICIPIO = CD_MUNICIPIO;
    }

    
    
    public int getNR_ZONA() {
        return NR_ZONA;
    }

    public void setNR_ZONA(int NR_ZONA) {
        this.NR_ZONA = NR_ZONA;
    }

    public int getCD_MUNICIPIO() {
        return CD_MUNICIPIO;
    }

    public void setCD_MUNICIPIO(int CD_MUNICIPIO) {
        this.CD_MUNICIPIO = CD_MUNICIPIO;
    }
    
    public boolean saveMe(){
        return DAOZona.saveZona(this);
    }
    
    
}

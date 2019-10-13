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
class Municipio {
    private int CD_MUNICIPIO;
    private String SG_UF;
    private String NM_MUNICIPIO;

    public Municipio() {
    }

    public Municipio(int CD_MUNICIPIO, String SG_UF, String NM_MUNICIPIO) {
        this.CD_MUNICIPIO = CD_MUNICIPIO;
        this.SG_UF = SG_UF;
        this.NM_MUNICIPIO = NM_MUNICIPIO;
    }

    
    
    
    public int getCD_MUNICIPIO() {
        return CD_MUNICIPIO;
    }

    public void setCD_MUNICIPIO(int CD_MUNICIPIO) {
        this.CD_MUNICIPIO = CD_MUNICIPIO;
    }

    public String getSG_UF() {
        return SG_UF;
    }

    public void setSG_UF(String SG_UF) {
        this.SG_UF = SG_UF;
    }

    public String getNM_MUNICIPIO() {
        return NM_MUNICIPIO;
    }

    public void setNM_MUNICIPIO(String NM_MUNICIPIO) {
        this.NM_MUNICIPIO = NM_MUNICIPIO;
    }
    
    
    
}

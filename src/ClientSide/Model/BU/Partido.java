package ClientSide.Model.BU;

import DAO.DAOPartido;

/**
 * @author adston
 */
public class Partido {
    
    private int NR_PARTIDO;
    private String SG_PARTIDO;
    private String NM_PARTIDO;

    public Partido(int NR_PARTIDO, String SG_PARTIDO, String NM_PARTIDO) {
        this.NR_PARTIDO = NR_PARTIDO;
        this.SG_PARTIDO = SG_PARTIDO;
        this.NM_PARTIDO = NM_PARTIDO;
    }
    
    public Partido(){}
    
    
    

    public int getNR_PARTIDO() {
        return NR_PARTIDO;
    }

    public void setNR_PARTIDO(int NR_PARTIDO) {
        this.NR_PARTIDO = NR_PARTIDO;
    }

    public String getSG_PARTIDO() {
        return SG_PARTIDO;
    }

    public void setSG_PARTIDO(String SG_PARTIDO) {
        this.SG_PARTIDO = SG_PARTIDO;
    }

    public String getNM_PARTIDO() {
        return NM_PARTIDO;
    }

    public void setNM_PARTIDO(String NM_PARTIDO) {
        this.NM_PARTIDO = NM_PARTIDO;
    }
    
    public boolean saveMe(){
        return DAOPartido.savePartido(this);
    }
    
    
    
    
}

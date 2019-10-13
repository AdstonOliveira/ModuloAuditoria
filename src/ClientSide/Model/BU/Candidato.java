package ClientSide.Model.BU;
/**
 * @author adston
 */
public class Candidato {
    private int NrVotavel;
    private String NmVotavel;
    private int QtVotos;
    
    private int CdCargoPergunta;
    private String DsCargoPergunta;
    
    private int CdTipoVotavel;
    private String DsTipoVotavel;
    
    private int NrPartido;
    private String SgPartido;
    private String NmPartido;
    
public Candidato(){
    
}

    public String getDsTipoVotavel() {
        return DsTipoVotavel;
    }

    public void setDsTipoVotavel(String DsTipoVotavel) {
        this.DsTipoVotavel = DsTipoVotavel;
    }

    public String getNmPartido() {
        return NmPartido;
    }

    public void setNmPartido(String NmPartido) {
        this.NmPartido = NmPartido;
    }
    public int getNrVotavel() {
        return NrVotavel;
    }

    public void setNrVotavel(int NrVotavel) {
        this.NrVotavel = NrVotavel;
    }

    public int getQtVotos() {
        return QtVotos;
    }

    public void setQtVotos(int QtVotos) {
        this.QtVotos = QtVotos;
    }

    public String getNmVotavel() {
        return NmVotavel;
    }

    public void setNmVotavel(String NmVotavel) {
        this.NmVotavel = NmVotavel;
    }

    public int getCdCargoPergunta() {
        return CdCargoPergunta;
    }

    public void setCdCargoPergunta(int CdCargoPergunta) {
        this.CdCargoPergunta = CdCargoPergunta;
    }

    public int getCdTipoVotavel() {
        return CdTipoVotavel;
    }

    public void setCdTipoVotavel(int CdTipoVotavel) {
        this.CdTipoVotavel = CdTipoVotavel;
    }

    public int getNrPartido() {
        return NrPartido;
    }

    public void setNrPartido(int NrPartido) {
        this.NrPartido = NrPartido;
    }

    
    public void setDsCargoPergunta(String value){
        
    }
    
    public String getDsCargoPergunta(){
        return "Cargo";
    }
    public String getSgPartido(){
        return "Sigla";
    }
    public void setSgPartido(String value){
        
    }
    
    
}

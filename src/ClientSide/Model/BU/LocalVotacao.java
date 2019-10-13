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
class LocalVotacao {
    private int NR_LOCAL_VOTACAO;
    private int QT_APTOS;
    private int QT_COMPARECIMENTO;
    private int QT_ABSTENCOES;
    private int QT_ELEITORES_BIOMETRIA_NH;
    private int SECAO;

    public LocalVotacao() {
    }

    public LocalVotacao(int NR_LOCAL_VOTACAO, int QT_APTOS, int QT_COMPARECIMENTO, int QT_ABSTENCOES, int QT_ELEITORES_BIOMETRIA_NH, int SECAO) {
        this.NR_LOCAL_VOTACAO = NR_LOCAL_VOTACAO;
        this.QT_APTOS = QT_APTOS;
        this.QT_COMPARECIMENTO = QT_COMPARECIMENTO;
        this.QT_ABSTENCOES = QT_ABSTENCOES;
        this.QT_ELEITORES_BIOMETRIA_NH = QT_ELEITORES_BIOMETRIA_NH;
        this.SECAO = SECAO;
    }

    
    
    
    
    
    public int getNR_LOCAL_VOTACAO() {
        return NR_LOCAL_VOTACAO;
    }

    public void setNR_LOCAL_VOTACAO(int NR_LOCAL_VOTACAO) {
        this.NR_LOCAL_VOTACAO = NR_LOCAL_VOTACAO;
    }

    public int getQT_APTOS() {
        return QT_APTOS;
    }

    public void setQT_APTOS(int QT_APTOS) {
        this.QT_APTOS = QT_APTOS;
    }

    public int getQT_COMPARECIMENTO() {
        return QT_COMPARECIMENTO;
    }

    public void setQT_COMPARECIMENTO(int QT_COMPARECIMENTO) {
        this.QT_COMPARECIMENTO = QT_COMPARECIMENTO;
    }

    public int getQT_ABSTENCOES() {
        return QT_ABSTENCOES;
    }

    public void setQT_ABSTENCOES(int QT_ABSTENCOES) {
        this.QT_ABSTENCOES = QT_ABSTENCOES;
    }

    public int getQT_ELEITORES_BIOMETRIA_NH() {
        return QT_ELEITORES_BIOMETRIA_NH;
    }

    public void setQT_ELEITORES_BIOMETRIA_NH(int QT_ELEITORES_BIOMETRIA_NH) {
        this.QT_ELEITORES_BIOMETRIA_NH = QT_ELEITORES_BIOMETRIA_NH;
    }

    public int getSECAO() {
        return SECAO;
    }

    public void setSECAO(int SECAO) {
        this.SECAO = SECAO;
    }
    
    
    
}

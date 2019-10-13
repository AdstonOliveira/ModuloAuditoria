package ClientSide.View.cliente;

import ClientSide.Model.BU.Candidato;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * @author adston
 */
public class VotosTableModel extends AbstractTableModel{
    private List<Candidato> candidatos;
    private String[] colunas = {"NR_VOTAVEL","NM_VOTAVEL","DS_CARGO_PERGUNTA","SG_PARTIDO", "QT_VOTOS"};
    
    public VotosTableModel(List<Candidato> dados){
        this.candidatos = dados;
    }
    
    public VotosTableModel(){
        this.candidatos=new ArrayList();
    }
    
    @Override
    public int getRowCount() {
        return this.candidatos.size();
    }

    @Override
    public int getColumnCount() {
        return this.colunas.length;
    }

    @Override
    public String getColumnName(int columnIndex){
        return colunas[columnIndex];
    }
    @Override
    public Class<?> getColumnClass(int columnIndex) {  
        /*private String[] colunas = {"NR_VOTAVEL","NM_VOTAVEL","DS_CARGO_PERGUNTA","SG_PARTIDO", "QT_VOTOS"};*/
        switch (columnIndex){
            case 0:
                return int.class;
            case 1:
                return String.class;
            case 2:
                return String.class;
            case 3:
                return String.class;
            default:
                return int.class;
        }
    }
    
    public void setValueAt(Candidato aValue, int rowIndex) {  
       Candidato candidato = candidatos.get(rowIndex);
       
        candidato.setNrVotavel(aValue.getNrVotavel());
        candidato.setNmVotavel(aValue.getNmVotavel());
        candidato.setDsCargoPergunta(aValue.getDsCargoPergunta());
        candidato.setSgPartido(aValue.getSgPartido());
        candidato.setQtVotos(aValue.getQtVotos());

        fireTableCellUpdated(rowIndex, 0);  
        fireTableCellUpdated(rowIndex, 1);  
        fireTableCellUpdated(rowIndex, 2);  
        fireTableCellUpdated(rowIndex, 3);  
    }
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {  
        Candidato candidato = candidatos.get(rowIndex);
     switch (columnIndex) {
         case 0:  
             candidato.setNrVotavel( Integer.valueOf(aValue.toString()) );
             break;
         case 1:  
             candidato.setNmVotavel( aValue.toString() );             
             break;
         case 2:  
             candidato.setDsCargoPergunta( (aValue.toString()) );             
             break;
         case 3:
             candidato.setSgPartido( (aValue.toString()) );
             break;
         case 4:
             candidato.setQtVotos( Integer.valueOf(aValue.toString()) );
         default:  
             System.err.println("Índice da coluna inválido");
     }  
        fireTableCellUpdated(rowIndex, columnIndex);  
     }
    
    @Override
    public Object getValueAt(int i, int Ci) {
        Candidato candidato = candidatos.get(i);
        switch (Ci){
            case 0:
                return candidato.getNrVotavel();
            case 1:
                return candidato.getNmVotavel();
            case 2: 
                return candidato.getDsCargoPergunta();
            case 3:
                return candidato.getSgPartido();
            case 4:
                return candidato.getQtVotos();
            default:
                System.err.println("Coluna nao identificada");
                return null;
        }
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {  
        return false;  
    }
    
    public Candidato getUrna(int i){
        return this.candidatos.get(i);
    }
    public void addCandidato(Candidato u){
        this.candidatos.add(u);
        int ultimoIndice = getRowCount() - 1;  
   
        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }
    
    public void addListaUrnas(List<Candidato> candidato){
        int tamanhoAntigo = this.getRowCount();
        this.candidatos.addAll(candidato);
        fireTableRowsInserted(tamanhoAntigo, getRowCount() - 1);
    }
    public void limpar() {  
        this.candidatos.clear();    
        fireTableDataChanged();  
    }  
   
    public boolean isEmpty() {  
        return this.candidatos.isEmpty();  
    }  
}

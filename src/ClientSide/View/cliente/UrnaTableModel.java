package ClientSide.View.cliente;

import ClientSide.Model.BU.Urna;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author adston
 */
public class UrnaTableModel extends AbstractTableModel{
    private List<Urna> urnas;
    private String[] colunas = {"URNA", "DT_ABERTURA", "DT_ENCERRAMENTO", "COD_URNA_EFETIVADA"};
    
    public UrnaTableModel(List<Urna> dados){
        this.urnas = dados;
    }
    
    public UrnaTableModel(){
        this.urnas=new ArrayList();
    }
    
    @Override
    public int getRowCount() {
        return this.urnas.size();
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
        switch (columnIndex){
            case 0:
                return int.class;
            case 1:
                return Date.class;
            case 2:
                return Date.class;
            case 3:
                return int.class;
            default:
                return String.class;
        }
    }
    
    public void setValueAt(Urna aValue, int rowIndex) {  
       Urna urna = urnas.get(rowIndex);
       
        urna.setUrna(aValue.getUrna());
        urna.setNR_URNA_EFETIVADA(aValue.getNR_URNA_EFETIVADA());
        urna.setDT_ABERTURA(aValue.getDT_ABERTURA());
        urna.setDT_ENCERRAMENTO(aValue.getDT_ENCERRAMENTO());
   
        fireTableCellUpdated(rowIndex, 0);  
        fireTableCellUpdated(rowIndex, 1);  
        fireTableCellUpdated(rowIndex, 2);  
        fireTableCellUpdated(rowIndex, 3);  
   
    }
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {  
        Urna urna = urnas.get(rowIndex);
         /*{"URNA", "DT_ABERTURA", "DT_ENCERRAMENTO", "COD_URNA_EFETIVADA"};*/
   
     switch (columnIndex) {
         case 0:  
             urna.setUrna( Integer.valueOf(aValue.toString()) );
             break;
         case 1:  
             urna.setDT_ABERTURA( Timestamp.valueOf(aValue.toString()) );             
             break;
         case 2:  
             urna.setDT_ENCERRAMENTO( Timestamp.valueOf(aValue.toString()) );             
             break;
         case 3:
             urna.setNR_URNA_EFETIVADA( Integer.valueOf(aValue.toString()) );
             break;
         default:  
             System.err.println("Índice da coluna inválido");
     }  
        fireTableCellUpdated(rowIndex, columnIndex);  
     }
    
    @Override
    public Object getValueAt(int i, int Ci) {
        Urna urna = urnas.get(i);
        switch (Ci){
            case 0:
                return urna.getUrna();
            case 1:
                return urna.getDT_ABERTURA();
            case 2: 
                return urna.getDT_ENCERRAMENTO();
            case 3:
                return urna.getNR_URNA_EFETIVADA();
            default:
                System.err.println("Coluna nao identificada");
                return null;
        }
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {  
        return false;  
    }
    
    public Urna getUrna(int i){
        return this.urnas.get(i);
    }
    public void addUrna(Urna u){
        this.urnas.add(u);
        int ultimoIndice = getRowCount() - 1;  
   
        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }
    
    public void addListaUrnas(List<Urna> urnas){
        int tamanhoAntigo = this.getRowCount();
        this.urnas.addAll(urnas);
        fireTableRowsInserted(tamanhoAntigo, getRowCount() - 1);
    }
    public void limpar() {  
        this.urnas.clear();    
        fireTableDataChanged();  
    }  
   
    public boolean isEmpty() {  
        return this.urnas.isEmpty();  
    }  
    
    
    
    
    
}

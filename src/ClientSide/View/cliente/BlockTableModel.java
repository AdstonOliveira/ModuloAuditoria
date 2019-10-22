package ClientSide.View.cliente;

import ServerSide.Model.Block;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * @author adston
 */
public class BlockTableModel extends AbstractTableModel{
    private List<Block> blocos;
    private String[] colunas = { "B_TIMESTAMP", "BLOCK_HASH", "PREVIOUS_HASH", "AMOUNT_TRANSACTIONS",
        "NOUCE","DIFFICULTY","ID" };
    
    
    public BlockTableModel(List<Block> dados){
        this.blocos = dados;
            
    }
    
    public BlockTableModel(){
        this.blocos=new ArrayList();
    }
    
    @Override
    public int getRowCount() {
        return this.blocos.size();
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
        /*{ "B_TIMESTAMP", "BLOCK_HASH", "PREVIOUS_HASH", "AMOUNT_TRANSACTIONS",
        "NOUCE","DIFFICULTY","ID" }*/
        switch (columnIndex){
            case 0:
                return Timestamp.class;
            case 1:
                return String.class;
            case 2:
                return String.class;
            case 3:
                return int.class;
            
            default:
                return int.class;
        }
    }
    
    public void setValueAt(Block aValue, int rowIndex) {  
       Block bloco = blocos.get(rowIndex);
       /*{ "B_TIMESTAMP", "BLOCK_HASH", "PREVIOUS_HASH", "AMOUNT_TRANSACTIONS",
        "NOUCE","DIFFICULTY","ID" }*/
        bloco.setTimeStamp(aValue.getTimeStamp());
        bloco.setHash(aValue.getHash());
        bloco.setPreviousHash(aValue.getPreviousHash());
        bloco.setAmount_transactions(aValue.getAmount_transactions());
        bloco.setNonce(aValue.getNonce());
        bloco.setDifficulty(aValue.getDifficulty());
        bloco.setId(aValue.getId());
        
        fireTableCellUpdated(rowIndex, 0);  
        fireTableCellUpdated(rowIndex, 1);  
        fireTableCellUpdated(rowIndex, 2);  
        fireTableCellUpdated(rowIndex, 3);  
        fireTableCellUpdated(rowIndex, 4);  
        fireTableCellUpdated(rowIndex, 5);  
        fireTableCellUpdated(rowIndex, 6);  
    }
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {  
/*{ "B_TIMESTAMP", "BLOCK_HASH", "PREVIOUS_HASH", "AMOUNT_TRANSACTIONS",
        "NOUCE","DIFFICULTY","ID" }*/
        Block bloco = blocos.get(rowIndex);
        
     switch (columnIndex) {
         case 0:  
             bloco.setTimeStamp(Timestamp.valueOf(aValue.toString()));
             break;
         case 1:  
             bloco.setHash(aValue.toString());
             break;
         case 2:  
             bloco.setPreviousHash(aValue.toString());
             break;
         case 3:
             bloco.setAmount_transactions(Integer.valueOf(aValue.toString()));
             break;
         case 4:
             bloco.setNonce(Integer.valueOf(aValue.toString()));
             break;
         case 5:
             bloco.setDifficulty(Integer.valueOf(aValue.toString()));
             break;
         case 6:
             bloco.setId(Integer.valueOf(aValue.toString()));
             break;
         default:  
             System.err.println("Índice da coluna inválido");
             break;
     }  
        fireTableCellUpdated(rowIndex, columnIndex);  
     }
    
    @Override
    public Object getValueAt(int i, int Ci) {
        Block bloco = blocos.get(i);
        /*{ "B_TIMESTAMP", "BLOCK_HASH", "PREVIOUS_HASH", "AMOUNT_TRANSACTIONS",
        "NOUCE","DIFFICULTY","ID" }*/

        switch (Ci){
            case 0:
                return bloco.getTimeStamp();
            case 1:
                return bloco.getHash();
            case 2: 
                return bloco.getPreviousHash();
            case 3:
                return bloco.getAmount_transactions();
            case 4:
                return bloco.getNonce();
            case 5:
                return bloco.getDifficulty();
            case 6:
                return bloco.getId();
            default:
                System.err.println("Coluna nao identificada");
                return null;
        }
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {  
        return false;  
    }
    
    public Block getBlock(int i){
        return this.blocos.get(i);
    }
    public void addBlock(Block u){
        this.blocos.add(u);
        int ultimoIndice = getRowCount() - 1;  
   
        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }
    
    public void addListaBlocos(List<Block> bloco){
        int tamanhoAntigo = this.getRowCount();
        this.blocos.addAll(bloco);
        fireTableRowsInserted(tamanhoAntigo, getRowCount() - 1);
    }
    public void limpar() {  
        this.blocos.clear();    
        fireTableDataChanged();  
    }  
   
    public boolean isEmpty() {  
        return this.blocos.isEmpty();  
    }  
}

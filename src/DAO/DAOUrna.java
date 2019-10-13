package DAO;

import ClientSide.Model.BU.Urna;
import static DAO.DAO.conn;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author adston
 */
/*
private int urna;
    private int NR_URNA_EFETIVADA;
    private int CD_CARGA_1_URNA_EFEETIVADA;
    private int CD_CARGA_2_URNA_EFEETIVADA;
    private int CD_FLASHCARD_URNA_EFETIVADA;
    private Date DT_ABERTURA;
    private Date DT_ENCERRAMENTO;
*/
public class DAOUrna extends DAO{
    
    public static boolean SaveUrna(Urna urna){
        conn = DAO.getConnection();
        
        PreparedStatement stmt = null;
        
        try {
            stmt = conn.prepareStatement("insert into adm.urna("
                    + "URNA, NR_URNA_EFETIVADA, CD_CARGA_1_URNA_EFETIVADA, CD_CARGA_2_URNA_EFETIVADA, CD_FLASCARD_URNA_EFETIVADA,"
                    + "DT_ABERTURA, DT_ENCERRAMENTO) "
                    + "values (?, ?, ?, ?, ?, '"+urna.getDT_ABERTURA()+"','"+urna.getDT_ENCERRAMENTO()+"')");
            stmt.setInt(1, urna.getUrna());
            stmt.setInt(2, urna.getNR_URNA_EFETIVADA() );
            stmt.setInt(3, urna.getCD_CARGA_1_URNA_EFEETIVADA() );
            stmt.setInt(4, urna.getCD_CARGA_2_URNA_EFEETIVADA() );
            stmt.setInt(5, urna.getCD_FLASHCARD_URNA_EFETIVADA() );
//            stmt.setTimestamp(4, urna.getDT_ABERTURA() );
//            stmt.setTimestamp(5, urna.getDT_ENCERRAMENTO() );
            
            stmt.execute();
            DAO.closeConnection(conn, stmt);
            System.out.println("Transacao salva");
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            DAO.closeConnection(conn, stmt);
        }
        return false;
        }
    
    public static List<Urna> getUrnas() throws SQLException{
        conn = DAO.getConnection();
        PreparedStatement stmt = conn.prepareStatement("select * from urna");
        ResultSet rs = stmt.executeQuery();
        List<Urna> lista = new ArrayList();
        
        while( rs.next() ){
            Urna u = new Urna();
            u.setUrna(rs.getInt("URNA"));
            u.setNR_URNA_EFETIVADA(rs.getInt("NR_URNA_EFETIVADA"));
            u.setCD_CARGA_1_URNA_EFEETIVADA(rs.getInt("CD_CARGA_1_URNA_EFETIVADA"));
            u.setCD_CARGA_2_URNA_EFEETIVADA(rs.getInt("CD_CARGA_2_URNA_EFETIVADA"));
            u.setCD_FLASHCARD_URNA_EFETIVADA(rs.getInt("CD_FLASCARD_URNA_EFETIVADA"));
            u.setDT_ABERTURA(rs.getTimestamp("DT_ABERTURA"));
            u.setDT_ENCERRAMENTO(rs.getTimestamp("DT_ENCERRAMENTO"));
            
            lista.add(u);
        }
    
        return lista;
    }
    
}

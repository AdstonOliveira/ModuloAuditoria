package DAO;

import ClientSide.Model.BU.Urna;
import static DAO.DAO.conn;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    
    public static boolean checkUrna(Urna urna){
        
        conn = DAO.getConnection();
        PreparedStatement stmt;
        try {
            stmt = conn.prepareStatement("select * from urna where urna = "+urna.getUrna()+"");
            ResultSet rs = stmt.executeQuery();
            
           if( !rs.next() )
               return false;

        } catch (SQLException ex) {
            Logger.getLogger(DAOUrna.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        DAO.closeConnection(conn,stmt);
        
        return true;
    }
    
    
    public static boolean SaveUrna(Urna urna) throws SQLException{
        if( !checkUrna(urna) ){ 
            conn = DAO.getConnection();
        
            try {
                conn.setAutoCommit(false);
                PreparedStatement stmt = conn.prepareStatement("insert into urna("
                        + "URNA, CD_CARGA_1_URNA_EFETIVADA, CD_CARGA_2_URNA_EFETIVADA, CD_FLASCARD_URNA_EFETIVADA,"
                        + "DT_ABERTURA, DT_ENCERRAMENTO) "
                        + "values (?, ?, ?, ?, ?, ?)");
                
                stmt.setInt( 1, urna.getUrna() );
                stmt.setString(2, urna.getCD_CARGA_1_URNA_EFEETIVADA() );
                stmt.setString(3, urna.getCD_CARGA_2_URNA_EFEETIVADA() );
                stmt.setString(4, urna.getCD_FLASHCARD_URNA_EFETIVADA() );
                stmt.setTimestamp(5, urna.getDT_ABERTURA() );
                stmt.setTimestamp(6, urna.getDT_ENCERRAMENTO() );

                stmt.execute();
                conn.commit();
                conn.setAutoCommit(true);
                DAO.closeConnection(conn, stmt);
                System.out.println("Urna salva");
            return true;
            
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Erro ao salvar urna");
                conn.rollback();
            return false;
            }
            
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
            u.setCD_CARGA_1_URNA_EFEETIVADA(rs.getString("CD_CARGA_1_URNA_EFETIVADA"));
            u.setCD_CARGA_2_URNA_EFEETIVADA(rs.getString("CD_CARGA_2_URNA_EFETIVADA"));
            u.setCD_FLASHCARD_URNA_EFETIVADA(rs.getString("CD_FLASCARD_URNA_EFETIVADA"));
            u.setDT_ABERTURA(rs.getTimestamp("DT_ABERTURA"));
            u.setDT_ENCERRAMENTO(rs.getTimestamp("DT_ENCERRAMENTO"));
            
            lista.add(u);
        }
        DAO.closeConnection(conn, stmt, rs);
    
        return lista;
    }

    public static boolean deleteAll(){
        return DAO.deleteAll("urna");
    }
    
}

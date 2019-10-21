package DAO;

import ClientSide.Model.BU.Partido;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author adston
 */
public class DAOPartido extends DAO{
    public static boolean deleteAll(){
        return DAO.deleteAll("partidos");
    }
    public static boolean checkPartido(int id){
        conn = DAO.getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement("select * from partidos where NR_PARTIDO = "+id);
            ResultSet rs = stmt.executeQuery();
            
            boolean exist = rs.next();
            DAO.closeConnection(conn, stmt, rs);
            
            return exist;
        } catch (SQLException ex) {
            Logger.getLogger(DAOPartido.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    public static boolean savePartido(Partido p){
        if( !checkPartido(p.getNR_PARTIDO()) ){
            conn = DAO.getConnection();
            try {
            conn.setAutoCommit(false);
                PreparedStatement stmt = conn.prepareStatement("insert into partidos(NR_PARTIDO, SG_PARTIDO, NM_PARTIDO) values (?,?,?)");
                stmt.setInt(1, p.getNR_PARTIDO());
                stmt.setString(2, p.getSG_PARTIDO());
                stmt.setString(3, p.getNM_PARTIDO());
                
            stmt.execute();
            conn.commit();
            DAO.closeConnection(conn, stmt);
            return true;
            } catch (SQLException ex) {
                Logger.getLogger(DAOPartido.class.getName()).log(Level.SEVERE, null, ex);
                try {
                    conn.rollback();
                    return false;
                } catch (SQLException ex1) {
                    Logger.getLogger(DAOPartido.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
        }
        return false;
    }
    
    
}

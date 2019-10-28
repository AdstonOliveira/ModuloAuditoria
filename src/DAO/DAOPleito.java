package DAO;

import ClientSide.Model.BU.Pleito;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author adston
 */
public class DAOPleito extends DAO{
    
    
    public static boolean checkExist(Pleito pleito){
        conn = DAO.getConnection();
        boolean exist = false;
        
        String select = "select * from pleito where cd_pleito = ?";
        
        try {
            PreparedStatement stmt = conn.prepareStatement(select);
            stmt.setInt(1, pleito.getCD_PLEITO());
            ResultSet rs = stmt.executeQuery();
            
            if( !rs.next() )
                exist = false;
            else
                exist = true;
                
            DAO.closeConnection(conn, stmt, rs);
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOPleito.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return exist;
    }
    
    public static boolean savePleito(Pleito pleito){
        if( !checkExist(pleito) ){
            
            System.out.println("Pleito nao existe, salvando");
            conn = DAO.getConnection();
            String insert = "insert into pleito (cd_pleito, dt_pleito, nr_turno) values (?,?,?)";
            
                try {
                    conn.setAutoCommit(false);
                    PreparedStatement stmt = conn.prepareStatement(insert);
                    
                    stmt.setInt(1, pleito.getCD_PLEITO());
                    stmt.setDate(2, new Date(pleito.getDT_PLEITO().getTime()));
                    stmt.setInt(3, pleito.getNR_TURNO());

                    stmt.execute();
                    conn.commit();
                    conn.setAutoCommit(true);
                    DAO.closeConnection(conn, stmt);
                    return true;

                } catch (SQLException ex) {
                    Logger.getLogger(DAOPleito.class.getName()).log(Level.SEVERE, null, ex);
                    try {
                        conn.rollback();
                    } catch (SQLException ex1) {
                        Logger.getLogger(DAOPleito.class.getName()).log(Level.SEVERE, null, ex1);
                    }
                }
        }
        System.out.println("Pleito ja existe");
        return false;
    }
    
    
    
    
    
    
    
}

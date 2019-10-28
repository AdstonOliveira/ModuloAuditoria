package DAO;

import ClientSide.Model.BU.BU;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author adston
 */
public class DAOBu extends DAO{
    public static boolean checkExist(BU bu){
        conn = DAO.getConnection();
        String search = "select * from boletim_urna where urna = ?";
        boolean exist = false;
        try {
            PreparedStatement stmt = conn.prepareStatement(search);
            stmt.setInt( 1, bu.getUrna() );
            
            ResultSet rs = stmt.executeQuery();
            
            if(!rs.next())
                return false;
            
            DAO.closeConnection(conn, stmt, rs);

        } catch (SQLException ex) {
            Logger.getLogger(DAOBu.class.getName()).log(Level.SEVERE, null, ex);
        }
        return exist;
    }
    
    public static boolean saveBU(BU bu){
        if( !checkExist(bu) ){
            System.out.println("B-Urna Nao existe, salvando ");
            conn = DAO.getConnection();
            String insert = "insert into Boletim_urna(urna, dt_geracao, hh_geracao, pleito ) values (?,?,?,?)";
                try {
                    PreparedStatement stmt = conn.prepareStatement(insert);
                    stmt.setInt(1, bu.getUrna() );
                    stmt.setDate(2, new Date( bu.getDT_GERACAO().getTime()) );
                    stmt.setTime(3, bu.getHH_GERACAO() );
                    stmt.setInt(4, bu.getPLEITO() );
                    stmt.executeUpdate();
                    DAO.closeConnection(conn,stmt);

                    return true;
                } catch (SQLException ex) {
                    Logger.getLogger(DAOBu.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
        System.out.println("BU ja existe");
        return false;
    }
    
    
    
}

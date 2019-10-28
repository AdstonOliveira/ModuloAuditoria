package DAO;

import ClientSide.Model.BU.Eleicao;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author adston
 */
public class DAOEleicao extends DAO{
    
    
    public static boolean saveEleicao(Eleicao eleicao){
        
        if( !DAO.checkExist("eleicao", "cd_eleicao", eleicao.getCD_ELEICAO()) ){
            conn = DAO.getConnection();
            String insert = "insert into eleicao(cd_eleicao, ds_eleicao, dt_eleicao, cd_pleito) values(?,?,?,?)";
            try {
                conn.setAutoCommit(false);
                PreparedStatement stmt = conn.prepareStatement(insert);
                stmt.setInt(1, eleicao.getCD_ELEICAO());
                stmt.setString(2, eleicao.getDS_ELEICAO());
                stmt.setDate(3, new Date(eleicao.getDT_ELEICAO().getTime()) );
                stmt.setInt(4, eleicao.getCD_PLEITO());
                
                stmt.execute();
                conn.commit();
                conn.setAutoCommit(true);
                
                DAO.closeConnection(conn, stmt);
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(DAOEleicao.class.getName()).log(Level.SEVERE, null, ex);
                try {
                    conn.rollback();
                    conn.setAutoCommit(true);
                    return false;
                } catch (SQLException ex1) {
                    Logger.getLogger(DAOEleicao.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
        }
        
        System.out.println("Esta eleicao ja esta salva");
        return false;
    }
    
    
    
    
    
}

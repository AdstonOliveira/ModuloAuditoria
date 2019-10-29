/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import ClientSide.Model.BU.Secao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author adston
 */
public class DAOSecao extends DAO{
    
    public static boolean checkExist(Secao secao){
        conn = DAO.getConnection();
        String select = "select * from secao where secao = ? and local_votacao_id = ? and zona = ?";
        boolean exist = true;
        try {
            PreparedStatement stmt = conn.prepareStatement(select);
            stmt.setInt(1, secao.getSECAO());
            stmt.setInt(2, secao.getLOCAL_VOTACAO());
            stmt.setInt(3, secao.getNR_ZONA());
            
            ResultSet rs = stmt.executeQuery();
            if(!rs.next())
                exist = false;
            
            DAO.closeConnection(conn, stmt, rs);
        } catch (SQLException ex) {
            Logger.getLogger(DAOSecao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return exist;
    }
    
    public static boolean saveSecao(Secao secao){
        if(!checkExist(secao)){
            conn = DAO.getConnection();
            String insert = "insert into secao(secao, local_votacao_id, zona, urna) values(?,?,?,?)";
            try {
                conn.setAutoCommit(false);
                
                PreparedStatement stmt = conn.prepareStatement(insert);
                stmt.setInt(1, secao.getSECAO());
                stmt.setInt(2, secao.getLOCAL_VOTACAO());
                stmt.setInt(3, secao.getNR_ZONA());
                stmt.setInt(4, secao.getURNA());
                
                stmt.execute();
                conn.commit();
                conn.setAutoCommit(true);
                DAO.closeConnection(conn, stmt);
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(DAOSecao.class.getName()).log(Level.SEVERE, null, ex);
                try {
                    conn.rollback();
                    conn.setAutoCommit(true);
                    DAO.closeConnection(conn);
                    return false;
                } catch (SQLException ex1) {
                    Logger.getLogger(DAOSecao.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
        }
        System.out.println("Secao ja esta salva");
        return false;
    }
    
}

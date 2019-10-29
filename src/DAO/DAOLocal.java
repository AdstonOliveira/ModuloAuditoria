/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import ClientSide.Model.BU.LocalVotacao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author adston
 */
public class DAOLocal extends DAO{
    
    public static boolean checkExist(LocalVotacao local){
        conn = DAO.getConnection();
        String select = "select * from local_votacao where nr_local_votacao = "+ local.getNR_LOCAL_VOTACAO()
                +" and zona = "+local.getZONA();
        boolean exist = true;
        try {
            PreparedStatement stmt = conn.prepareStatement(select);
            
            ResultSet rs = stmt.executeQuery();
            
            if( !rs.next() ){
                exist = false;
                System.out.println("Local Não Existe");
            }
            
            DAO.closeConnection(conn, stmt, rs);
        } catch (SQLException ex) {
            Logger.getLogger(DAOLocal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return exist;
    }
    
    
    public static boolean saveLocal(LocalVotacao local){
        if(!checkExist(local)){
           conn = DAO.getConnection();
           String insert = "Insert into local_votacao(nr_local_votacao, qt_aptos, qt_comparecimento, qt_abstencoes, "
                   + "qt_eleitores_biometria_nh, zona) values(?,?,?,?,?,?)";
           
            try {
                conn.setAutoCommit(false);
                
                PreparedStatement stmt = conn.prepareStatement(insert);
                
                stmt.setInt(1, local.getNR_LOCAL_VOTACAO());
                stmt.setInt(2, local.getQT_APTOS());
                stmt.setInt(3, local.getQT_COMPARECIMENTO());
                stmt.setInt(4, local.getQT_ABSTENCOES());
                stmt.setInt(5, local.getQT_ELEITORES_BIOMETRIA_NH());
                stmt.setInt(6, local.getZONA());
                
                stmt.execute();
                conn.commit();
                
                DAO.closeConnection(conn, stmt);
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(DAOLocal.class.getName()).log(Level.SEVERE, null, ex);
               try {
                   conn.rollback();
                   conn.setAutoCommit(true);
                   DAO.closeConnection(conn);
                   return false;
               } catch (SQLException ex1) {
                   Logger.getLogger(DAOLocal.class.getName()).log(Level.SEVERE, null, ex1);
               }
            }
        }
        System.out.println("Local ja existe");
        return false;
        
        
        
    }
  
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import ClientSide.Model.BU.Municipio;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author adston
 */
public class DAOMunicipio extends DAO{
    
    public static boolean saveMunicipio( Municipio mun ){
        
        if(!DAO.checkExist("municipios", "cd_municipio", mun.getCD_MUNICIPIO())){
            conn = DAO.getConnection();
            String insert = "insert into municipios(cd_municipio, sg_uf, nm_municipio) values(?,?,?)";
            try {
                conn.setAutoCommit(false);
                PreparedStatement stmt = conn.prepareStatement(insert);
                stmt.setInt(1, mun.getCD_MUNICIPIO());
                stmt.setString(2, mun.getSG_UF());
                stmt.setString(3, mun.getNM_MUNICIPIO());
                
                stmt.execute();
                conn.commit();
                
                DAO.closeConnection(conn, stmt);
                return true;
                
            } catch (SQLException ex) {
                Logger.getLogger(DAOMunicipio.class.getName()).log(Level.SEVERE, null, ex);
                try {
                    conn.rollback();
                    DAO.closeConnection(conn);
                    return false;
                } catch (SQLException ex1) {
                    Logger.getLogger(DAOMunicipio.class.getName()).log(Level.SEVERE, null, ex1);
                }
                return false;
            }
        }
        System.out.println("Municipio ja existe");
        return false;
    }
    
    
    
}

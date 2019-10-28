/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import ClientSide.Model.BU.Zona;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author adston
 */
public class DAOZona extends DAO{
    
    public static boolean saveZona(Zona zona){
        
        if(!DAO.checkExist("zona", "nr_zona", zona.getNR_ZONA())){
            conn = DAO.getConnection();
            String insert = "insert into zona(nr_zona, cd_municipio) values (?,?)";
            try {
                conn.setAutoCommit(false);
                
                PreparedStatement stmt = conn.prepareStatement(insert);
                stmt.setInt(1, zona.getNR_ZONA());
                stmt.setInt(2, zona.getCD_MUNICIPIO());
                stmt.execute();
                
                conn.commit();
                DAO.closeConnection(conn, stmt);
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(DAOZona.class.getName()).log(Level.SEVERE, null, ex);
                try {
                    conn.rollback();
                    conn.setAutoCommit(true);
                    DAO.closeConnection(conn);
                } catch (SQLException ex1) {
                    Logger.getLogger(DAOZona.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
        }
        System.out.println("Zona ja cadastrada");
        return false;
        
        
        
        
    }
    
    
    
    
    
}

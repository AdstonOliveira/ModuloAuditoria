package DAO;

import ClientSide.Model.BU.Cargo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author adston
 */
public class DAOCargo extends DAO{
    
    public static boolean checkCargo(int id){
        conn = DAO.getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement("Select * from cargos where CD_CARGO_PERGUNTA = "+id);
            ResultSet rs = stmt.executeQuery();
            
            boolean exist = rs.next();
            DAO.closeConnection(conn,stmt,rs);
            
            return exist;
        } catch (SQLException ex) {
            Logger.getLogger(DAOCargo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public static boolean saveCargo(Cargo c){
        if( !checkCargo( c.getCd_cargo_pergunta() ) ){
            conn = DAO.getConnection();

            try {
                conn.setAutoCommit(false);
                    PreparedStatement stmt = conn.prepareStatement("insert into cargos(CD_CARGO_PERGUNTA, DS_CARGO_PERGUNTA) values (?,?)");
                    stmt.setInt(1, c.getCd_cargo_pergunta());
                    stmt.setString(2, c.getDs_cargo_pergunta());

                    stmt.execute();
                    conn.commit();
                    System.out.println("Cargo salvo");

                    DAO.closeConnection(conn, stmt);
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(DAOCargo.class.getName()).log(Level.SEVERE, null, ex);
                try {
                    conn.rollback();
                    System.out.println("RollBack cargo");
                } catch (SQLException ex1) {
                    Logger.getLogger(DAOCargo.class.getName()).log(Level.SEVERE, null, ex1);
                    System.out.println("Falha ao realizar rollback");
                }
                return false;
            }
        }
        return false;
    }
    
    
    public static Cargo getCargo(int id){
        conn = DAO.getConnection();
        Cargo c = new Cargo();
        PreparedStatement stmt;
        try {
            stmt = conn.prepareStatement("select * from cargos where cd_cargo_pergunta = "+id);
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                c.setCd_cargo_pergunta(rs.getInt("CD_CARGO_PERGUNTA"));
                c.setDs_cargo_pergunta(rs.getString("DS_CARGO_PERGUNTA"));
            }
            
            DAO.closeConnection(conn, stmt,rs);
        } catch (SQLException ex) {
            Logger.getLogger(DAOCargo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return c;
    }
    
    public static Cargo getCargo(String descricao){
        conn = DAO.getConnection();
        Cargo c = new Cargo();
        
        PreparedStatement stmt;
        
        try {
            stmt = conn.prepareStatement("select * from cargos where ds_cargo_pergunta like '"+descricao+"'");
            
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                c.setCd_cargo_pergunta(rs.getInt("CD_CARGO_PERGUNTA"));
                c.setDs_cargo_pergunta(rs.getString("DS_CARGO_PERGUNTA"));
            }
            
            DAO.closeConnection(conn, stmt,rs);
        } catch (SQLException ex) {
            Logger.getLogger(DAOCargo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return c;
    }
    
    
}

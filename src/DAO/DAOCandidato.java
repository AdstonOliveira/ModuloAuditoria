package DAO;

import ClientSide.Model.BU.Candidato;
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
public class DAOCandidato extends DAO{
private String[] colunas = {"NR_VOTAVEL","NM_VOTAVEL","DS_CARGO_PERGUNTA","SG_PARTIDO", "QT_VOTOS"};    

public static boolean saveCandidato(Candidato candidato){
    conn = DAO.getConnection();
    PreparedStatement stmt;
    
    if( !checkCandidato(candidato) ){
        try {
            conn.setAutoCommit(false);
            
            stmt = conn.prepareStatement("insert into candidato"
                    + "(NR_VOTAVEL, NM_VOTAVEL, QT_VOTOS, CD_CARGO_PERGUNTA, CD_TIPO_VOTAVEL, NR_PARTIDO)"
                    + " values(?,?,?,?,?,?)");
            
            stmt.setInt(1, candidato.getNrVotavel());
            stmt.setString(2, candidato.getNmVotavel());
            stmt.setInt(3, candidato.getQtVotos() );
            stmt.setInt(4, candidato.getCdCargoPergunta());
            stmt.setInt(5, candidato.getCdTipoVotavel());
            stmt.setInt(6, candidato.getNrPartido() );
            
            stmt.execute();
            
            conn.commit();

            conn.setAutoCommit(true);
            DAO.closeConnection(conn, stmt);
         
         return true;
        } catch (SQLException ex) {
            Logger.getLogger(DAOCandidato.class.getName()).log(Level.SEVERE, null, ex);
            try {
                conn.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(DAOCandidato.class.getName()).log(Level.SEVERE, null, ex1);
                System.out.println("Rollback candidato");
            }
         return false;
        }
    }else{
        System.out.println("Candidato existe, fazendo update dos votos");
        DAO.closeConnection(conn);
        return updateVotosCandidato(candidato);
    }
    
}
public static boolean updateVotosCandidato(Candidato c){
    conn = DAO.getConnection();
    PreparedStatement stmt;
    
    try {
        conn.setAutoCommit(false);
                
        String update = "update candidato set qt_votos = (select qt_votos from candidato where nr_votavel = ?"
                + " and CD_CARGO_PERGUNTA = ? and CD_TIPO_VOTAVEL = ? ) + ? "
                + "where NR_VOTAVEL = ? and CD_CARGO_PERGUNTA = ? and CD_TIPO_VOTAVEL = ? ";
        
        stmt = conn.prepareStatement(update);
                
        stmt.setInt( 1, c.getNrVotavel() );
        stmt.setInt(2, c.getCdCargoPergunta() );
        stmt.setInt(3, c.getCdTipoVotavel() );
        stmt.setInt(4, c.getQtVotos() );
        
        stmt.setInt(5, c.getNrVotavel() );
        stmt.setInt(6, c.getCdCargoPergunta() );
        stmt.setInt(7, c.getCdTipoVotavel() );
        
        
        stmt.executeUpdate();
        conn.commit();
        
        DAO.closeConnection(conn, stmt);
        
        System.out.println("Votos atualizados");
        return true;
    } catch (SQLException ex) {
        Logger.getLogger(DAOCandidato.class.getName()).log(Level.SEVERE, null, ex);
        try {
            conn.rollback();
        } catch (SQLException ex1) {
            Logger.getLogger(DAOCandidato.class.getName()).log(Level.SEVERE, null, ex1);
        }
        return false;
    }
}

public static boolean checkCandidato( Candidato c ){
    
    conn = DAO.getConnection();
    PreparedStatement stmt;
    try {
        stmt = conn.prepareStatement("select * from candidato where nr_votavel = "+c.getNrVotavel() 
                +" and CD_TIPO_VOTAVEL = " + c.getCdTipoVotavel() 
                    + " and CD_CARGO_PERGUNTA = " + c.getCdCargoPergunta());
        
        ResultSet rs = stmt.executeQuery();
        
        if( !rs.next() )
            return false;
        
    } catch (SQLException ex) {
        Logger.getLogger(DAOCandidato.class.getName()).log(Level.SEVERE, null, ex);
        return false;
    }
    
    DAO.closeConnection(conn, stmt);
    return true;
}

public static List<Candidato> getVotosTableModel(){
    conn = DAO.getConnection();
    PreparedStatement stmt;
    List<Candidato> candidatos = new ArrayList();
    
    try { 

        stmt = conn.prepareStatement("select * from tablecandidato order by DS_CARGO_PERGUNTA");
        ResultSet rs = stmt.executeQuery();
        
        while( rs.next() ){
            Candidato c = new Candidato();
  /*    private String[] colunas = {"NR_VOTAVEL","NM_VOTAVEL","DS_CARGO_PERGUNTA","SG_PARTIDO", "QT_VOTOS"};
*/
            c.setNrVotavel(rs.getInt("NR_VOTAVEL"));
            c.setNmVotavel(rs.getString("NM_VOTAVEL"));
            c.setDsCargoPergunta(rs.getString("DS_CARGO_PERGUNTA"));
            c.setSgPartido(rs.getString("SG_PARTIDO"));
            c.setQtVotos(rs.getInt("QT_VOTOS"));
            
            candidatos.add(c);
        }
        
    } catch (SQLException ex) {
        Logger.getLogger(DAOCandidato.class.getName()).log(Level.SEVERE, null, ex);
    }
    return candidatos;
}

public static boolean deleteAll(){
    return DAO.deleteAll("candidato");
}

}

package DAO;

import ClientSide.Model.BU.Candidato;
import ClientSide.Model.BU.Cargo;
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
    if(!checkCandidato(candidato)){
        try {
            stmt = conn.prepareStatement("insert into candidato values(?,?,?,?,?,?)");
            stmt.setInt(1, candidato.getNrVotavel());
            stmt.setString(2, candidato.getNmVotavel());
            stmt.setInt(3, candidato.getQtVotos() );
            stmt.setInt(4, candidato.getCdCargoPergunta());
            stmt.setInt(5, candidato.getCdTipoVotavel());
            stmt.setInt(6, candidato.getNrPartido() );
         stmt.execute();
         DAO.closeConnection(conn, stmt);
         System.out.println("Candidato salvo");
         return true;
        } catch (SQLException ex) {
            Logger.getLogger(DAOCandidato.class.getName()).log(Level.SEVERE, null, ex);
         return false;
        }
    }else{
        return updateVotosCandidato(candidato);
    }
    
}
public static boolean updateVotosCandidato(Candidato c){
    conn = DAO.getConnection();
    PreparedStatement stmt;
    try {
        stmt = conn.prepareStatement(
                "update candidato set qt_votos = ("
                        +"(select qt_votos from candidato where nr_votavel = "+c.getNrVotavel()+")"+
                            "+"+c.getQtVotos() +") "
                                    + "where nr_votavel = "+c.getNrVotavel() );
        stmt.execute();
        DAO.closeConnection(conn, stmt);
        System.out.println("Votos atualizados");
        return true;
    } catch (SQLException ex) {
        Logger.getLogger(DAOCandidato.class.getName()).log(Level.SEVERE, null, ex);
        return false;
    }
}

public static boolean checkCandidato(Candidato c){
    
    conn = DAO.getConnection();
    PreparedStatement stmt;
    try {
        stmt = conn.prepareStatement("select * from candidato where nr_votavel = "+c.getNrVotavel() );
        ResultSet rs = stmt.executeQuery();
        
        if( !rs.next() )
            return false;
        
    } catch (SQLException ex) {
        Logger.getLogger(DAOCandidato.class.getName()).log(Level.SEVERE, null, ex);
        return false;
    }
    return true;
}

public static List<Candidato> getVotosTableModel(){
    conn = DAO.getConnection();
    PreparedStatement stmt;
    List<Candidato> candidatos = new ArrayList();
    
    try {
        stmt = conn.prepareStatement(
                "select c.nr_votavel ,c.nm_votavel, cs.ds_cargo_pergunta , p.sg_partido, c.qt_votos from candidato as c "+
                "right join cargos as cs on c.cd_cargo_pergunta = cs.CD_CARGO_PERGUNTA "+
                "right join partidos as p on c.nr_partido = p.NR_PARTIDO"
        );
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



}

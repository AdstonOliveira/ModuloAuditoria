package ClientSide.Model;


import Tools.Util;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.apache.derby.impl.drda.NetworkServerControlImpl;

/**
 * @author adston
 */
public class DAO {

    private static final String DRIVER = "org.apache.derby.jdbc.ClientDriver";
    private static final String URL = "jdbc:derby://localhost:1527/auditoria"; //porta padrão
    private static final String USER = "adm";
    private static final String PASS = "ifsp100%";

    public static Connection getConnection() {
        //Iniciar o banco sem precisar o fazer manualmente toda vez que executar e sem usar o netbeans
        try {
            System.setProperty("derby.system.home", ".\\db\\javadb\\auditoria");
        // Crie uma pasta onde você quiser que fique o seu banco e set aqui
           //pode ser qualquer diretorio incluindo a pasta onde estara o executavel(.jar)
           //nesse Caso: C:\\MyDB\\.netbeans-derby
            NetworkServerControlImpl networkServer = new NetworkServerControlImpl();
            networkServer.start(new PrintWriter(System.out));
            System.out.println("Banco Iniciado");
        } catch (Exception ex) {
            System.out.println("Não conseguiu iniciar banco de dados.");

        }

        try {
            Class.forName(DRIVER);
            return java.sql.DriverManager.getConnection(URL, USER, PASS);

        } catch (ClassNotFoundException | SQLException ex) {
            throw new RuntimeException("Erro na conexão: ", ex);
        }
    }

    public static void closeConnection(Connection con) {

        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException ex) {
            //menssagem
        }

    }

    public static void closeConnection(Connection con, PreparedStatement stmt) {

        closeConnection(con);

        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException ex) {
            //menssagem
        }

    }

    public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs) {

        closeConnection(con, stmt);

        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException ex) {
            //menssagem
        }

    }

    public static void Fazbackup(Connection con) {

        try {

            String backupdirectory = "\\MyDB\\.netbeans-derby";
            CallableStatement cs = con.prepareCall("CALL SYSCS_UTIL.SYSCS_BACKUP_DATABASE (?)");
            cs.setString(1, backupdirectory);
            cs.execute();
            cs.close();
            JOptionPane.showMessageDialog(null, "Backup feito com sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro no backup" + ex);
        }

    }

    public static void LerBackup(String diretorio) {

        String a = diretorio;

        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
            DriverManager.getConnection("jdbc:derby:BancoGerenciador;restoreFrom=" + a);
            JOptionPane.showMessageDialog(null, "Backing Up Realizado com sucesso!");
        } catch (InstantiationException ex) {
            JOptionPane.showMessageDialog(null, "Erro no backing Up" + ex);
        } catch (IllegalAccessException ex) {
            JOptionPane.showMessageDialog(null, "Erro no backing Up" + ex);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro no backing Up" + ex);
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Erro no backing Up" + ex);
        }

    }
    
    
    public static boolean login(String nome, String senha){
        Connection con = DAO.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        senha = Util.applySha256( senha );
           System.out.println(senha);
        try {
            stmt = con.prepareStatement("SELECT * FROM adm.cliente where nome='"+nome+"' and senha='"+ (senha) + "'");
            rs = stmt.executeQuery();
            if( !rs.next() ){
                JOptionPane.showMessageDialog(null,"Usuario ou senha inválidos", "Erro no login", 0);
                return false;
            }
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            DAO.closeConnection(con, stmt, rs);
        }
        return false;
        
    }
    
    public static boolean insertNovo(String nome, String senha){
        Connection con = DAO.getConnection();
        PreparedStatement stmt = null;
        
        nome = nome;

        try {
            stmt = con.prepareStatement("insert into adm.cliente(nome,senha) values (?,?)");
            stmt.setString(1, nome);
            stmt.setString(2, Util.applySha256(senha));
            
            stmt.executeUpdate();
            DAO.closeConnection(con, stmt);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            DAO.closeConnection(con, stmt);
        }
        return false;
    }
}

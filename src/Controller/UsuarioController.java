package Controller;

import DAO.UsuarioDAO;
import Models.Usuario;

import java.sql.SQLException;

public class UsuarioController {
    public static void main(String[] args) throws SQLException {
        Usuario usu = new Usuario();

        usu.setId(4);
        usu.setNome("Rafa Paciencia");
        usu.setDocumento("443332224");
        usu.setDataNascimento("1990-06-23");
        usu.setEmail("rafa3@g.com");
        usu.setSenha("mSenha321");

        UsuarioDAO dao = new UsuarioDAO();

        dao.cadastrarUsuario(usu);
    }
}

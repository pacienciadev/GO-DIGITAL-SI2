package View;
import DAO.UsuarioDAO;
import Models.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class HomeView extends JFrame {
    private JLabel lblEmail;
    private JLabel lblPassword;
    private JTextField txtEmail;
    private JPasswordField pwdPassword;
    private JButton btnLogin;

    Usuario usu = new Usuario();

    public HomeView() {
        super("Área do Usuário");

        this.setLayout(new GridBagLayout());
        this.setSize(250,200);

        lblEmail = new JLabel("Digite seu E-mail: ");
        lblPassword = new JLabel("Digite sua Senha: ");

        txtEmail = new JTextField(10);
        pwdPassword = new JPasswordField(10);

        btnLogin = new JButton("Entrar");

        Handler handler = new Handler();
        pwdPassword.addActionListener(handler);
        btnLogin.addActionListener(handler);

        GridBagConstraints posicoes = new GridBagConstraints();
        posicoes.insets = new Insets(3,0,3,0);
        posicoes.anchor = posicoes.LINE_START;
        posicoes.gridx = 0;
        posicoes.gridy = 0;
        add(lblEmail, posicoes);
        posicoes.gridx = 1;
        posicoes.gridy = 0;
        add(txtEmail, posicoes);
        posicoes.gridx = 0;
        posicoes.gridy = 1;
        add(lblPassword, posicoes);
        posicoes.gridx = 1;
        posicoes.gridy = 1;
        add(pwdPassword, posicoes);
        posicoes.gridx = 0;
        posicoes.gridy = 2;
        add(btnLogin, posicoes);

    }

    public class Handler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent evento) {
            Usuario usu = new Usuario();

            UsuarioDAO dao = new UsuarioDAO();

            try {
                usu = dao.loginUsuario(txtEmail.getText(), pwdPassword.getText());

                if(usu.getNome() != null){
                    JOptionPane.showMessageDialog(null, "Usuário ou senha incorreto");
                } else {
                    JOptionPane.showMessageDialog(null, "Login realizado, bem-vindo " + usu.getNome());
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public static void main(String[] args) {
        HomeView janela = new HomeView();
        janela.setDefaultCloseOperation(EXIT_ON_CLOSE);
        janela.setVisible(true);
    }
}

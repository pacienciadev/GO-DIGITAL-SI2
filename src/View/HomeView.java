package View;
import DAO.UsuarioDAO;
import Models.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Scanner;

public class HomeView extends JFrame {
    private JLabel lblEmail;
    private JLabel lblPassword;
    private JTextField txtEmail;
    private JPasswordField pwdPassword;
    private JButton btnLogin;

    static HomeView janela = new HomeView();

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
            UsuarioDAO dao = new UsuarioDAO();

            try {
                Usuario usu = dao.loginUsuario(txtEmail.getText(), pwdPassword.getText());

                if(usu.getNome() != null){
                    JOptionPane.showMessageDialog(null, "Login realizado, bem-vindo");

                    janela.setVisible(false);
                    dispose(); //Destroy the JFrame object

                    System.out.println("==============================================\n"
                            +"Bem-vindo ao sistema - Go Digital Challenge"
                            +"\n==============================================");

                    crudSystem();
                } else {
                    JOptionPane.showMessageDialog(null, "Usuário ou senha incorretos");

                    System.out.println("\nVocê não tem permissão para acessar o sistema, sessão encerrada!");
                    System.exit(0);
                }
            } catch (SQLException e) {
                System.out.println("Algo de errado com a conexão do banco de dados.");
                throw new RuntimeException(e);
            }
        }
    }

    public void crudSystem() throws SQLException {
        Scanner numero = new Scanner(System.in);
        Scanner texto = new Scanner(System.in);

        Usuario usu = new Usuario();
        UsuarioDAO dao = new UsuarioDAO();

        System.out.println("Digite uma das opções: \n1 - Para cadastrar um novo usuário\n2 - Para alterar um usuário\n3 - Para excluir um usuário\n4 - Para consultar um usuário existente:\n5 - Para sair ");
        int opcao = numero.nextInt();

        if(opcao == 1) {
            usu.setId(10);
            usu.setNome("Rafael Teste Eduardo");
            usu.setEmail("meu@email.com");
            usu.setDocumento("4466633377");
            usu.setDataNascimento("1990-12-31");
            usu.setSenha("myTest");

            dao.cadastrarUsuario(usu);
            System.out.println("\nCadastro efetuado com sucesso\n\n");
        }
        else if(opcao == 3) {
            System.out.println("Digite o id do contato");
            int id = numero.nextInt();
            System.out.println("Deseja realmente excluir o usuário?\n(Digite 'S' ou 's' para excluir)");
            String resp = texto.nextLine();
            if(resp.equals("S") || resp.equals("s")) {
                dao.excluirUsuario(id);
                System.out.println("\nUsuário excluído com sucesso\n\n");
            }else{
                System.out.println("\nA ação no foi efetivada!\n");
            }
        }
        else if(opcao == 4) {
            System.out.println("Digite o id do contato");
            int id = numero.nextInt();
            usu = dao.buscarUsuarioPorId(id);
            if(usu.getNome()==null){
                System.out.println("\n\n USUÁRIO INEXISTENTE NA BASE \n\n");
            } else {
                System.out.println("\n\n**************************************"
                        +"\nId:" + usu.getId()
                        +"\nNome:" + usu.getNome()
                        +"\nE-mail: "+ usu.getEmail()
                        +"\nDocumento: "+ usu.getDocumento()
                        +"\nData de nascimento: "+ usu.getDataNascimento()
                        +"\n**************************************\n\n");
            }
        }
        else if(opcao == 5){
            System.out.println("\n\n OBRIGADO POR UTILIZAR NOSSO SISTEMA! \n\n");

            System.exit(0);
        }

        crudSystem();
    }

    public static void main(String[] args) {
        janela.setDefaultCloseOperation(EXIT_ON_CLOSE);
        janela.setVisible(true);
    }
}

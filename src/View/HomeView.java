package View;
import DAO.UsuarioDAO;
import Models.Usuario;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.Scanner;

public class HomeView extends JFrame {
    public static void main(String[] args) throws SQLException {
        HomeView janela = new HomeView();

        janela.setLayout(new FlowLayout());
        janela.setSize(300,300);
        janela.setVisible(true);

//        //Inicializando a JLabel para o nome
//        lblNomeUsuario = new JLabel("Insira seu nome:");
//        //adicionando a JLabel
//        add(lblNomeUsuario);
//        //Inicializando a caixa de texto com largura para 10 caracteres
//        txtNomeUsuario = new JTextField(10);
//        //incluindo a caixa de texto na nossa janela
//        add(txtNomeUsuario);

















        Scanner numero = new Scanner(System.in);
        Scanner texto = new Scanner(System.in);

        Usuario usu = new Usuario();

        usu.setId(5);
//        usu.setNome(JOptionPane.showInputDialog(null, "Digite o nome do usuário: ", "GO-DIGITAL-SI2", JOptionPane.INFORMATION_MESSAGE));
        usu.setEmail("joao2@fiap.com.br");
        usu.setDocumento("1154545845");
        usu.setDataNascimento("1990-06-23");
        usu.setSenha("myPassPass123");

        UsuarioDAO dao = new UsuarioDAO();

        System.out.println("Digite 1 para cadastrar\n2 para alterar\n3 para excluir\n4 para consultar: ");
        int opcao = numero.nextInt();

        if(opcao == 1) {
            dao.cadastrarUsuario(usu);
            System.out.println("Cadastro efetuado com sucesso");
        }
        else if(opcao == 3) {
            System.out.println("Digite o id do contato");
            int id = numero.nextInt();
            System.out.println("Deseja realmente excluir o contato?");
            String resp = texto.nextLine();
            if(resp.equals("Sim")) {
                dao.excluirUsuario(id);
                System.out.println("Contato excluido com sucesso");
            }
        }
        else if(opcao == 4) {
            System.out.println("Digite o id do contato");
            int id = numero.nextInt();
            usu = dao.buscarUsuarioPorId(id);
            System.out.println("Nome:" + usu.getNome() + " email: "+ usu.getEmail());
        }
    }
}

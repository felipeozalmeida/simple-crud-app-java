/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userscrudapp.view;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import userscrudapp.controller.UsuarioController;
import userscrudapp.model.bean.UsuarioBean;

/**
 *
 * @author Felipe Almeida
 */
public class LoginView {

    private static UsuarioController ContUsu = null;

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // Configuração
        LoginView.ContUsu = new UsuarioController();

        // Bootstrap da aplicação
        if (valida()) {
            // UsuarioView.main();
            // PessoaView.main();
            PessoaUsuarioView.main();
        }
    }

    public static boolean valida() throws SQLException {
        String login = JOptionPane.showInputDialog("Entre com o Login");
        String senha = JOptionPane.showInputDialog("Entre com o Senha");
        UsuarioBean usu = LoginView.ContUsu.valida(new UsuarioBean(0, login, senha, "", ""));

        if (usu == null) { return false; }
        JOptionPane.showMessageDialog(null, usu);
        return true;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userscrudapp.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.MouseInputAdapter;

import userscrudapp.controller.UsuarioController;
import userscrudapp.model.bean.UsuarioBean;
import userscrudapp.viewmodel.UsuarioTableModel;

/**
 *
 * @author Felipe Almeida
 */
public class UsuarioView {

    private static UsuarioController CONTROLLER = null;
    private static JFrame usuFrame;
    private static JTable usuTable;
    private static JScrollPane usuScrollPane;
    private static JPanel usuPanel;
    private static JPanel usuInseriPanel;
    private static final String[] STATUS = { "ATIVO", "DESATIVADO" };
    private static final String[] TIPOS = { "ADM", "PADRÃO" };

    public static void main() throws ClassNotFoundException, SQLException {
        // Configuração
        UsuarioView.CONTROLLER = new UsuarioController();
        usuFrame = new JFrame("Usuários");
        usuFrame.setBounds(30, 30, 600, 450);
        usuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Bootstrap da aplicação
        lista();
    }

    private static void lista() throws SQLException {
        usuTable = new JTable(new UsuarioTableModel(CONTROLLER.lista()));
        usuTable.setAutoCreateRowSorter(true);
        usuTable.getTableHeader().setBackground(Color.BLACK);
        usuTable.getTableHeader().setForeground(Color.YELLOW);
        usuTable.addMouseListener(new MouseInputAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    JTable targetTable = (JTable) e.getSource();
                    int rowIndex = targetTable.rowAtPoint(e.getPoint());
                    if (rowIndex != -1) {
                        int id = (int) targetTable.getModel().getValueAt(rowIndex, 0);
                        UsuarioBean usu = new UsuarioBean(id, null, null, null, null);
                        try {
                            busca(usu);
                        } catch (SQLException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                    }
                }
            };
        });

        usuScrollPane = new JScrollPane(usuTable);
        usuScrollPane.setPreferredSize(new Dimension(580, 430));

        usuPanel = new JPanel();
        usuPanel.add(usuScrollPane);

        @SuppressWarnings("serial")
        JButton usuInseriButton = new JButton(new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                try {
                    inseri(e);
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });
        usuInseriButton.setText("Adicionar");

        @SuppressWarnings("serial")
        JButton usuAlteraButton = new JButton(new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                int rowIndex = usuTable.getSelectedRow();
                if (rowIndex != -1) {
                    int id = (int) usuTable.getModel().getValueAt(rowIndex, 0);
                    String login = (String) usuTable.getModel().getValueAt(rowIndex, 1);
                    String senha = (String) usuTable.getModel().getValueAt(rowIndex, 2);
                    String status = (String) usuTable.getModel().getValueAt(rowIndex, 3);
                    String tipo = (String) usuTable.getModel().getValueAt(rowIndex, 4);
                    UsuarioBean usu = new UsuarioBean(id, login, senha, status, tipo);
                    try {
                        altera(e, usu);
                    } catch (SQLException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }
            }
        });
        usuAlteraButton.setText("Editar");

        @SuppressWarnings("serial")
        JButton usuExcluiButton = new JButton(new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                int rowIndex = usuTable.getSelectedRow();
                if (rowIndex != -1) {
                    int id = (int) usuTable.getModel().getValueAt(rowIndex, 0);
                    String login = (String) usuTable.getModel().getValueAt(rowIndex, 1);
                    String senha = (String) usuTable.getModel().getValueAt(rowIndex, 2);
                    String status = (String) usuTable.getModel().getValueAt(rowIndex, 3);
                    String tipo = (String) usuTable.getModel().getValueAt(rowIndex, 4);
                    UsuarioBean usu = new UsuarioBean(id, login, senha, status, tipo);
                    try {
                        exclui(e, usu);
                    } catch (HeadlessException | SQLException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }
            }
        });
        usuExcluiButton.setText("Excluir");

        usuInseriPanel = new JPanel();
        usuInseriPanel.add(usuInseriButton);
        usuInseriPanel.add(usuAlteraButton);
        usuInseriPanel.add(usuExcluiButton);

        usuFrame.add(usuInseriPanel, BorderLayout.NORTH);
        usuFrame.add(usuPanel, BorderLayout.CENTER);
        usuFrame.setVisible(true);
    }

    private static void busca(UsuarioBean usu) throws SQLException {
        UsuarioBean usuBusca = CONTROLLER.busca(usu);
        JOptionPane.showMessageDialog(null, usuBusca, "Visualizar usuário", JOptionPane.INFORMATION_MESSAGE);
    }

    private static void inseri(ActionEvent e) throws SQLException {
        String login = JOptionPane.showInputDialog(null, "Login", "Cadastrar usuário", JOptionPane.INFORMATION_MESSAGE);
        String senha = JOptionPane.showInputDialog(null, "Senha", "Cadastrar usuário", JOptionPane.INFORMATION_MESSAGE);
        String status = (String) JOptionPane.showInputDialog(null, "Status", "Cadastrar usuário",
                JOptionPane.INFORMATION_MESSAGE, null, STATUS, STATUS[0]);
        String tipo = (String) JOptionPane.showInputDialog(null, "Tipo", "Cadastrar usuário",
                JOptionPane.INFORMATION_MESSAGE, null, TIPOS, TIPOS[0]);
        UsuarioBean usuInseri = CONTROLLER.inseri(new UsuarioBean(0, login, senha, status, tipo));
        JOptionPane.showMessageDialog(null, usuInseri, "Usuário cadastrado", JOptionPane.INFORMATION_MESSAGE);
        usuTable.setModel(new UsuarioTableModel(CONTROLLER.lista()));
    }

    private static void altera(ActionEvent e, UsuarioBean usu) throws SQLException {
        usu.setLogin((String) JOptionPane.showInputDialog(null, "Login", "Alterar usuário",
                JOptionPane.INFORMATION_MESSAGE, null, null, usu.getLogin()));
        usu.setSenha((String) JOptionPane.showInputDialog(null, "Senha", "Alterar usuário",
                JOptionPane.INFORMATION_MESSAGE, null, null, usu.getSenha()));
        usu.setStatus((String) JOptionPane.showInputDialog(null, "Status", "Alterar usuário",
                JOptionPane.INFORMATION_MESSAGE, null, STATUS, usu.getStatus()));
        usu.setTipo((String) JOptionPane.showInputDialog(null, "Tipo", "Alterar usuário",
                JOptionPane.INFORMATION_MESSAGE, null, TIPOS, usu.getTipo()));
        UsuarioBean usuAltera = CONTROLLER.altera(usu);
        JOptionPane.showMessageDialog(null, usuAltera, "Usuário alterado", JOptionPane.INFORMATION_MESSAGE);
        usuTable.setModel(new UsuarioTableModel(CONTROLLER.lista()));
    }

    private static void exclui(ActionEvent e, UsuarioBean usu) throws HeadlessException, SQLException {
        if (CONTROLLER.exclui(usu)) {
            JOptionPane.showMessageDialog(null, "Usuário excluído", "Usuário excluído", JOptionPane.INFORMATION_MESSAGE);
            usuTable.setModel(new UsuarioTableModel(CONTROLLER.lista()));
        }
    }

}

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

import userscrudapi.controller.PessoaUsuarioController;
import userscrudapi.model.bean.PessoaUsuarioBean;
import userscrudapp.viewmodel.PessoaUsuarioTableModel;

/**
 *
 * @author Felipe Almeida
 */
public class PessoaUsuarioView {

    private static PessoaUsuarioController controller = null;
    private static JFrame pessoaUsuarioFrame;
    private static JTable pessoaUsuarioTable;
    private static JScrollPane pessoaUsuarioScrollPane;
    private static JPanel pessoaUsuarioPanel;
    private static JPanel pessoaUsuarioInseriPanel;

    public static void main() throws ClassNotFoundException, SQLException {
        // Configuração
        PessoaUsuarioView.controller = new PessoaUsuarioController();
        pessoaUsuarioFrame = new JFrame("Pessoa X Usuário");
        pessoaUsuarioFrame.setBounds(30, 30, 600, 450);
        pessoaUsuarioFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Bootstrap da aplicação
        lista();
    }

    private static void lista() throws ClassNotFoundException, SQLException {
        pessoaUsuarioTable = new JTable(new PessoaUsuarioTableModel(controller.lista()));
        pessoaUsuarioTable.setAutoCreateRowSorter(true);
        pessoaUsuarioTable.getTableHeader().setBackground(Color.BLACK);
        pessoaUsuarioTable.getTableHeader().setForeground(Color.YELLOW);
        pessoaUsuarioTable.addMouseListener(new MouseInputAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    JTable targetTable = (JTable) e.getSource();
                    int rowIndex = targetTable.rowAtPoint(e.getPoint());
                    if (rowIndex != -1) {
                        int id = (int) targetTable.getModel().getValueAt(rowIndex, 0);
                        PessoaUsuarioBean pessoaUsuario = null;
                        try {
                            pessoaUsuario = new PessoaUsuarioBean(id);
                            try {
                                busca(pessoaUsuario);
                            } catch (SQLException e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                            }
                        } catch (ClassNotFoundException | SQLException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                    }
                }
            };
        });

        pessoaUsuarioScrollPane = new JScrollPane(pessoaUsuarioTable);
        pessoaUsuarioScrollPane.setPreferredSize(new Dimension(580, 430));

        pessoaUsuarioPanel = new JPanel();
        pessoaUsuarioPanel.add(pessoaUsuarioScrollPane);

        @SuppressWarnings("serial")
        JButton pessoaUsuarioInseriButton = new JButton(new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                try {
                    inseri(e);
                } catch (ClassNotFoundException | SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });
        pessoaUsuarioInseriButton.setText("Adicionar");

        @SuppressWarnings("serial")
        JButton pessoaUsuarioAlteraButton = new JButton(new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                int rowIndex = pessoaUsuarioTable.getSelectedRow();
                if (rowIndex != -1) {
                    int id = (int) pessoaUsuarioTable.getModel().getValueAt(rowIndex, 0);
                    int idPessoa = (int) pessoaUsuarioTable.getModel().getValueAt(rowIndex, 1);
                    int idUsuario = (int) pessoaUsuarioTable.getModel().getValueAt(rowIndex, 2);
                    PessoaUsuarioBean pessoaUsuario = null;
                    try {
                        pessoaUsuario = new PessoaUsuarioBean(id, idPessoa, idUsuario);
                        try {
                            altera(e, pessoaUsuario);
                        } catch (SQLException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                    } catch (ClassNotFoundException | SQLException e2) {
                        // TODO Auto-generated catch block
                        e2.printStackTrace();
                    }
                }
            }
        });
        pessoaUsuarioAlteraButton.setText("Editar");

        @SuppressWarnings("serial")
        JButton pessoaUsuarioExcluiButton = new JButton(new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                int rowIndex = pessoaUsuarioTable.getSelectedRow();
                if (rowIndex != -1) {
                    int id = (int) pessoaUsuarioTable.getModel().getValueAt(rowIndex, 0);
                    int idPessoa = (int) pessoaUsuarioTable.getModel().getValueAt(rowIndex, 1);
                    int idUsuario = (int) pessoaUsuarioTable.getModel().getValueAt(rowIndex, 2);
                    PessoaUsuarioBean pessoaUsuario;
                    try {
                        pessoaUsuario = new PessoaUsuarioBean(id, idPessoa, idUsuario);
                        try {
                            exclui(e, pessoaUsuario);
                        } catch (HeadlessException | SQLException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                    } catch (ClassNotFoundException | SQLException e2) {
                        // TODO Auto-generated catch block
                        e2.printStackTrace();
                    }
                }
            }
        });
        pessoaUsuarioExcluiButton.setText("Excluir");

        pessoaUsuarioInseriPanel = new JPanel();
        pessoaUsuarioInseriPanel.add(pessoaUsuarioInseriButton);
        pessoaUsuarioInseriPanel.add(pessoaUsuarioAlteraButton);
        pessoaUsuarioInseriPanel.add(pessoaUsuarioExcluiButton);

        pessoaUsuarioFrame.add(pessoaUsuarioInseriPanel, BorderLayout.NORTH);
        pessoaUsuarioFrame.add(pessoaUsuarioPanel, BorderLayout.CENTER);
        pessoaUsuarioFrame.setVisible(true);
    }

    private static void busca(PessoaUsuarioBean pessoaUsuario) throws ClassNotFoundException, SQLException {
        PessoaUsuarioBean pessoaUsuarioBusca = controller.busca(pessoaUsuario);
        JOptionPane.showMessageDialog(null, pessoaUsuarioBusca, "Visualizar pessoa X usuário",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private static void inseri(ActionEvent e) throws ClassNotFoundException, SQLException {
        int idPessoa = Integer.parseInt(JOptionPane.showInputDialog(null, "IdPessoa", "Cadastrar pessoa X usuário",
                JOptionPane.INFORMATION_MESSAGE));
        int idUsuario = Integer.parseInt(JOptionPane.showInputDialog(null, "IdUsuario", "Cadastrar pessoa X usuário",
                JOptionPane.INFORMATION_MESSAGE));
        PessoaUsuarioBean pessoaUsuarioInseri = controller.inseri(new PessoaUsuarioBean(0, idPessoa, idUsuario));
        JOptionPane.showMessageDialog(null, pessoaUsuarioInseri, "Pessoa X usuário cadastrado",
                JOptionPane.INFORMATION_MESSAGE);
        pessoaUsuarioTable.setModel(new PessoaUsuarioTableModel(controller.lista()));
    }

    private static void altera(ActionEvent e, PessoaUsuarioBean pessoaUsuario) throws ClassNotFoundException, SQLException {
        pessoaUsuario.setIdPessoa(Integer.parseInt((String) JOptionPane.showInputDialog(null, "IdPessoa",
                "Alterar pessoa X usuário", JOptionPane.INFORMATION_MESSAGE, null, null, pessoaUsuario.getIdPessoa())));
        pessoaUsuario.setIdUsuario(
                Integer.parseInt((String) JOptionPane.showInputDialog(null, "IdUsuario", "Alterar pessoa X usuário",
                        JOptionPane.INFORMATION_MESSAGE, null, null, pessoaUsuario.getIdUsuario())));
        PessoaUsuarioBean pessoaUsuarioAltera = controller.altera(pessoaUsuario);
        JOptionPane.showMessageDialog(null, pessoaUsuarioAltera, "Pessoa X usuário alterado",
                JOptionPane.INFORMATION_MESSAGE);
        pessoaUsuarioTable.setModel(new PessoaUsuarioTableModel(controller.lista()));
    }

    private static void exclui(ActionEvent e, PessoaUsuarioBean pessoaUsuario) throws ClassNotFoundException, HeadlessException, SQLException {
        if (controller.exclui(pessoaUsuario)) {
            JOptionPane.showMessageDialog(null, "Pessoa X usuário excluído", "Pessoa X usuário excluído", JOptionPane.INFORMATION_MESSAGE);
            pessoaUsuarioTable.setModel(new PessoaUsuarioTableModel(controller.lista()));
        }
    }
}

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

import userscrudapp.controller.PessoaController;
import userscrudapp.model.bean.PessoaBean;
import userscrudapp.viewmodel.PessoaTableModel;

/**
 *
 * @author Felipe Almeida
 */
public class PessoaView {

    private static PessoaController controller = null;
    private static JFrame pessoaFrame;
    private static JTable pessoaTable;
    private static JScrollPane pessoaScrollPane;
    private static JPanel pessoaPanel;
    private static JPanel pessoaInseriPanel;

    public static void main() throws ClassNotFoundException, SQLException {
        // Configuração
        PessoaView.controller = new PessoaController();
        pessoaFrame = new JFrame("Pessoas");
        pessoaFrame.setBounds(30, 30, 600, 450);
        pessoaFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Bootstrap da aplicação
        lista();
    }

    private static void lista() throws SQLException {
        pessoaTable = new JTable(new PessoaTableModel(controller.lista()));
        pessoaTable.setAutoCreateRowSorter(true);
        pessoaTable.getTableHeader().setBackground(Color.BLACK);
        pessoaTable.getTableHeader().setForeground(Color.YELLOW);
        pessoaTable.addMouseListener(new MouseInputAdapter() {
            public void mousePressed(final MouseEvent e) {
                if (e.getClickCount() == 2) {
                    final JTable targetTable = (JTable) e.getSource();
                    final int rowIndex = targetTable.rowAtPoint(e.getPoint());
                    if (rowIndex != -1) {
                        final int id = (int) targetTable.getModel().getValueAt(rowIndex, 0);
                        final PessoaBean pessoa = new PessoaBean(id, null, null);
                        try {
                            busca(pessoa);
                        } catch (final SQLException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                    }
                }
            };
        });

        pessoaScrollPane = new JScrollPane(pessoaTable);
        pessoaScrollPane.setPreferredSize(new Dimension(580, 430));

        pessoaPanel = new JPanel();
        pessoaPanel.add(pessoaScrollPane);

        @SuppressWarnings("serial")
        final JButton pessoaInseriButton = new JButton(new AbstractAction() {
            public void actionPerformed(final ActionEvent e) {
                try {
                    inseri(e);
                } catch (final SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });
        pessoaInseriButton.setText("Adicionar");

        @SuppressWarnings("serial")
        final JButton pessoaAlteraButton = new JButton(new AbstractAction() {
            public void actionPerformed(final ActionEvent e) {
                final int rowIndex = pessoaTable.getSelectedRow();
                if (rowIndex != -1) {
                    final int id = (int) pessoaTable.getModel().getValueAt(rowIndex, 0);
                    final String nome = (String) pessoaTable.getModel().getValueAt(rowIndex, 1);
                    final String cargo = (String) pessoaTable.getModel().getValueAt(rowIndex, 2);
                    final PessoaBean pessoa = new PessoaBean(id, nome, cargo);
                    try {
                        altera(e, pessoa);
                    } catch (final SQLException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }
            }
        });
        pessoaAlteraButton.setText("Editar");

        @SuppressWarnings("serial")
        final JButton pessoaExcluiButton = new JButton(new AbstractAction() {
            public void actionPerformed(final ActionEvent e) {
                final int rowIndex = pessoaTable.getSelectedRow();
                if (rowIndex != -1) {
                    final int id = (int) pessoaTable.getModel().getValueAt(rowIndex, 0);
                    final String nome = (String) pessoaTable.getModel().getValueAt(rowIndex, 1);
                    final String cargo = (String) pessoaTable.getModel().getValueAt(rowIndex, 2);
                    final PessoaBean pessoa = new PessoaBean(id, nome, cargo);
                    try {
                        exclui(e, pessoa);
                    } catch (HeadlessException | SQLException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }
            }
        });
        pessoaExcluiButton.setText("Excluir");

        pessoaInseriPanel = new JPanel();
        pessoaInseriPanel.add(pessoaInseriButton);
        pessoaInseriPanel.add(pessoaAlteraButton);
        pessoaInseriPanel.add(pessoaExcluiButton);

        pessoaFrame.add(pessoaInseriPanel, BorderLayout.NORTH);
        pessoaFrame.add(pessoaPanel, BorderLayout.CENTER);
        pessoaFrame.setVisible(true);
    }

    private static void busca(final PessoaBean pessoa) throws SQLException {
        final PessoaBean pessoaBusca = controller.busca(pessoa);
        JOptionPane.showMessageDialog(null, pessoaBusca, "Visualizar pessoa", JOptionPane.INFORMATION_MESSAGE);
    }

    private static void inseri(final ActionEvent e) throws SQLException {
        final String nome = JOptionPane.showInputDialog(null, "Login", "Cadastrar pessoa",
                JOptionPane.INFORMATION_MESSAGE);
        final String cargo = JOptionPane.showInputDialog(null, "Senha", "Cadastrar pessoa",
                JOptionPane.INFORMATION_MESSAGE);
        final PessoaBean pessoaInseri = controller.inseri(new PessoaBean(0, nome, cargo));
        JOptionPane.showMessageDialog(null, pessoaInseri, "Pessoa cadastrada", JOptionPane.INFORMATION_MESSAGE);
        pessoaTable.setModel(new PessoaTableModel(controller.lista()));
    }

    private static void altera(final ActionEvent e, final PessoaBean pessoa) throws SQLException {
        pessoa.setNome((String) JOptionPane.showInputDialog(null, "Login", "Alterar pessoa",
                JOptionPane.INFORMATION_MESSAGE, null, null, pessoa.getNome()));
        pessoa.setCargo((String) JOptionPane.showInputDialog(null, "Senha", "Alterar pessoa",
                JOptionPane.INFORMATION_MESSAGE, null, null, pessoa.getCargo()));
        final PessoaBean pessoaAltera = controller.altera(pessoa);
        JOptionPane.showMessageDialog(null, pessoaAltera, "Pessoa alterada", JOptionPane.INFORMATION_MESSAGE);
        pessoaTable.setModel(new PessoaTableModel(controller.lista()));
    }

    private static void exclui(final ActionEvent e, final PessoaBean pessoa) throws HeadlessException, SQLException {
        if (controller.exclui(pessoa)) {
            JOptionPane.showMessageDialog(null, "Pessoa excluída", "Pessoa excluída", JOptionPane.INFORMATION_MESSAGE);
            pessoaTable.setModel(new PessoaTableModel(controller.lista()));
        }
    }
}

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

import userscrudapi.controller.FornecedorController;
import userscrudapi.model.bean.FornecedorBean;
import userscrudapp.viewmodel.FornecedorTableModel;

/**
 *
 * @author Felipe Almeida
 */
public class FornecedorView {

    private static FornecedorController controller = null;
    private static JFrame fornecedorFrame;
    private static JTable fornecedorTable;
    private static JScrollPane fornecedorScrollPane;
    private static JPanel fornecedorPanel;
    private static JPanel fornecedorInseriPanel;

    public static void main() throws ClassNotFoundException, SQLException {
        // Configuração
        FornecedorView.controller = new FornecedorController();
        fornecedorFrame = new JFrame("Fornecedores");
        fornecedorFrame.setBounds(30, 30, 600, 450);
        fornecedorFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Bootstrap da aplicação
        lista();
    }

    private static void lista() throws ClassNotFoundException, SQLException {
        fornecedorTable = new JTable(new FornecedorTableModel(controller.lista()));
        fornecedorTable.setAutoCreateRowSorter(true);
        fornecedorTable.getTableHeader().setBackground(Color.BLACK);
        fornecedorTable.getTableHeader().setForeground(Color.YELLOW);
        fornecedorTable.addMouseListener(new MouseInputAdapter() {
            public void mousePressed(final MouseEvent e) {
                if (e.getClickCount() == 2) {
                    final JTable targetTable = (JTable) e.getSource();
                    final int rowIndex = targetTable.rowAtPoint(e.getPoint());
                    if (rowIndex != -1) {
                        final int id = (int) targetTable.getModel().getValueAt(rowIndex, 0);
                        FornecedorBean fornecedor;
                        try {
                            fornecedor = new FornecedorBean(id);
                            try {
                                busca(fornecedor);
                            } catch (final SQLException e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                            }
                        } catch (ClassNotFoundException | SQLException e2) {
                            // TODO Auto-generated catch block
                            e2.printStackTrace();
                        }
                    }
                }
            };
        });

        fornecedorScrollPane = new JScrollPane(fornecedorTable);
        fornecedorScrollPane.setPreferredSize(new Dimension(580, 430));

        fornecedorPanel = new JPanel();
        fornecedorPanel.add(fornecedorScrollPane);

        @SuppressWarnings("serial")
        final JButton fornecedorInseriButton = new JButton(new AbstractAction() {
            public void actionPerformed(final ActionEvent e) {
                try {
                    inseri(e);
                } catch (final ClassNotFoundException | SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });
        fornecedorInseriButton.setText("Adicionar");

        @SuppressWarnings("serial")
        final JButton fornecedorAlteraButton = new JButton(new AbstractAction() {
            public void actionPerformed(final ActionEvent e) {
                final int rowIndex = fornecedorTable.getSelectedRow();
                if (rowIndex != -1) {
                    final int id = (int) fornecedorTable.getModel().getValueAt(rowIndex, 0);
                    final int idPessoa = (int) fornecedorTable.getModel().getValueAt(rowIndex, 1);
                    final String cnpj = (String) fornecedorTable.getModel().getValueAt(rowIndex, 2);
                    final String endereco = (String) fornecedorTable.getModel().getValueAt(rowIndex, 3);
                    final String nome = (String) fornecedorTable.getModel().getValueAt(rowIndex, 4);
                    FornecedorBean fornecedor;
                    try {
                        fornecedor = new FornecedorBean(id, idPessoa, cnpj, endereco, nome);
                        try {
                            altera(e, fornecedor);
                        } catch (final SQLException e1) {
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
        fornecedorAlteraButton.setText("Editar");

        @SuppressWarnings("serial")
        final JButton fornecedorExcluiButton = new JButton(new AbstractAction() {
            public void actionPerformed(final ActionEvent e) {
                final int rowIndex = fornecedorTable.getSelectedRow();
                if (rowIndex != -1) {
                    final int id = (int) fornecedorTable.getModel().getValueAt(rowIndex, 0);
                    final int idPessoa = (int) fornecedorTable.getModel().getValueAt(rowIndex, 1);
                    final String cnpj = (String) fornecedorTable.getModel().getValueAt(rowIndex, 2);
                    final String endereco = (String) fornecedorTable.getModel().getValueAt(rowIndex, 3);
                    final String nome = (String) fornecedorTable.getModel().getValueAt(rowIndex, 4);
                    FornecedorBean fornecedor;
                    try {
                        fornecedor = new FornecedorBean(id, idPessoa, cnpj, endereco, nome);
                        try {
                            exclui(e, fornecedor);
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
        fornecedorExcluiButton.setText("Excluir");

        fornecedorInseriPanel = new JPanel();
        fornecedorInseriPanel.add(fornecedorInseriButton);
        fornecedorInseriPanel.add(fornecedorAlteraButton);
        fornecedorInseriPanel.add(fornecedorExcluiButton);

        fornecedorFrame.add(fornecedorInseriPanel, BorderLayout.NORTH);
        fornecedorFrame.add(fornecedorPanel, BorderLayout.CENTER);
        fornecedorFrame.setVisible(true);
    }

    private static void busca(final FornecedorBean fornecedor) throws ClassNotFoundException, SQLException {
        final FornecedorBean fornecedorBusca = controller.busca(fornecedor);
        JOptionPane.showMessageDialog(null, fornecedorBusca, "Visualizar fornecedor", JOptionPane.INFORMATION_MESSAGE);
    }

    private static void inseri(final ActionEvent e) throws ClassNotFoundException, SQLException {
        final int idPessoa = Integer.parseInt(JOptionPane.showInputDialog(null, "ID da Pessoa", "Cadastrar fornecedor",
                JOptionPane.INFORMATION_MESSAGE));
        final String cnpj = JOptionPane.showInputDialog(null, "CNPJ", "Cadastrar fornecedor",
                JOptionPane.INFORMATION_MESSAGE);
        final String endereco = JOptionPane.showInputDialog(null, "Endereço", "Cadastrar fornecedor",
                JOptionPane.INFORMATION_MESSAGE);
        final String nome = JOptionPane.showInputDialog(null, "Nome", "Cadastrar fornecedor",
                JOptionPane.INFORMATION_MESSAGE);
        final FornecedorBean fornecedorInseri = controller.inseri(new FornecedorBean(0, idPessoa, cnpj, endereco, nome));
        JOptionPane.showMessageDialog(null, fornecedorInseri, "Fornecedor cadastrado", JOptionPane.INFORMATION_MESSAGE);
        fornecedorTable.setModel(new FornecedorTableModel(controller.lista()));
    }

    private static void altera(final ActionEvent e, final FornecedorBean fornecedor) throws ClassNotFoundException, SQLException {
        fornecedor.setIdPessoa(Integer.parseInt((String) JOptionPane.showInputDialog(null, "ID da Pessoa", "Alterar fornecedor",
                JOptionPane.INFORMATION_MESSAGE, null, null, fornecedor.getIdPessoa())));
        fornecedor.setCnpj((String) JOptionPane.showInputDialog(null, "CNPJ", "Alterar fornecedor",
                JOptionPane.INFORMATION_MESSAGE, null, null, fornecedor.getCnpj()));
        fornecedor.setEndereco((String) JOptionPane.showInputDialog(null, "Endereço",
                "Alterar fornecedor", JOptionPane.INFORMATION_MESSAGE, null, null, fornecedor.getEndereco()));
        fornecedor.setNome((String) JOptionPane.showInputDialog(null, "Nome",
                "Alterar fornecedor", JOptionPane.INFORMATION_MESSAGE, null, null, fornecedor.getNome()));
        final FornecedorBean fornecedorAltera = controller.altera(fornecedor);
        JOptionPane.showMessageDialog(null, fornecedorAltera, "Fornecedor alterado", JOptionPane.INFORMATION_MESSAGE);
        fornecedorTable.setModel(new FornecedorTableModel(controller.lista()));
    }

    private static void exclui(final ActionEvent e, final FornecedorBean fornecedor) throws ClassNotFoundException,
            HeadlessException, SQLException {
        if (controller.exclui(fornecedor)) {
            JOptionPane.showMessageDialog(null, "Fornecedor excluído", "Fornecedor excluído",
                    JOptionPane.INFORMATION_MESSAGE);
            fornecedorTable.setModel(new FornecedorTableModel(controller.lista()));
        }
    }
}

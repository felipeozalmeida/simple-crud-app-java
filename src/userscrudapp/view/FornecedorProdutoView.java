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

import userscrudapi.controller.FornecedorProdutoController;
import userscrudapi.model.bean.FornecedorProdutoBean;
import userscrudapp.viewmodel.FornecedorProdutoTableModel;

/**
 *
 * @author Felipe Almeida
 */
public class FornecedorProdutoView {

    private static FornecedorProdutoController controller = null;
    private static JFrame fornecedorProdutoFrame;
    private static JTable fornecedorProdutoTable;
    private static JScrollPane fornecedorProdutoScrollPane;
    private static JPanel fornecedorProdutoPanel;
    private static JPanel fornecedorProdutoInseriPanel;

    public static void main() throws ClassNotFoundException, SQLException {
        // Configuração
        FornecedorProdutoView.controller = new FornecedorProdutoController();
        fornecedorProdutoFrame = new JFrame("Fornecedor X Produto");
        fornecedorProdutoFrame.setBounds(30, 30, 600, 450);
        fornecedorProdutoFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Bootstrap da aplicação
        lista();
    }

    private static void lista() throws ClassNotFoundException, SQLException {
        fornecedorProdutoTable = new JTable(new FornecedorProdutoTableModel(controller.lista()));
        fornecedorProdutoTable.setAutoCreateRowSorter(true);
        fornecedorProdutoTable.getTableHeader().setBackground(Color.BLACK);
        fornecedorProdutoTable.getTableHeader().setForeground(Color.YELLOW);
        fornecedorProdutoTable.addMouseListener(new MouseInputAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    JTable targetTable = (JTable) e.getSource();
                    int rowIndex = targetTable.rowAtPoint(e.getPoint());
                    if (rowIndex != -1) {
                        int id = (int) targetTable.getModel().getValueAt(rowIndex, 0);
                        FornecedorProdutoBean fornecedorProduto = null;
                        try {
                            fornecedorProduto = new FornecedorProdutoBean(id);
                            try {
                                busca(fornecedorProduto);
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

        fornecedorProdutoScrollPane = new JScrollPane(fornecedorProdutoTable);
        fornecedorProdutoScrollPane.setPreferredSize(new Dimension(580, 430));

        fornecedorProdutoPanel = new JPanel();
        fornecedorProdutoPanel.add(fornecedorProdutoScrollPane);

        @SuppressWarnings("serial")
        JButton fornecedorProdutoInseriButton = new JButton(new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                try {
                    inseri(e);
                } catch (ClassNotFoundException | SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });
        fornecedorProdutoInseriButton.setText("Adicionar");

        @SuppressWarnings("serial")
        JButton fornecedorProdutoAlteraButton = new JButton(new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                int rowIndex = fornecedorProdutoTable.getSelectedRow();
                if (rowIndex != -1) {
                    int id = (int) fornecedorProdutoTable.getModel().getValueAt(rowIndex, 0);
                    int idFornecedor = (int) fornecedorProdutoTable.getModel().getValueAt(rowIndex, 1);
                    int idProduto = (int) fornecedorProdutoTable.getModel().getValueAt(rowIndex, 2);
                    FornecedorProdutoBean fornecedorProduto = null;
                    try {
                        fornecedorProduto = new FornecedorProdutoBean(id, idFornecedor, idProduto);
                        try {
                            altera(e, fornecedorProduto);
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
        fornecedorProdutoAlteraButton.setText("Editar");

        @SuppressWarnings("serial")
        JButton fornecedorProdutoExcluiButton = new JButton(new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                int rowIndex = fornecedorProdutoTable.getSelectedRow();
                if (rowIndex != -1) {
                    int id = (int) fornecedorProdutoTable.getModel().getValueAt(rowIndex, 0);
                    int idFornecedor = (int) fornecedorProdutoTable.getModel().getValueAt(rowIndex, 1);
                    int idProduto = (int) fornecedorProdutoTable.getModel().getValueAt(rowIndex, 2);
                    FornecedorProdutoBean fornecedorProduto;
                    try {
                        fornecedorProduto = new FornecedorProdutoBean(id, idFornecedor, idProduto);
                        try {
                            exclui(e, fornecedorProduto);
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
        fornecedorProdutoExcluiButton.setText("Excluir");

        fornecedorProdutoInseriPanel = new JPanel();
        fornecedorProdutoInseriPanel.add(fornecedorProdutoInseriButton);
        fornecedorProdutoInseriPanel.add(fornecedorProdutoAlteraButton);
        fornecedorProdutoInseriPanel.add(fornecedorProdutoExcluiButton);

        fornecedorProdutoFrame.add(fornecedorProdutoInseriPanel, BorderLayout.NORTH);
        fornecedorProdutoFrame.add(fornecedorProdutoPanel, BorderLayout.CENTER);
        fornecedorProdutoFrame.setVisible(true);
    }

    private static void busca(FornecedorProdutoBean fornecedorProduto) throws ClassNotFoundException, SQLException {
        FornecedorProdutoBean fornecedorProdutoBusca = controller.busca(fornecedorProduto);
        JOptionPane.showMessageDialog(null, fornecedorProdutoBusca, "Visualizar fornecedor X produto",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private static void inseri(ActionEvent e) throws ClassNotFoundException, SQLException {
        int idFornecedor = Integer.parseInt(JOptionPane.showInputDialog(null, "IdFornecedor", "Cadastrar fornecedor X produto",
                JOptionPane.INFORMATION_MESSAGE));
        int idProduto = Integer.parseInt(JOptionPane.showInputDialog(null, "IdProduto", "Cadastrar fornecedor X produto",
                JOptionPane.INFORMATION_MESSAGE));
        FornecedorProdutoBean fornecedorProdutoInseri = controller.inseri(new FornecedorProdutoBean(0, idFornecedor, idProduto));
        JOptionPane.showMessageDialog(null, fornecedorProdutoInseri, "Fornecedor X produto cadastrado",
                JOptionPane.INFORMATION_MESSAGE);
        fornecedorProdutoTable.setModel(new FornecedorProdutoTableModel(controller.lista()));
    }

    private static void altera(ActionEvent e, FornecedorProdutoBean fornecedorProduto) throws ClassNotFoundException, SQLException {
        fornecedorProduto.setIdFornecedor(Integer.parseInt((String) JOptionPane.showInputDialog(null, "IdFornecedor",
                "Alterar fornecedor X produto", JOptionPane.INFORMATION_MESSAGE, null, null, fornecedorProduto.getIdFornecedor())));
        fornecedorProduto.setIdProduto(
                Integer.parseInt((String) JOptionPane.showInputDialog(null, "IdProduto", "Alterar fornecedor X produto",
                        JOptionPane.INFORMATION_MESSAGE, null, null, fornecedorProduto.getIdProduto())));
        FornecedorProdutoBean fornecedorProdutoAltera = controller.altera(fornecedorProduto);
        JOptionPane.showMessageDialog(null, fornecedorProdutoAltera, "Fornecedor X produto alterado",
                JOptionPane.INFORMATION_MESSAGE);
        fornecedorProdutoTable.setModel(new FornecedorProdutoTableModel(controller.lista()));
    }

    private static void exclui(ActionEvent e, FornecedorProdutoBean fornecedorProduto) throws ClassNotFoundException, HeadlessException, SQLException {
        if (controller.exclui(fornecedorProduto)) {
            JOptionPane.showMessageDialog(null, "Fornecedor X produto excluído", "Fornecedor X produto excluído", JOptionPane.INFORMATION_MESSAGE);
            fornecedorProdutoTable.setModel(new FornecedorProdutoTableModel(controller.lista()));
        }
    }
}

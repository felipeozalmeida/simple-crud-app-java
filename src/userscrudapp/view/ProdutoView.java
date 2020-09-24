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

import userscrudapi.controller.ProdutoController;
import userscrudapi.model.bean.ProdutoBean;
import userscrudapp.viewmodel.ProdutoTableModel;

/**
 *
 * @author Felipe Almeida
 */
public class ProdutoView {

    private static ProdutoController controller = null;
    private static JFrame produtoFrame;
    private static JTable produtoTable;
    private static JScrollPane produtoScrollPane;
    private static JPanel produtoPanel;
    private static JPanel produtoInseriPanel;

    public static void main() throws ClassNotFoundException, SQLException {
        // Configuração
        ProdutoView.controller = new ProdutoController();
        produtoFrame = new JFrame("Produtos");
        produtoFrame.setBounds(30, 30, 600, 450);
        produtoFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Bootstrap da aplicação
        lista();
    }

    private static void lista() throws SQLException {
        produtoTable = new JTable(new ProdutoTableModel(controller.lista()));
        produtoTable.setAutoCreateRowSorter(true);
        produtoTable.getTableHeader().setBackground(Color.BLACK);
        produtoTable.getTableHeader().setForeground(Color.YELLOW);
        produtoTable.addMouseListener(new MouseInputAdapter() {
            public void mousePressed(final MouseEvent e) {
                if (e.getClickCount() == 2) {
                    final JTable targetTable = (JTable) e.getSource();
                    final int rowIndex = targetTable.rowAtPoint(e.getPoint());
                    if (rowIndex != -1) {
                        final int id = (int) targetTable.getModel().getValueAt(rowIndex, 0);
                        final ProdutoBean produto = new ProdutoBean(id);
                        try {
                            busca(produto);
                        } catch (final SQLException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                    }
                }
            };
        });

        produtoScrollPane = new JScrollPane(produtoTable);
        produtoScrollPane.setPreferredSize(new Dimension(580, 430));

        produtoPanel = new JPanel();
        produtoPanel.add(produtoScrollPane);

        @SuppressWarnings("serial")
        final JButton produtoInseriButton = new JButton(new AbstractAction() {
            public void actionPerformed(final ActionEvent e) {
                try {
                    inseri(e);
                } catch (final SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });
        produtoInseriButton.setText("Adicionar");

        @SuppressWarnings("serial")
        final JButton produtoAlteraButton = new JButton(new AbstractAction() {
            public void actionPerformed(final ActionEvent e) {
                final int rowIndex = produtoTable.getSelectedRow();
                if (rowIndex != -1) {
                    final int id = (int) produtoTable.getModel().getValueAt(rowIndex, 0);
                    final String cod = (String) produtoTable.getModel().getValueAt(rowIndex, 1);
                    final String nome = (String) produtoTable.getModel().getValueAt(rowIndex, 2);
                    final int valor = (int) produtoTable.getModel().getValueAt(rowIndex, 3);
                    final int quant = (int) produtoTable.getModel().getValueAt(rowIndex, 4);
                    final ProdutoBean produto = new ProdutoBean(id, cod, nome, valor, quant);
                    try {
                        altera(e, produto);
                    } catch (final SQLException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }
            }
        });
        produtoAlteraButton.setText("Editar");

        @SuppressWarnings("serial")
        final JButton produtoExcluiButton = new JButton(new AbstractAction() {
            public void actionPerformed(final ActionEvent e) {
                final int rowIndex = produtoTable.getSelectedRow();
                if (rowIndex != -1) {
                    final int id = (int) produtoTable.getModel().getValueAt(rowIndex, 0);
                    final String cod = (String) produtoTable.getModel().getValueAt(rowIndex, 1);
                    final String nome = (String) produtoTable.getModel().getValueAt(rowIndex, 2);
                    final int valor = (int) produtoTable.getModel().getValueAt(rowIndex, 3);
                    final int quant = (int) produtoTable.getModel().getValueAt(rowIndex, 4);
                    final ProdutoBean produto = new ProdutoBean(id, cod, nome, valor, quant);
                    try {
                        exclui(e, produto);
                    } catch (HeadlessException | SQLException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }
            }
        });
        produtoExcluiButton.setText("Excluir");

        produtoInseriPanel = new JPanel();
        produtoInseriPanel.add(produtoInseriButton);
        produtoInseriPanel.add(produtoAlteraButton);
        produtoInseriPanel.add(produtoExcluiButton);

        produtoFrame.add(produtoInseriPanel, BorderLayout.NORTH);
        produtoFrame.add(produtoPanel, BorderLayout.CENTER);
        produtoFrame.setVisible(true);
    }

    private static void busca(final ProdutoBean produto) throws SQLException {
        final ProdutoBean produtoBusca = controller.busca(produto);
        JOptionPane.showMessageDialog(null, produtoBusca, "Visualizar produto", JOptionPane.INFORMATION_MESSAGE);
    }

    private static void inseri(final ActionEvent e) throws SQLException {
        final String cod = JOptionPane.showInputDialog(null, "Código", "Cadastrar produto",
                JOptionPane.INFORMATION_MESSAGE);
        final String nome = JOptionPane.showInputDialog(null, "Nome", "Cadastrar produto",
                JOptionPane.INFORMATION_MESSAGE);
        final int valor = Integer.parseInt(JOptionPane.showInputDialog(null, "Valor", "Cadastrar produto",
                JOptionPane.INFORMATION_MESSAGE));
        final int quant = Integer.parseInt(JOptionPane.showInputDialog(null, "Quantidade", "Cadastrar produto",
                JOptionPane.INFORMATION_MESSAGE));
        final ProdutoBean produtoInseri = controller.inseri(new ProdutoBean(0, cod, nome, valor, quant));
        JOptionPane.showMessageDialog(null, produtoInseri, "Produto cadastrado", JOptionPane.INFORMATION_MESSAGE);
        produtoTable.setModel(new ProdutoTableModel(controller.lista()));
    }

    private static void altera(final ActionEvent e, final ProdutoBean produto) throws SQLException {
        produto.setCod((String) JOptionPane.showInputDialog(null, "Código", "Alterar produto",
                JOptionPane.INFORMATION_MESSAGE, null, null, produto.getCod()));
        produto.setNome((String) JOptionPane.showInputDialog(null, "Nome", "Alterar produto",
                JOptionPane.INFORMATION_MESSAGE, null, null, produto.getNome()));
        produto.setValor(Integer.parseInt((String) JOptionPane.showInputDialog(null, "Valor",
                "Alterar produto", JOptionPane.INFORMATION_MESSAGE, null, null, produto.getValor())));
        produto.setQuant(Integer.parseInt((String) JOptionPane.showInputDialog(null, "Quantidade",
                "Alterar produto", JOptionPane.INFORMATION_MESSAGE, null, null, produto.getQuant())));
        final ProdutoBean produtoAltera = controller.altera(produto);
        JOptionPane.showMessageDialog(null, produtoAltera, "Produto alterado", JOptionPane.INFORMATION_MESSAGE);
        produtoTable.setModel(new ProdutoTableModel(controller.lista()));
    }

    private static void exclui(final ActionEvent e, final ProdutoBean produto) throws HeadlessException, SQLException {
        if (controller.exclui(produto)) {
            JOptionPane.showMessageDialog(null, "Produto excluído", "Produto excluído",
                    JOptionPane.INFORMATION_MESSAGE);
            produtoTable.setModel(new ProdutoTableModel(controller.lista()));
        }
    }
}

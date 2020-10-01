package userscrudapp.viewmodel;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

import userscrudapi.model.bean.FornecedorProdutoBean;

@SuppressWarnings("serial")
public class FornecedorProdutoTableModel extends AbstractTableModel {

    private static final String[] COLUMNS = { "ID", "ID do Fornecedor", "ID do Produto", "Nome do Fornecedor",
            "Nome do Produto" };
    private static ArrayList<FornecedorProdutoBean> fornecedoresProdutos;

    public FornecedorProdutoTableModel(ArrayList<FornecedorProdutoBean> fornecedoresProdutos) {
        FornecedorProdutoTableModel.fornecedoresProdutos = fornecedoresProdutos;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return getValueAt(0, columnIndex).getClass();
    }

    @Override
    public int getColumnCount() {
        return COLUMNS.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return COLUMNS[columnIndex];
    }

    @Override
    public int getRowCount() {
        return fornecedoresProdutos == null ? 0 : fornecedoresProdutos.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object temp = null;
        switch (columnIndex) {
            case 0:
                temp = fornecedoresProdutos.get(rowIndex).getId();
                break;
            case 1:
                temp = fornecedoresProdutos.get(rowIndex).getIdFornecedor();
                break;
            case 2:
                temp = fornecedoresProdutos.get(rowIndex).getIdProduto();
                break;
            case 3:
                try {
                    temp = fornecedoresProdutos.get(rowIndex).getFornecedor().getNome();
                } catch (ClassNotFoundException | SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                break;
            case 4:
                try {
                    temp = fornecedoresProdutos.get(rowIndex).getProduto().getNome();
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                break;
            default: break;
        }
        return temp;
    }
}

package userscrudapp.viewmodel;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import userscrudapi.model.bean.ProdutoBean;

@SuppressWarnings("serial")
public class ProdutoTableModel extends AbstractTableModel {

    private static final String[] COLUMNS = {"ID", "Código", "Nome", "Valor", "Qtde"};
    private static ArrayList<ProdutoBean> produtos;

    public ProdutoTableModel(final ArrayList<ProdutoBean> produtos) {
        ProdutoTableModel.produtos = produtos;
    }

    @Override
    public Class<?> getColumnClass(final int columnIndex) {
        return getValueAt(0, columnIndex).getClass();
    }

    @Override
    public int getColumnCount() {
        return COLUMNS.length;
    }

    @Override
    public String getColumnName(final int columnIndex) {
        return COLUMNS[columnIndex];
    }

    @Override
    public int getRowCount() {
        return produtos == null ? 0 : produtos.size();
    }

    @Override
    public Object getValueAt(final int rowIndex, final int columnIndex) {
        Object temp = null;
        switch (columnIndex) {
            case 0: temp = produtos.get(rowIndex).getId(); break;
            case 1: temp = produtos.get(rowIndex).getCod(); break;
            case 2: temp = produtos.get(rowIndex).getNome(); break;
            case 3: temp = produtos.get(rowIndex).getValor(); break;
            case 4: temp = produtos.get(rowIndex).getQuant(); break;
            default: break;
        }
        return temp;
    }

}

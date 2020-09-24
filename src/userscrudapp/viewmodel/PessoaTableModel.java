package userscrudapp.viewmodel;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

import userscrudapi.model.bean.PessoaBean;

@SuppressWarnings("serial")
public class PessoaTableModel extends AbstractTableModel {

    private static final String[] COLUMNS = {"ID", "Nome", "Cargo"};
    private static ArrayList<PessoaBean> pessoas;

    public PessoaTableModel(ArrayList<PessoaBean> pessoas) {
        PessoaTableModel.pessoas = pessoas;
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
        return pessoas == null ? 0 : pessoas.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object temp = null;
        switch (columnIndex) {
            case 0: temp = pessoas.get(rowIndex).getId(); break;
            case 1: temp = pessoas.get(rowIndex).getNome(); break;
            case 2: temp = pessoas.get(rowIndex).getCargo(); break;
            default: break;
        }
        return temp;
    }
}

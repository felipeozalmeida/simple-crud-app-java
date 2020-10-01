package userscrudapp.viewmodel;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import userscrudapi.model.bean.FornecedorBean;

@SuppressWarnings("serial")
public class FornecedorTableModel extends AbstractTableModel {

    private static final String[] COLUMNS = { "ID", "ID do Contato", "CNPJ", "Endereço", "Nome", "Nome do Contato" };
    private static ArrayList<FornecedorBean> fornecedores;

    public FornecedorTableModel(final ArrayList<FornecedorBean> fornecedores) {
        FornecedorTableModel.fornecedores = fornecedores;
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
        return fornecedores == null ? 0 : fornecedores.size();
    }

    @Override
    public Object getValueAt(final int rowIndex, final int columnIndex) {
        Object temp = null;
        switch (columnIndex) {
            case 0:
                temp = fornecedores.get(rowIndex).getId();
                break;
            case 1:
                temp = fornecedores.get(rowIndex).getIdPessoa();
                break;
            case 2:
                temp = fornecedores.get(rowIndex).getCnpj();
                break;
            case 3:
                temp = fornecedores.get(rowIndex).getEndereco();
                break;
            case 4:
                temp = fornecedores.get(rowIndex).getNome();
                break;
            case 5:
                try {
                    temp = fornecedores.get(rowIndex).getPessoa().getNome();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                break;
            default: break;
        }
        return temp;
    }

}

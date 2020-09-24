package userscrudapp.viewmodel;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

import userscrudapp.model.bean.UsuarioBean;

@SuppressWarnings("serial")
public class UsuarioTableModel extends AbstractTableModel {

    private static final String[] COLUMNS = {"ID", "Login", "Senha", "Status", "Tipo"};
    private static ArrayList<UsuarioBean> usus;

    public UsuarioTableModel(ArrayList<UsuarioBean> usus) {
        UsuarioTableModel.usus = usus;
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
        return usus == null ? 0 : usus.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object temp = null;
        switch (columnIndex) {
            case 0: temp = usus.get(rowIndex).getId(); break;
            case 1: temp = usus.get(rowIndex).getLogin(); break;
            case 2: temp = usus.get(rowIndex).getSenha(); break;
            case 3: temp = usus.get(rowIndex).getStatus(); break;
            case 4: temp = usus.get(rowIndex).getTipo(); break;
            default: break;
        }
        return temp;
    }
}

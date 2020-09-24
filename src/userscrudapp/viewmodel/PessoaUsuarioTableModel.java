package userscrudapp.viewmodel;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

import userscrudapp.model.bean.PessoaUsuarioBean;

@SuppressWarnings("serial")
public class PessoaUsuarioTableModel extends AbstractTableModel {

    private static final String[] COLUMNS = { "ID", "ID da Pessoa", "ID do Usuário", "Nome da Pessoa",
            "Tipo do Usuário" };
    private static ArrayList<PessoaUsuarioBean> pessoasUsuarios;

    public PessoaUsuarioTableModel(ArrayList<PessoaUsuarioBean> pessoasUsuarios) {
        PessoaUsuarioTableModel.pessoasUsuarios = pessoasUsuarios;
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
        return pessoasUsuarios == null ? 0 : pessoasUsuarios.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object temp = null;
        switch (columnIndex) {
            case 0:
                temp = pessoasUsuarios.get(rowIndex).getId();
                break;
            case 1:
                temp = pessoasUsuarios.get(rowIndex).getIdPessoa();
                break;
            case 2:
                temp = pessoasUsuarios.get(rowIndex).getIdUsuario();
                break;
            case 3:
                try {
                    temp = pessoasUsuarios.get(rowIndex).getPessoa().getNome();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                break;
            case 4:
                try {
                    temp = pessoasUsuarios.get(rowIndex).getUsuario().getTipo();
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

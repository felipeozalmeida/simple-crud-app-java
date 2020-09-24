package userscrudapp.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import userscrudapp.model.bean.ProdutoBean;
import userscrudapp.model.dao.ProdutoDao;

public class ProdutoController {
    private static ProdutoDao Dao = null;

    public ProdutoController() throws ClassNotFoundException, SQLException {
        ProdutoController.Dao = new ProdutoDao();
    }

    public ArrayList<ProdutoBean> lista() throws SQLException {
        return Dao.lista();
    }

    public ProdutoBean busca(final ProdutoBean pessoa) throws SQLException {
        return Dao.busca(pessoa);
    }

    public ProdutoBean inseri(final ProdutoBean pessoa) throws SQLException {
        return Dao.inseri(pessoa);
    }

    public ProdutoBean altera(final ProdutoBean pessoa) throws SQLException {
        return Dao.altera(pessoa);
    }

    public boolean exclui(final ProdutoBean pessoa) throws SQLException {
        return Dao.exclui(pessoa);
    }
}

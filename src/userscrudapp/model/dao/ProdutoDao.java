package userscrudapp.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import userscrudapp.config.DbConnection;
import userscrudapp.model.bean.ProdutoBean;

public class ProdutoDao {

    private static Connection DbConnection = null;
    private static ArrayList<ProdutoBean> produtos = new ArrayList<>();

    public ProdutoDao() throws ClassNotFoundException, SQLException {
        DbConnection = new DbConnection().getConnection();
    }

    public ArrayList<ProdutoBean> lista() throws SQLException {
        final ArrayList<ProdutoBean> produtosLista = new ArrayList<>();

        final String sql = "SELECT * FROM produtos";
        final PreparedStatement stmt = DbConnection.prepareStatement(sql);
        final ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            produtosLista.add(new ProdutoBean(
                rs.getInt(1),
                rs.getString(2),
                rs.getString(3),
                rs.getInt(4),
                rs.getInt(5)));
        }
        produtos = produtosLista;

        rs.close();
        stmt.close();
        return produtos;
    }

    public ProdutoBean busca(final ProdutoBean produto) throws SQLException {
        ProdutoBean produtoBusca = null;

        final String sql = "SELECT * FROM produtos WHERE id = ?";
        final PreparedStatement stmt = DbConnection.prepareStatement(sql);
        stmt.setInt(1, produto.getId());
        final ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            produtoBusca = new ProdutoBean(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getInt(4),
                    rs.getInt(5));
        }

        rs.close();
        stmt.close();
        return produtoBusca;
    }

    public ProdutoBean inseri(final ProdutoBean produto) throws SQLException {
        final String sql = "INSERT INTO produtos (cod, nome, valor, quant) VALUES (?, ?, ?, ?);";
        final PreparedStatement stmt = DbConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, produto.getCod());
        stmt.setString(2, produto.getNome());
        stmt.setInt(3, produto.getValor());
        stmt.setInt(4, produto.getQuant());
        stmt.executeUpdate();
        final ResultSet rs = stmt.getGeneratedKeys();
        if (rs.next())
            produto.setId(rs.getInt(1));

        rs.close();
        stmt.close();
        return produto;
    }

    public ProdutoBean altera(final ProdutoBean produto) throws SQLException {
        final String sql = "UPDATE produtos SET cod = ?, nome = ?, valor = ?, quant = ? WHERE id = ?";
        final PreparedStatement stmt = DbConnection.prepareStatement(sql);
        stmt.setString(1, produto.getCod());
        stmt.setString(2, produto.getNome());
        stmt.setInt(3, produto.getValor());
        stmt.setInt(4, produto.getQuant());
        stmt.setInt(5, produto.getId());
        stmt.execute();
        stmt.close();
        return produto;
    }

    public boolean exclui(final ProdutoBean produto) throws SQLException {
        final String sql = "DELETE FROM produtos WHERE id = ?";
        final PreparedStatement stmt = DbConnection.prepareStatement(sql);
        stmt.setInt(1, produto.getId());
        stmt.execute();
        stmt.close();
        return true;
    }
}

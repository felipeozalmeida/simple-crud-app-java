/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userscrudapp.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import userscrudapp.config.DbConnection;
import userscrudapp.model.bean.PessoaBean;

/**
 *
 * @author Felipe Almeida
 */
public class PessoaDao {

    private static Connection DbConnection = null;
    private static ArrayList<PessoaBean> pessoas = new ArrayList<>();
    // private static int autoIncrement = 4;

    public PessoaDao() throws ClassNotFoundException, SQLException {
        DbConnection = new DbConnection().getConnection();
        // pessoas.add(new PessoaBean(1, "Teste 1", "Gerente"));
        // pessoas.add(new PessoaBean(2, "Teste 2", "Supervisor"));
        // pessoas.add(new PessoaBean(3, "Teste 3", "Técnico"));
    }

    public ArrayList<PessoaBean> lista() throws SQLException {
        final ArrayList<PessoaBean> pessoasLista = new ArrayList<>();

        final String sql = "SELECT * FROM pessoas";
        final PreparedStatement stmt = DbConnection.prepareStatement(sql);
        final ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            pessoasLista.add(new PessoaBean(rs.getInt(1), rs.getString(2), rs.getString(3)));
        }
        pessoas = pessoasLista;

        rs.close();
        stmt.close();
        return pessoas;

        // MOCK
        // return pessoas;
    }

    public PessoaBean busca(final PessoaBean pessoa) throws SQLException {
        PessoaBean pessoaBusca = null;

        final String sql = "SELECT * FROM pessoas WHERE id = ?";
        final PreparedStatement stmt = DbConnection.prepareStatement(sql);
        stmt.setInt(1, pessoa.getId());
        final ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            pessoaBusca = new PessoaBean(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3));
        }

        rs.close();
        stmt.close();
        return pessoaBusca;

        // MOCK
        // return pessoas.stream().filter(u -> u.getId() ==
        // pessoa.getId()).findFirst().get();
    }

    public PessoaBean inseri(final PessoaBean pessoa) throws SQLException {
        final String sql = "INSERT INTO pessoas (nome, cargo) VALUES (?, ?)";
        final PreparedStatement stmt = DbConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, pessoa.getNome());
        stmt.setString(2, pessoa.getCargo());
        stmt.executeUpdate();
        final ResultSet rs = stmt.getGeneratedKeys();
        if (rs.next())
            pessoa.setId(rs.getInt(1));

        rs.close();
        stmt.close();
        return pessoa;

        // MOCK
        // pessoa.setId(autoIncrement++);
        // pessoas.add(pessoa);
        // return pessoa;
    }

    public PessoaBean altera(final PessoaBean pessoa) throws SQLException {
        final String sql = "UPDATE pessoas SET nome = ?, cargo = ? WHERE id = ?";
        final PreparedStatement stmt = DbConnection.prepareStatement(sql);
        stmt.setString(1, pessoa.getNome());
        stmt.setString(2, pessoa.getCargo());
        stmt.setInt(3, pessoa.getId());
        stmt.execute();
        stmt.close();
        return pessoa;

        // MOCK
        // final PessoaBean pessoaAntigo = pessoas.stream().filter(u -> u.getId() == pessoa.getId()).findFirst().get();
        // pessoas.set(pessoas.indexOf(pessoaAntigo), pessoa);
        // return pessoa;
    }

    public boolean exclui(final PessoaBean pessoa) throws SQLException {
        final String sql = "DELETE FROM pessoas WHERE id = ?";
        final PreparedStatement stmt = DbConnection.prepareStatement(sql);
        stmt.setInt(1, pessoa.getId());
        stmt.execute();
        stmt.close();
        return true;

        // MOCK
        // return pessoas.remove(pessoa);
    }
}

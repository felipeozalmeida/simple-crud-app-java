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
import userscrudapp.model.bean.PessoaUsuarioBean;

/**
 *
 * @author Felipe Almeida
 */
public class PessoaUsuarioDao {

    private static Connection DbConnection = null;
    private static ArrayList<PessoaUsuarioBean> pessoasUsuarios = new ArrayList<>();
    // private static int autoIncrement = 4;

    public PessoaUsuarioDao() throws ClassNotFoundException, SQLException {
        DbConnection = new DbConnection().getConnection();
        // pessoasUsuarios.add(new PessoaUsuarioBean(1, 1, 1));
        // pessoasUsuarios.add(new PessoaUsuarioBean(2, 2, 2));
        // pessoasUsuarios.add(new PessoaUsuarioBean(3, 3, 3));
    }

    public ArrayList<PessoaUsuarioBean> lista() throws ClassNotFoundException, SQLException {
        final ArrayList<PessoaUsuarioBean> pessoasUsuariosLista = new ArrayList<>();

        final String sql = "SELECT * FROM usuarios_pessoas";
        final PreparedStatement stmt = DbConnection.prepareStatement(sql);
        final ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            pessoasUsuariosLista.add(new PessoaUsuarioBean(rs.getInt(1), rs.getInt(2), rs.getInt(3)));
        }
        pessoasUsuarios = pessoasUsuariosLista;

        rs.close();
        stmt.close();
        return pessoasUsuarios;

        // MOCK
        // return pessoasUsuarios;
    }

    public PessoaUsuarioBean busca(PessoaUsuarioBean pessoaUsuario) throws ClassNotFoundException, SQLException {
        PessoaUsuarioBean pessoaUsuarioBusca = null;

        final String sql = "SELECT * FROM usuarios_pessoas WHERE id = ?";
        final PreparedStatement stmt = DbConnection.prepareStatement(sql);
        stmt.setInt(1, pessoaUsuario.getId());
        final ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            pessoaUsuarioBusca = new PessoaUsuarioBean(
                    rs.getInt(1),
                    rs.getInt(2),
                    rs.getInt(3));
        }

        rs.close();
        stmt.close();
        return pessoaUsuarioBusca;

        // MOCK
        // return pessoasUsuarios.stream().filter(u -> u.getId() == pessoaUsuario.getId()).findFirst().get();
    }

    public PessoaUsuarioBean inseri(PessoaUsuarioBean pessoaUsuario) throws SQLException {
        final String sql = "INSERT INTO usuarios_pessoas (idUsuario, idPessoa) VALUES (?, ?)";
        final PreparedStatement stmt = DbConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        stmt.setInt(1, pessoaUsuario.getIdUsuario());
        stmt.setInt(2, pessoaUsuario.getIdPessoa());
        stmt.executeUpdate();
        final ResultSet rs = stmt.getGeneratedKeys();
        if (rs.next())
            pessoaUsuario.setId(rs.getInt(1));

        rs.close();
        stmt.close();
        return pessoaUsuario;

        // MOCK
        // pessoaUsuario.setId(autoIncrement++);
        // pessoasUsuarios.add(pessoaUsuario);
        // return pessoaUsuario;
    }

    public PessoaUsuarioBean altera(PessoaUsuarioBean pessoaUsuario) throws SQLException {
        final String sql = "UPDATE usuarios_pessoas SET idUsuario = ?, idPessoa = ? WHERE id = ?";
        final PreparedStatement stmt = DbConnection.prepareStatement(sql);
        stmt.setInt(1, pessoaUsuario.getIdUsuario());
        stmt.setInt(2, pessoaUsuario.getIdPessoa());
        stmt.setInt(3, pessoaUsuario.getId());
        stmt.execute();
        stmt.close();
        return pessoaUsuario;

        // MOCK
        // PessoaUsuarioBean pessoaUsuarioAntigo = pessoasUsuarios
        //         .stream().filter(u -> u.getId() == pessoaUsuario.getId()).findFirst().get();
        // pessoasUsuarios.set(pessoasUsuarios.indexOf(pessoaUsuarioAntigo), pessoaUsuario);
        // return pessoaUsuario;
    }

    public boolean exclui(PessoaUsuarioBean pessoaUsuario) throws SQLException {
        final String sql = "DELETE FROM usuarios_pessoas WHERE id = ?";
        final PreparedStatement stmt = DbConnection.prepareStatement(sql);
        stmt.setInt(1, pessoaUsuario.getId());
        stmt.execute();
        stmt.close();
        return true;

        // MOCK
        // return pessoasUsuarios.remove(pessoaUsuario);
    }
}

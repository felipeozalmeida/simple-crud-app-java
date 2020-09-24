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
import userscrudapp.model.bean.UsuarioBean;

/**
 *
 * @author Felipe Almeida
 */
public class UsuarioDao {

    private static Connection DbConnection = null;
    private static ArrayList<UsuarioBean> usus = new ArrayList<>();
    // private static int autoIncrement = 4;

    public UsuarioDao() throws ClassNotFoundException, SQLException {
        DbConnection = new DbConnection().getConnection();

        // MOCK
        // usus.add(new UsuarioBean(1, "teste1", "senha1", "ATIVO", "ADM"));
        // usus.add(new UsuarioBean(2, "teste2", "senha2", "ATIVO", "PADRÃO"));
        // usus.add(new UsuarioBean(3, "teste3", "senha3", "ATIVO", "PADRÃO"));
    }

    public UsuarioBean valida(UsuarioBean usu) throws SQLException {
        UsuarioBean usuValida = null;

        String sql = "SELECT * FROM usuarios WHERE login = ? AND senha = ?";
        PreparedStatement stmt = DbConnection.prepareStatement(sql);
        stmt.setString(1, usu.getLogin());
        stmt.setString(2, usu.getSenha());
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            usuValida = new UsuarioBean(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5));
        }

        rs.close();
        stmt.close();
        return usuValida;

        // MOCK
        // usu.setId(25);
        // usu.setStatus("ATIVO");
        // usu.setTipo("ADM");
        // return usu;
    }

    public ArrayList<UsuarioBean> lista() throws SQLException {
        ArrayList<UsuarioBean> ususLista = new ArrayList<>();

        String sql = "SELECT * FROM usuarios";
        PreparedStatement stmt = DbConnection.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            ususLista.add(new UsuarioBean(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5)));
        }
        usus = ususLista;

        rs.close();
        stmt.close();
        return usus;
    }

    public UsuarioBean busca(UsuarioBean usu) throws SQLException {
        UsuarioBean usuBusca = null;

        String sql = "SELECT * FROM usuarios WHERE id = ?";
        PreparedStatement stmt = DbConnection.prepareStatement(sql);
        stmt.setInt(1, usu.getId());
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            usuBusca = new UsuarioBean(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5));
        }

        rs.close();
        stmt.close();
        return usuBusca;

        // MOCK
        // return usus.stream().filter(u -> u.getId() == usu.getId()).findFirst().get();
    }

    public UsuarioBean inseri(UsuarioBean usu) throws SQLException {
        String sql = "INSERT INTO usuarios (login, senha, status, tipo) VALUES (?, ?, ?, ?)";
        PreparedStatement stmt = DbConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, usu.getLogin());
        stmt.setString(2, usu.getSenha());
        stmt.setString(3, usu.getStatus());
        stmt.setString(4, usu.getTipo());
        stmt.executeUpdate();
        ResultSet rs = stmt.getGeneratedKeys();
        if (rs.next()) { usu.setId(rs.getInt(1)); }

        rs.close();
        stmt.close();
        return usu;

        // MOCK
        // usu.setId(autoIncrement++);
        // usus.add(usu);
        // return usu;
    }

    public UsuarioBean altera(UsuarioBean usu) throws SQLException {
        String sql = "UPDATE usuarios SET login = ?, senha = ?, status = ?, tipo = ? WHERE id = ?";
        PreparedStatement stmt = DbConnection.prepareStatement(sql);
        stmt.setString(1, usu.getLogin());
        stmt.setString(2, usu.getSenha());
        stmt.setString(3, usu.getStatus());
        stmt.setString(4, usu.getTipo());
        stmt.setInt(5, usu.getId());
        stmt.execute();
        stmt.close();
        return usu;

        // MOCK
        // UsuarioBean usuAntigo = usus.stream().filter(u -> u.getId() == usu.getId()).findFirst().get();
        // usus.set(usus.indexOf(usuAntigo), usu);
        // return usu;
    }

    public boolean exclui(UsuarioBean usu) throws SQLException {
        String sql = "DELETE FROM usuarios WHERE id = ?";
        PreparedStatement stmt = DbConnection.prepareStatement(sql);
        stmt.setInt(1, usu.getId());
        stmt.execute();
        stmt.close();
        return true;

        // MOCK
        // return usus.remove(usu);
    }
}

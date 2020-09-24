/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userscrudapp.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import userscrudapp.model.bean.UsuarioBean;
import userscrudapp.model.dao.UsuarioDao;

/**
 *
 * @author Felipe Almeida
 */
public class UsuarioController {

    private static UsuarioDao Dao = null;

    public UsuarioController() throws ClassNotFoundException, SQLException {
        Dao = new UsuarioDao();
    }

    public UsuarioBean valida(UsuarioBean usu) throws SQLException {
        return Dao.valida(usu);
    }

    public ArrayList<UsuarioBean> lista() throws SQLException {
        return Dao.lista();
    }

    public UsuarioBean busca(UsuarioBean usu) throws SQLException {
        return Dao.busca(usu);
    }

    public UsuarioBean inseri(UsuarioBean usu) throws SQLException {
        return Dao.inseri(usu);
    }

    public UsuarioBean altera(UsuarioBean usu) throws SQLException {
        return Dao.altera(usu);
    }

    public boolean exclui(UsuarioBean usu) throws SQLException {
        return Dao.exclui(usu);
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userscrudapp.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import userscrudapp.model.bean.PessoaUsuarioBean;
import userscrudapp.model.dao.PessoaUsuarioDao;

/**
 *
 * @author Felipe Almeida
 */
public class PessoaUsuarioController {

    private static PessoaUsuarioDao Dao = null;

    public PessoaUsuarioController() throws ClassNotFoundException, SQLException {
        PessoaUsuarioController.Dao = new PessoaUsuarioDao();
    }

    public ArrayList<PessoaUsuarioBean> lista() throws ClassNotFoundException, SQLException {
        return Dao.lista();
    }

    public PessoaUsuarioBean busca(PessoaUsuarioBean pessoaUsuario) throws ClassNotFoundException, SQLException {
        return Dao.busca(pessoaUsuario);
    }

    public PessoaUsuarioBean inseri(PessoaUsuarioBean pessoaUsuario) throws SQLException {
        return Dao.inseri(pessoaUsuario);
    }

    public PessoaUsuarioBean altera(PessoaUsuarioBean pessoaUsuario) throws SQLException {
        return Dao.altera(pessoaUsuario);
    }

    public boolean exclui(PessoaUsuarioBean pessoaUsuario) throws SQLException {
        return Dao.exclui(pessoaUsuario);
    }
}

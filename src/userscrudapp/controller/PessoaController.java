/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userscrudapp.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import userscrudapp.model.bean.PessoaBean;
import userscrudapp.model.dao.PessoaDao;

/**
 *
 * @author Felipe Almeida
 */
public class PessoaController {

    private static PessoaDao Dao = null;

    public PessoaController() throws ClassNotFoundException, SQLException {
        PessoaController.Dao = new PessoaDao();
    }

    public ArrayList<PessoaBean> lista() throws SQLException {
        return Dao.lista();
    }

    public PessoaBean busca(PessoaBean pessoa) throws SQLException {
        return Dao.busca(pessoa);
    }

    public PessoaBean inseri(PessoaBean pessoa) throws SQLException {
        return Dao.inseri(pessoa);
    }

    public PessoaBean altera(PessoaBean pessoa) throws SQLException {
        return Dao.altera(pessoa);
    }

    public boolean exclui(PessoaBean pessoa) throws SQLException {
        return Dao.exclui(pessoa);
    }
}

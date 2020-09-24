package userscrudapp.model.bean;

import java.sql.SQLException;

import userscrudapp.model.dao.PessoaDao;
import userscrudapp.model.dao.UsuarioDao;

public class PessoaUsuarioBean {

    private static PessoaDao pDao = null;
    private static UsuarioDao uDao = null;

    private int id;
    private int idPessoa;
    private int idUsuario;

    public PessoaUsuarioBean(final int id) throws ClassNotFoundException, SQLException {
        this.id = id;
        PessoaUsuarioBean.pDao = new PessoaDao();
        PessoaUsuarioBean.uDao = new UsuarioDao();
    }

    public PessoaUsuarioBean(final int id, final int idPessoa, final int idUsuario)
            throws ClassNotFoundException, SQLException {
        this.id = id;
        this.idPessoa = idPessoa;
        this.idUsuario = idUsuario;
        PessoaUsuarioBean.pDao = new PessoaDao();
        PessoaUsuarioBean.uDao = new UsuarioDao();
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public int getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(final int idPessoa) {
        this.idPessoa = idPessoa;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(final int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public PessoaBean getPessoa() throws SQLException {
        return PessoaUsuarioBean.pDao.busca(new PessoaBean(idPessoa));
    }

    public UsuarioBean getUsuario() throws SQLException {
        return PessoaUsuarioBean.uDao.busca(new UsuarioBean(idUsuario));
    }

    @Override
    public String toString() {
        return "PessoaUsuarioBean [id=" + id + ", idPessoa=" + idPessoa + ", idUsuario=" + idUsuario + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + idPessoa;
        result = prime * result + idUsuario;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PessoaUsuarioBean other = (PessoaUsuarioBean) obj;
        if (id != other.id)
            return false;
        if (idPessoa != other.idPessoa)
            return false;
        if (idUsuario != other.idUsuario)
            return false;
        return true;
    }

}

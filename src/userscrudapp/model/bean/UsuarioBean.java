/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userscrudapp.model.bean;

/**
 *
 * @author Felipe Almeida
 */
public class UsuarioBean {

    private int id;
    private String login;
    private String senha;
    private String status;
    private String tipo;

    public UsuarioBean(int id) {
        this.id = id;
    }

    public UsuarioBean(int id, String login, String senha, String status, String tipo) {
        this.id = id;
        this.login = login;
        this.senha = senha;
        this.status = status;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", login=" + login + ", senha=" + senha + ", status=" + status + ", tipo=" + tipo + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (null == obj || getClass() != obj.getClass()) return false;
        UsuarioBean usu = (UsuarioBean) obj;
        return (
            this.getId() == usu.getId() &&
            this.getLogin() == usu.getLogin() &&
            this.getSenha() == usu.getSenha() &&
            this.getStatus() == usu.getStatus() &&
            this.getTipo() == usu.getTipo()
        );
    }

}

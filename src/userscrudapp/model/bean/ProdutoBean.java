package userscrudapp.model.bean;

public class ProdutoBean {

    private int id;
    private String cod;
    private String nome;
    private int valor;
    private int quant;

    public ProdutoBean(final int id) {
        this.id = id;
    }

    public ProdutoBean(final int id, final String cod, final String nome, final int valor, final int quant) {
        this.id = id;
        this.cod = cod;
        this.nome = nome;
        this.valor = valor;
        this.quant = quant;
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(final String cod) {
        this.cod = cod;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(final String nome) {
        this.nome = nome;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(final int valor) {
        this.valor = valor;
    }

    public int getQuant() {
        return quant;
    }

    public void setQuant(final int quant) {
        this.quant = quant;
    }

    @Override
    public String toString() {
        return "ProdutoBean [cod=" + cod + ", id=" + id + ", nome=" + nome + ", quant=" + quant + ", valor=" + valor
                + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((cod == null) ? 0 : cod.hashCode());
        result = prime * result + id;
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        result = prime * result + quant;
        result = prime * result + valor;
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final ProdutoBean other = (ProdutoBean) obj;
        if (cod == null) {
            if (other.cod != null)
                return false;
        } else if (!cod.equals(other.cod))
            return false;
        if (id != other.id)
            return false;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        if (quant != other.quant)
            return false;
        if (valor != other.valor)
            return false;
        return true;
    }

}

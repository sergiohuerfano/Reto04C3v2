package iSergio.Reto04C3.model;

public class CountClient {

    private Long total;
    private Cliente cliente;

    public CountClient(Long total, Cliente cliente) {
        this.total = total;
        this.cliente = cliente;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Cliente getClient() {
        return cliente;
    }

    public void setClient(Cliente cliente) {
        this.cliente = cliente;
    }
}

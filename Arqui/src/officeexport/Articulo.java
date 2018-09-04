package officeexport;

import java.util.Date;

public class Articulo {
    private int codArticulo;
    private String nombreArticulo;
    private int valorUni;
    private int cantidadUnidades;
    private int montoTotal;
    private String estado;
    private Date fechaDesp;

    public Articulo(int codArticulo, String nombreArticulo, int valorUni, int cantidadUnidades, int montoTotal, String estado, Date fechaDesp) {
        this.codArticulo = codArticulo;
        this.nombreArticulo = nombreArticulo;
        this.valorUni = valorUni;
        this.cantidadUnidades = cantidadUnidades;
        this.montoTotal = montoTotal;
        this.estado = estado;
        this.fechaDesp = fechaDesp;
    }

    public int getCodArticulo() {
        return codArticulo;
    }

    public void setCodArticulo(int codArticulo) {
        this.codArticulo = codArticulo;
    }

    public String getNombreArticulo() {
        return nombreArticulo;
    }

    public void setNombreArticulo(String nombreArticulo) {
        this.nombreArticulo = nombreArticulo;
    }

    public int getValorUni() {
        return valorUni;
    }

    public void setValorUni(int valorUni) {
        this.valorUni = valorUni;
    }

    public int getCantidadUnidades() {
        return cantidadUnidades;
    }

    public void setCantidadUnidades(int cantidadUnidades) {
        this.cantidadUnidades = cantidadUnidades;
    }

    public int getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(int montoTotal) {
        this.montoTotal = montoTotal;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechaDesp() {
        return fechaDesp;
    }

    public void setFechaDesp(Date fechaDesp) {
        this.fechaDesp = fechaDesp;
    }
    
    
}

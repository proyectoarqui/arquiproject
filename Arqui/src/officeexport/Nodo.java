package officeexport;

public class Nodo {
    private Despacho data;
    private Nodo padre;
    private Nodo hojaIzquierda;
    private Nodo hojaDerecha;

    public Nodo(Despacho data) {
        this.data = data;
    }

    public Despacho getData() {
        return data;
    }

    public void setData(Despacho data) {
        this.data = data;
    }

    public Nodo getPadre() {
        return padre;
    }

    public void setPadre(Nodo padre) {
        this.padre = padre;
    }

    public Nodo getHojaIzquierda() {
        return hojaIzquierda;
    }

    public void setHojaIzquierda(Nodo hojaIzquierda) {
        this.hojaIzquierda = hojaIzquierda;
    }

    public Nodo getHojaDerecha() {
        return hojaDerecha;
    }

    public void setHojaDerecha(Nodo hojaDerecha) {
        this.hojaDerecha = hojaDerecha;
    }
    
    
    
    
    
    
}

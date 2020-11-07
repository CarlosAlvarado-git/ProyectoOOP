public class Cantidad {
    Producto producto; 
    int cantidad; 

    public Cantidad(Producto p, int c){
        this.producto = p; 
        this.cantidad = c; 
    }
    public int getCantidad(){
        return this.cantidad; 
    }
    public void setCantidad(int c){
        this.cantidad = c; 
    } 
}

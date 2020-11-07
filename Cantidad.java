public class Cantidad {
    Producto producto; 
    int cantidad; 

    public Cantidad(Producto p, int c){
        this.producto = p; 
        this.cantidad = c; 
    }
    public Producto getProducto(){
        return this.producto; 
    }
    public int getCantidad(){
        return this.cantidad; 
    }
    public void setCantidad(int c){
        this.cantidad = c; 
    } 
}

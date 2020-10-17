public class Cantidad {
    Producto producto; 
    int cantidad; 

    public Cantidad(Producto p, int c){
        this.producto = p; 
        this.cantidad = c; 
    }
    //getCampo()
    public int getCantidad(){
        return this.cantidad; 
    }
    //setCampo()
    public void setCantidad(int c){
        this.cantidad = c; 
    } 
}

public class Cantidad {
    String Idproducto; 
    int cantidad; 
    int NoBodega;

    public Cantidad(String p, int c, int NoB){
        this.Idproducto = p; 
        this.cantidad = c; 
        this.NoBodega = NoB;
    }
    public String getIdProducto(){
        return this.Idproducto; 
    }
    public int getBodega(){
        return this.NoBodega; 
    }
    public int getCantidad(){
        return this.cantidad; 
    }
    public void changeCantidad(int c){
        this.cantidad = c; 
    } 
}
import java.util.ArrayList;

public class Bodega {
    static Cantidad cant; 
    String nombre; 
    ArrayList<Cantidad> productos; 
    //Traslados

    public Bodega(String nombre){
        this.nombre = nombre; 
        this.productos = new ArrayList<Cantidad>();  
    }
    public void newProducto(Producto p, int c){
        cant = new Cantidad(p, c);
        this.productos.add(cant); 
        
    }
    public void Ventas(int unidades){

    }
    public void Compra(int unidades){

    }
    //traslados 
    public void traslado(int unidades){

    }

}

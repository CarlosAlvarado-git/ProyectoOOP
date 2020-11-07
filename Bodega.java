import java.util.ArrayList;

public class Bodega {
    static Cantidad cant; 
    static Producto pro;
    String nombre; //nombre de la bodega 
    ArrayList<Cantidad> productos; 
    //Traslados

    public Bodega(String nombre){
        this.nombre = nombre; 
        this.productos = new ArrayList<Cantidad>();  
    }
    public int buscar(int id){
        int direccion = -1; 
        for(int i = 0; i < productos.size() && direccion == -1; i++){
            cant = productos.get(i); 
            pro = cant.getProducto(); 
            if (id == pro.getId()){
                direccion = i; 
            }
        }
        return direccion; 
    }
    public void newProducto(Producto p, int c){
        cant = new Cantidad(p, c);
        this.productos.add(cant); 
    }
    public void Venta(int id, int unidades){
        int direccion = buscar(id); 
        if(direccion == -1){
            System.out.println("El producto con id " + id + "no existe."); 
        }
        else{
            cant = productos.get(direccion); 
            if (cant.getCantidad() < unidades){
                System.out.println("No hay suficiente existencia para realizar la venta");
                System.out.println("Existencia actual: " + cant.getCantidad());
            }
            else{
                cant.setCantidad(cant.getCantidad() - unidades);
            }
        }
    }
    public void Compra(int id, int unidades){
        int direccion = buscar(id); 
        if(direccion == -1){
            System.out.println("El producto con id " + id + "no existe."); 
        }
        else{
            cant.setCantidad(cant.getCantidad() + unidades);
            System.out.println("Compra realizada con éxito.");
        }
    }
    //traslados 
    public void traslado(int unidades){
        System.out.println("0"); 
    }

}

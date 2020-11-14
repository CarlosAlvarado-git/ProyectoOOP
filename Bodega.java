import java.util.LinkedList;

public class Bodega {
    static Cantidad cant; 
    static Producto pro;
    int NoBodega; //nombre de la bodega 
    LinkedList<Producto> Productos = new LinkedList<Producto>();// con id de la bodega
    LinkedList<Cantidad> Cantidades = new LinkedList<Cantidad>();// con id de la bodega
    //Traslados

    public Bodega(int id){
        this.NoBodega = id; 
        //this.productos = new ArrayList<Cantidad>();  
    }
    public void LlenarBodega(LinkedList<Cantidad> CanMySQL, LinkedList<Producto> ProMySQL){
        for(int i = 0; i < CanMySQL.size(); i++){
            if(CanMySQL.get(i).getBodega() == this.NoBodega){
                this.Cantidades.add(CanMySQL.get(i));
                this.Productos.add(ProMySQL.get(i));
            }
        }
    }
    // No sé si lo vamos a usar 
    public int buscar(String id){
        int direccion = -1; 
        for(int i = 0; i < this.Cantidades.size() && direccion == -1; i++){
            this.cant = this.Cantidades.get(i); 
            this.pro = this.cant.getProducto(); 
            if (id.equals(this.pro.getId())){
                direccion = i; 
            }
        }
        return direccion; 
    }
    public void newProducto(Producto p, int c){// comprar producto nuevo
        this.cant = new Cantidad(p, c);
        this.productos.add(cant); 
    }
    public void Venta(String id, int unidades){
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
    public void Compra(String id, int unidades){
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

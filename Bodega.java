import java.util.LinkedList;

public class Bodega {
    static Cantidad cant; 
    //static Producto pro;
    int NoBodega; //nombre de la bodega 
    LinkedList<Producto> Productos;// con id de la bodega
    LinkedList<Cantidad> Cantidades;// con id de la bodega
    //Traslados

    public Bodega(int id){
        this.NoBodega = id; 
        this.Productos = new LinkedList<Producto>();
        this.Cantidades = new LinkedList<Cantidad>();
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
    public int buscar(String id){
        int direccion = -1; 
        for(int i = 0; i < this.Cantidades.size() && direccion == -1; i++){
            cant = this.Cantidades.get(i); 
            if (id.equals(this.cant.getIdProducto())){
                direccion = i; 
            }
        }
        return direccion; 
    }
    public void nuevoProducto(Producto p, int c){// comprar producto nuevo
        int bar = buscar(p.getId());
        if(bar == -1){
            Cantidad np = new Cantidad(p.getId(), c,this.NoBodega);
            //p.setBodega(this.NoBodega);
            System.out.println("----------ANTES DE ASIGNAR-------------");
            for(int i = 0; i < this.Productos.size();i++ ){
                System.out.println(this.Productos.get(i).getId());
            }
            System.out.println("-----------------------");
            for(int i = 0; i < this.Cantidades.size();i++ ){
                System.out.println(this.Cantidades.get(i).getIdProducto());
            }
            this.Cantidades.add(np); 
            this.Productos.add(p);
            System.out.println("-----------DESPUÉS DE ASIGNAR------------");
            for(int i = 0; i < this.Productos.size();i++ ){
                System.out.println(this.Productos.get(i).getId());
            }
            System.out.println("-----------------------");
            for(int i = 0; i < this.Cantidades.size();i++ ){
                System.out.println(this.Cantidades.get(i).getIdProducto());
            }
        }
        else{
            
        }
        
    }
    public void Venta(String id, int unidades){
        int direccion = buscar(id); 
        if(direccion == -1){
            System.out.println("El producto con id " + id + "no existe."); 
        }
        else{
            this.cant = this.Cantidades.get(direccion); 
            if (this.cant.getCantidad() < unidades){
                System.out.println("No hay suficiente existencia para realizar la venta");
                System.out.println("Existencia actual: " + cant.getCantidad());
            }
            else{
                this.cant.changeCantidad(cant.getCantidad() - unidades);
            }
        }
    }
    public void Compra(String id, int unidades){
        int direccion = buscar(id); 
        cant = this.Cantidades.get(direccion);
        if(direccion == -1){
            System.out.println("El producto con id " + id + "no existe."); 
        }
        else{
            cant.changeCantidad(cant.getCantidad() + unidades);
            System.out.println("Compra realizada con éxito.");
        }
    }
    //traslados 
    public void traslado(int unidades){
        System.out.println("0"); 
    }

    public int getsizeProductos()
    {
        return this.Productos.size();
    }

    public String getIdProducto(int i)
    {
        return Productos.get(i).getId();
    }

    public String getNombreProducto(int i)
    {
        return Productos.get(i).getNombre();
    }

    public String getCantidadProducto(int i)
    {
        return String.valueOf(Cantidades.get(i).getCantidad());
    }

}

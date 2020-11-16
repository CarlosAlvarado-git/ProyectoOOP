import java.util.LinkedList;

public class Bodega {
    static Cantidad cant; 
    //static Producto pro;
    int NoBodega; //nombre de la bodega 
    LinkedList<Percusion> PercusionProductos;//

    LinkedList<Cantidad> Cantidades;// con id de la bodega
    //Traslados

    public Bodega(int id){
        this.NoBodega = id; 
        this.PercusionProductos = new LinkedList<Producto>();
        this.Cantidades = new LinkedList<Cantidad>();
        //this.productos = new ArrayList<Cantidad>();  
    }
    public void LlenarBodega(LinkedList<Cantidad> CanMySQL, LinkedList<Percusion> PerMySQL){
        for(int i = 0; i < CanMySQL.size(); i++){
            if(CanMySQL.get(i).getBodega() == this.NoBodega){
                this.Cantidades.add(CanMySQL.get(i));
            }
        }
        for(int i = 0; i < PerMySQL.size(); i++){
            for(int x = 0; x < this.Cantidades.size(); x++){
                if(PerMySQL.get(i).getId().equals(this.Cantidades.get(x).getIdProducto())){
                    this.PercusionProductos.add(PerMySQL.get(i));
                }
            }
        }
        //agregrar los de cuerdas y vientos
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
    public void nuevoProducto(Percusion p, int c){// comprar producto nuevo percusion
        int bar = buscar(p.getId());
        if(bar == -1){
            Cantidad np = new Cantidad(p.getId(), c,this.NoBodega);
            p.setBodega(this.NoBodega);
            this.Cantidades.add(np); 
            this.PercusionProductos.add(p);
        }
        else{
            //aviso
       }
        
    }
    public void nuevoProducto(Cuerdas p, int c){
        int bar = buscar(p.getId());
        if(bar == -1){
            Cantidad np = new Cantidad(p.getId(), c,this.NoBodega);
            p.setBodega(this.NoBodega);
            this.Cantidades.add(np); 
            //this.PercusionProductos.add(p); el de cuerdas
        }
        else{
            //aviso
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
    //-----------------
    public int getsizePerscusion()
    {
        return this.PercusionProductos.size();
    }

    public String getIdProductoPercusion(int i)
    {
        return this.PercusionProductos.get(i).getId();
    }

    public String getNombreProductoPercusion(int i)
    {
        return this.PercusionProductos.get(i).getNombre();
    }

    public String getCantidadProductoPerscusion(int i)
    {
        int pos = 0;
        for(int x = 0; x < this.Cantidades.size(); x++){
            if(this.Cantidades.get(x).getIdProducto().equals(this.PercusionProductos.get(i).getId())){
                pos = x;
                break;
            }
        }
        return String.valueOf(this.Cantidades.get(pos).getCantidad());
    }
    //otras iguales solo que con viento y cuerdas

}

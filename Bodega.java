import java.util.LinkedList;

public class Bodega {
    static Cantidad cant; 
    //static Producto pro;
    int NoBodega; //nombre de la bodega 
    LinkedList<Percusion> PercusionProductos;//
    LinkedList<Viento> VientoProductos;
    LinkedList<Cuerdas> CuerdasProductos;

    LinkedList<Cantidad> Cantidades;// con id de la bodega
    //Traslados

    public Bodega(int id){
        this.NoBodega = id; 
        this.PercusionProductos = new LinkedList<Percusion>();
        this.VientoProductos = new LinkedList<Viento>();
        this.CuerdasProductos = new LinkedList<Cuerdas>();
        //nuevas listas 
        this.Cantidades = new LinkedList<Cantidad>();
        //this.productos = new ArrayList<Cantidad>();  
    }
    public void LlenarBodega(LinkedList<Cantidad> CanMySQL, LinkedList<Percusion> PerMySQL, LinkedList<Viento> VientoMySQL, LinkedList<Cuerdas> CuerdasMySQL){
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
        for(int i = 0; i < VientoMySQL.size(); i++){
            for(int x = 0; x < this.Cantidades.size(); x++){
                if(VientoMySQL.get(i).getId().equals(this.Cantidades.get(x).getIdProducto())){
                    this.VientoProductos.add(VientoMySQL.get(i));
                }
            }
        }
        for(int i = 0; i < CuerdasMySQL.size(); i++){
            for(int x = 0; x < this.Cantidades.size(); x++){
                if(CuerdasMySQL.get(i).getId().equals(this.Cantidades.get(x).getIdProducto())){
                    this.CuerdasProductos.add(CuerdasMySQL.get(i));
                }
            }
        }
    }
    public int buscar(String id){
        int direccion = -1; 
        for(int i = 0; i < this.Cantidades.size() && direccion == -1; i++){
            cant = this.Cantidades.get(i); 
            if (id.equals(cant.getIdProducto())){
                direccion = i; 
            }
        }
        return direccion; 
    }
    /*
        Esta es la sección para comprar produtos
    */
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
            this.CuerdasProductos.add(p); 
        }
        else{
            //aviso
       }
    }
    public void nuevoProducto(Viento p, int c){
        int bar = buscar(p.getId());
        if(bar == -1){
            Cantidad np = new Cantidad(p.getId(), c,this.NoBodega);
            p.setBodega(this.NoBodega);
            this.Cantidades.add(np); 
            this.VientoProductos.add(p);
        }
        else{
            //aviso
       }
    }
    public void Venta(String id, int unidades){
        int direccion = buscar(id); 
        if(direccion == -1){
            //aviso
        }
        else{
            cant = this.Cantidades.get(direccion); 
            if (cant.getCantidad() < unidades){
                //aviso
                //System.out.println("Existencia actual: " + cant.getCantidad());
            }
            else{
                cant.changeCantidad(cant.getCantidad() - unidades);
            }
        }
    }
    public void Compra(String id, int unidades){
        int direccion = buscar(id); 
        if(direccion == -1){
            //aviso
        }
        else{
            cant = this.Cantidades.get(direccion);
            cant.changeCantidad(cant.getCantidad() + unidades);
        }
    }
    public void Movimiento(String id, int unidades, Bodega b){
        int direccion = b.buscar(id); 
        if(direccion == -1){
            
        }
        else{
            cant = b.Cantidades.get(direccion);
            cant.changeCantidad(cant.getCantidad() + unidades);
        }
    }
    //traslados 
    public void traslado(Srting id, int unidades, int BoNueva, LinkedList<Bodega> bodegass){
        int direccion = buscar(id);
        if(direccion == -1){
            //aviso
        }
        else{
            cant = this.Cantidades.get(direccion); 
            if (cant.getCantidad() < unidades){
                //aviso
                //System.out.println("Existencia actual: " + cant.getCantidad());
            }
            else{
                cant.changeCantidad(cant.getCantidad() - unidades);
                bodegass.get(BoNueva-1).Movimiento(id, unidades, bodegass.get(BoNueva));
            }
        }
    }
    /*
        GETS POR PRODUCTO XD
    */
    // GET's PARA PERCUSION
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
    // GET's PARA CUERDAS
    public int getsizeCuerdas()
    {
        return this.CuerdasProductos.size();
    }

    public String getIdProductoCuerdas(int i)
    {
        return this.CuerdasProductos.get(i).getId();
    }

    public String getNombreProductoCuerdas(int i)
    {
        return this.CuerdasProductos.get(i).getNombre();
    }
    public String getCantidadProductoCuerdas(int i)
    {
        int pos = 0;
        for(int x = 0; x < this.Cantidades.size(); x++){
            if(this.Cantidades.get(x).getIdProducto().equals(this.CuerdasProductos.get(i).getId())){
                pos = x;
                break;
            }
        }
        return String.valueOf(this.Cantidades.get(pos).getCantidad());
    }
    // GET's PARA VIENTO
    public int getsizeViento()
    {
        return this.VientoProductos.size();
    }

    public String getIdProductoViento(int i)
    {
        return this.VientoProductos.get(i).getId();
    }

    public String getNombreProductoViento(int i)
    {
        return this.VientoProductos.get(i).getNombre();
    }
    public String getCantidadProductoViento(int i)
    {
        int pos = 0;
        for(int x = 0; x < this.Cantidades.size(); x++){
            if(this.Cantidades.get(x).getIdProducto().equals(this.VientoProductos.get(i).getId())){
                pos = x;
                break;
            }
        }
        return String.valueOf(this.Cantidades.get(pos).getCantidad());
    }
}

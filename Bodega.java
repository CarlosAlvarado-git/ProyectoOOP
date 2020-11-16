import java.util.LinkedList;

public class Bodega {
    static Cantidad cant; 
    //static Producto pro;
    int NoBodega; //nombre de la bodega 
    LinkedList<Percusion> PercusionProductos = new LinkedList<Percusion>();//
    LinkedList<Viento> VientoProductos = new LinkedList<Viento>();
    LinkedList<Cuerdas> CuerdasProductos = new LinkedList<Cuerdas>();
    LinkedList<Cantidad> Cantidades = new LinkedList<Cantidad>();
    //Traslados

    public Bodega(int id){
        this.NoBodega = id;  
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
    public void Movimiento(String id, int unidades, Bodega bNueva, Bodega bAnterior){
        int direccion = bNueva.buscar(id); 
        if(direccion == -1){
            int buscarAnterior = 0;
            int pos = 0;
            for(int i = 0; i < bAnterior.PercusionProductos.size(); i++){
                if(id.equals(bAnterior.PercusionProductos.get(i).getId())){
                    buscarAnterior = 1;
                    pos = i;
                    break;
                }
            }
            for(int i = 0; i < bAnterior.VientoProductos.size(); i++){
                if(id.equals(bAnterior.VientoProductos.get(i).getId())){
                    buscarAnterior = 2;
                    pos = i;
                    break;
                }
            }
            for(int i = 0; i < bAnterior.CuerdasProductos.size(); i++){
                if(id.equals(bAnterior.CuerdasProductos.get(i).getId())){
                    buscarAnterior = 3;
                    pos = i;
                    break;
                }
            }
            if(buscarAnterior == 1){
                Percusion per = bNueva.retornarPercusion(bAnterior, pos);
                Cantidad np = new Cantidad(per.getId(), unidades, bNueva.NoBodega);
                bNueva.Cantidades.add(np);
                bNueva.PercusionProductos.add(per);
                //percusion
            }
            else if(buscarAnterior == 2){
                //viento
            }
            else{
                //cuerdas
            }
        }
        else{
            cant = bNueva.Cantidades.get(direccion);
            cant.changeCantidad(cant.getCantidad() + unidades);
        }
    }
    //traslados 
    public void traslado(String id, int unidades, int BoNueva, LinkedList<Bodega> bodegass){
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
                bodegass.get(BoNueva-1).Movimiento(id, unidades, bodegass.get(BoNueva-1), this);
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
    public Percusion retornarPercusion(Bodega b, int pos){
        String id = b.getIdProductoPercusion(pos);
        double pre = b.PercusionProductos.get(pos).getPrecio();
        String mar = b.PercusionProductos.get(pos).getMarca();
        String mode = b.PercusionProductos.get(pos).getModelo();
        String nom = b.PercusionProductos.get(pos).getNombre();
        String mat = b.PercusionProductos.get(pos).getMaterial();
        int peso =  b.PercusionProductos.get(pos).getPeso();
        String percu =  b.PercusionProductos.get(pos).getPercutor();
        String vibra =  b.PercusionProductos.get(pos).getVibrante();
        Percusion per = new Percusion(id, pre, mar, mode, nom, mat, peso, percu, vibra);
        return per;
    }
}
/**
    FUNCIONES PARA RETORNAR LOS VALORES
 */


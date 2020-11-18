import java.util.LinkedList;

public class Bodega {
    static Cantidad cant; 
    //static Producto pro;
    int NoBodega; //nombre de la bodega 
    int noVENTAS;
    int noCOMPRAS;
    LinkedList<Percusion> PercusionProductos = new LinkedList<Percusion>();//
    LinkedList<Viento> VientoProductos = new LinkedList<Viento>();
    LinkedList<Cuerdas> CuerdasProductos = new LinkedList<Cuerdas>();
    LinkedList<Cantidad> Cantidades = new LinkedList<Cantidad>();
    //Traslados

    public Bodega(int id, int v, int c){
        this.NoBodega = id; 
        this.noCOMPRAS = c;
        this.noVENTAS = v; 
    }
    public int getNoBodega(){
        return this.NoBodega;
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
    public void nuevoProducto(Percusion p, int c, MySQL db){// comprar producto nuevo percusion
        int bar = buscar(p.getId());
        if(bar == -1){
            Cantidad np = new Cantidad(p.getId(), c,this.NoBodega);
            p.setBodega(this.NoBodega);
            this.Cantidades.add(np); 
            this.PercusionProductos.add(p);
            db.escribirInstrumento_Percusion(p,np);
            this.noCOMPRAS = this.noCOMPRAS + c;
            db.escribirBodegas(this);
        }
        else{
            //aviso
       }
        
    }
    public void nuevoProducto(Cuerdas p, int c, MySQL db){
        int bar = buscar(p.getId());
        if(bar == -1){
            Cantidad np = new Cantidad(p.getId(), c,this.NoBodega);
            p.setBodega(this.NoBodega);
            this.Cantidades.add(np); 
            this.CuerdasProductos.add(p);
            db.escribirInstrumento_Cuerda(p,np); 
            this.noCOMPRAS = this.noCOMPRAS + c;
            db.escribirBodegas(this);
        }
        else{
            //aviso
       }
    }
    public void nuevoProducto(Viento p, int c, MySQL db){
        int bar = buscar(p.getId());
        if(bar == -1){
            Cantidad np = new Cantidad(p.getId(), c,this.NoBodega);
            p.setBodega(this.NoBodega);
            this.Cantidades.add(np); 
            this.VientoProductos.add(p);
            db.escribirInstrumento_Viento(p,np);
            this.noCOMPRAS = this.noCOMPRAS + c;
            db.escribirBodegas(this);
        }
        else{
            //aviso
       }
    }
    public void Venta(String id, int unidades, MySQL db){
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
                this.noVENTAS = this.noVENTAS + unidades;
                db.escribirBodegas(this);
                db.actualizarCantidad(cant, 1);
            }
        }
    }
    public void Compra(String id, int unidades, MySQL db){
        int direccion = buscar(id); 
        if(direccion == -1){
            //aviso
        }
        else{
            cant = this.Cantidades.get(direccion);
            cant.changeCantidad(cant.getCantidad() + unidades);
            this.noCOMPRAS = this.noCOMPRAS + unidades;
            db.escribirBodegas(this);
            db.actualizarCantidad(cant, 1);
        }
    }
    public void Movimiento(String id, int unidades, Bodega bNueva, Bodega bAnterior, MySQL db){
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
                db.actualizarCantidad(np, 0);
                //percusion
            }
            else if(buscarAnterior == 2){
                //viento
                Viento vien = bNueva.retornarVientos(bAnterior, pos);
                Cantidad np = new Cantidad(vien.getId(), unidades, bNueva.NoBodega);
                bNueva.Cantidades.add(np);
                bNueva.VientoProductos.add(vien);
                db.actualizarCantidad(np, 0);
            }
            else{
                //cuerdas
                Cuerdas cuer = bNueva.retornarCuerdas(bAnterior, pos);
                Cantidad np = new Cantidad(cuer.getId(), unidades, bNueva.NoBodega);
                bNueva.Cantidades.add(np);
                bNueva.CuerdasProductos.add(cuer);
                db.actualizarCantidad(np, 0);
            }
        }
        else{
            cant = bNueva.Cantidades.get(direccion);
            cant.changeCantidad(cant.getCantidad() + unidades);
            db.actualizarCantidad(cant, 0);
        }
    }
    //traslados 
    public void traslado(String id, int unidades, int BoNueva, LinkedList<Bodega> bodegass, MySQL db){
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
                db.actualizarCantidad(cant, 1);
                bodegass.get(BoNueva-1).Movimiento(id, unidades, bodegass.get(BoNueva-1), this, db);
            }
        }
    }
    public int getVentas(){
        return this.noVENTAS;
    }
    public int getCompras(){
        return this.noCOMPRAS;
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
        double peso =  b.PercusionProductos.get(pos).getPeso();
        //
        String percu =  b.PercusionProductos.get(pos).getPercutor();
        String vibra =  b.PercusionProductos.get(pos).getVibrante();
        Percusion per = new Percusion(id, pre, mar, mode, nom, mat, peso, percu, vibra);
        return per;
    }

    
    public Viento retornarVientos(Bodega b, int pos){
        String id = b.getIdProductoViento(pos);
        double pre = b.VientoProductos.get(pos).getPrecio();
        String mar = b.VientoProductos.get(pos).getMarca();
        String mode = b.VientoProductos.get(pos).getModelo();
        String nom = b.VientoProductos.get(pos).getNombre();
        String mat = b.VientoProductos.get(pos).getMaterial();
        double peso =  b.VientoProductos.get(pos).getPeso();
        //
        int largo =  b.VientoProductos.get(pos).getLargo();
        Viento vien = new Viento(id, pre, mar, mode, nom, mat, peso, largo);
        return vien;
    }

    
    public Cuerdas retornarCuerdas(Bodega b, int pos){
        String id = b.getIdProductoCuerdas(pos);
        double pre = b.CuerdasProductos.get(pos).getPrecio();
        String mar = b.CuerdasProductos.get(pos).getMarca();
        String mode = b.CuerdasProductos.get(pos).getModelo();
        String nom = b.CuerdasProductos.get(pos).getNombre();
        String mat = b.CuerdasProductos.get(pos).getMaterial();
        double peso =  b.CuerdasProductos.get(pos).getPeso();
        //
        String tipoCuerda =  b.CuerdasProductos.get(pos).getTipoCuerdas();
        int resonancia =  b.CuerdasProductos.get(pos).getResonancia();
        int nCuerdas =  b.CuerdasProductos.get(pos).getNoCuerdas();
        Cuerdas cuerd = new Cuerdas(id, pre, mar, mode, nom, mat, peso, tipoCuerda, resonancia, nCuerdas);
        return cuerd;
    }
}
/**
    FUNCIONES PARA RETORNAR LOS VALORES
 */


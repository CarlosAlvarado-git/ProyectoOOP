public class Producto{
    String codigo; 
    double precio; 
    String marca; 
    String modelo;  
    String nombre; 
    String material;
    int peso; 
    int cantidad; 
    int bodega; 
    boolean activo; 

    public Producto(String c, double precio, String mar, String mo, String n, String mat, int p, int ca, int b, boolean a){
        this.codigo = c;
        this.precio = precio; 
        this.marca = mar;
        this.modelo = mo; 
        this.nombre = n; 
        this.material = mat;
        this.peso = p; 
        this.cantidad = ca; 
        this.bodega = b; 
        this.activo = a; 
    }
    //getCampos()
    public String getCodigo(){
        return this.codigo; 
    }
    public double getPrecio(){
        return this.precio; 
    }
    public String getMarca(){
        return this.marca; 
    }
    public String getModelo(){
        return this.modelo; 
    }
    public String getNombre(){
        return this.nombre; 
    }
    public String getMaterial(){
        return this.material; 
    }
    public int getPeso(){
        return this.peso; 
    }
    public int getCantidad(){
        return this.cantidad; 
    }
    public int getBodega(){
        return this.bodega; 
    }
    public boolean getActivo(){
        return this.activo; 
    }
    //setCampos()
    public void setCodigo(String c){
        this.codigo = c; 
    }
    public void setPrecio(double p){
        this.precio = p; 
    }
    public void setMarca(String m){
        this.marca = m; 
    }
    public void setModelo(String m){
        this.modelo = m; 
    }
    public void setNombre(String n){
        this.nombre = n; 
    }
    public void setMaterial(String m){
        this.material = m; 
    }
    public void setPeso(int p){
        this.peso = p; 
    }
    public void setCantodad(int c){
        this.cantidad = c; 
    }
    public void setBodega(int b){
        this.bodega = b; 
    }
    public void setActivo(boolean a){
        this.activo = a; 
    }
}
public class Producto{
    String id; 
    double precio; 
    String marca; 
    String modelo;  
    String nombre; 
    String material;
    double peso; 
    int bodega; 

    public Producto(String id, double precio, String mar, String mo, String n, String mat, double p){
        this.id = id;
        this.precio = precio; 
        this.marca = mar;
        this.modelo = mo; 
        this.nombre = n; 
        this.material = mat;
        this.peso = p;
    }
    //getCampos() ------------------------
    public String getId(){
        return this.id; 
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
    public double getPeso(){
        return this.peso; 
    }
    public int getBodega(){
        return this.bodega; 
    }
    //setCampos() -----------------------------
    public void setId(String id){
        this.id = id; 
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
    public void setPeso(double p){
        this.peso = p; 
    }
    public void setBodega(int b){
        this.bodega = b; 
    }
}
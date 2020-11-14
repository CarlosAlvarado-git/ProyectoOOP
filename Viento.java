public class Viento extends Producto {
    int largo; 
    
    public Viento(String c, double precio, String mar, String mo, String n, String mat, int p, int largo){
        super(c, precio, mar, mo, n, mat, p);
        this.largo =  largo; 
    }
    public void setBodega(int b){
        super.bodega = b; 
    }
    //getCampos()
    public int getLargo(){
        return this.largo; 
    }
    //setCampos()
    public void setLargo(int largo){
        this.largo = largo; 
    }
}

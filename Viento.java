public class Viento extends Producto {
    int largo; 
    
    public Viento(String c, double precio, String mar, String mo, String n, String mat, int p, int b, boolean a, int largo){
        super(c, precio, mar, mo, n, mat, p, b, a);
        this.largo =  largo; 
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
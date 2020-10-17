public class Cuerdas extends Producto {
    String tipoCuerdas; 
    boolean resonancia; 
    int noCuerdas; 

    public Cuerdas(String c, double precio, String mar, String mo, String n, String mat, int p, int b, boolean a, String tiCuerda, boolean reso, int noCuerdas){
        super(c, precio, mar, mo, n, mat, p, b, a); 
        this.tipoCuerdas = tiCuerda; 
        this.resonancia = reso; 
        this.noCuerdas =  noCuerdas; 
    }
    //getCampos()
    public String getTipoCuerdas(){
        return this.tipoCuerdas; 
    }
    public boolean getResonancia(){
        return this.resonancia; 
    }
    public int getNoCuerdas(){
        return this.noCuerdas; 
    }
    //setCampos()
    public void setTipoCuerdas(String tipo){
        this.tipoCuerdas = tipo; 
    }
    public void setResonancia(boolean re){
        this.resonancia = re; 
    }
    public void setNoCuerdas(int numero){
        this.noCuerdas = numero; 
    }
}

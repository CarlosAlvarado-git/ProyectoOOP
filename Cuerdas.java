public class Cuerdas extends Producto {
    String tipoCuerdas; 
    int resonancia; 
    int noCuerdas; 

    public Cuerdas(String c, double precio, String mar, String mo, String n, String mat, double p, String tiCuerda, int reso, int noCuerdas){
        super(c, precio, mar, mo, n, mat, p); 
        this.tipoCuerdas = tiCuerda; 
        this.resonancia = reso; 
        this.noCuerdas =  noCuerdas; 
    }
    //getCampos()
    public String getTipoCuerdas(){
        return this.tipoCuerdas; 
    }
    public int getResonancia(){
        return this.resonancia; 
    }
    public int getNoCuerdas(){
        return this.noCuerdas; 
    }
    //setCampos()
    public void setTipoCuerdas(String tipo){
        this.tipoCuerdas = tipo; 
    }
    public void setResonancia(int re){
        this.resonancia = re; 
    }
    public void setNoCuerdas(int numero){
        this.noCuerdas = numero; 
    }
}

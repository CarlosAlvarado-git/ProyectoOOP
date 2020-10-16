public class Percusion extends Producto {
    String elementoPercutor; 
    String elementoVibrante;

    public Percusion(String c, double precio, String mar, String mo, String n, String mat, int p, int b, boolean a, String percutor, String vibrante){
        super(c, precio, mar, mo, n, mat, p, b, a); 
        this.elementoPercutor = percutor; 
        this.elementoVibrante = vibrante; 
    }
    //getCampos()
    public String getPercutor(){
        return this.elementoPercutor; 
    }
    public String getVibrante(){
        return this.elementoVibrante; 
    }
    //setCampos()
    public void setPercutor(String p){
        this.elementoPercutor = p; 
    } 
    public void setVibrante(String v){
        this.elementoVibrante = v; 
    } 
}

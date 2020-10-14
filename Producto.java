public class Producto{
	String codigo;
	double precio;
	String marca;
	String nombre;
	String modelo;
	String tipoMaterial;
	int peso;
	int cantidad;
	int noBodega;
	int activo;
	public Producto(String c, double p, String ma, String n, String mo, String tm, int pe, int can, int noB){
		this.codigo = c;
		this.precio = p;
		this.marca = ma;
		this.nombre = n;
		this.modelo = mo;
		this.peso = pe;
		this.cantidad = can;
		this.noBodega = noB;
		this.activo = 1;
	}
	public getCodigo(){
		return this.codigo;
	}
	public getPrecio(){
		return this.precio;
	}
}
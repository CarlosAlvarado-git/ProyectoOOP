public class instrumentoCuerdas extends Producto{
	String tipoCuerda;
	int resonancia;
	int noCuerdas;
	public instrumentoCuerdas(String tc, int r, int nc, String co, double pr, String ma, String no, String mo, String tp, int pe, int ca, int noB){
		super(co,pr,ma,no,mo,tp,pe,ca,noB);
		this.tipoCuerda = tc;
		this.resonancia = r;
		this.noCuerdas = nc;
	}
}
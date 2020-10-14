public class instrumentoPercusion extends Producto{
	String percutor;
	String vibrante;
	public instrumentoPercusion(String per,String vib,String co, double pr, String ma, String no, String mo, String tp, int pe, int ca, int noB){
		super(co,pr,ma,no,mo,tp,pe,ca,noB);
		this.percutor = per;
		this.vibrante = vib;
	}
}
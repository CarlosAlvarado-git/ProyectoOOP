public class instrumentoViento extends Producto{
	int tamano;
	public instrumentoViento(int tam, String co, double pr, String ma, String no, String mo, String tp, int pe, int ca, int noB){
		super(co,pr,ma,no,mo,tp,pe,ca,noB);
		this.tamano = tam;
	}
}
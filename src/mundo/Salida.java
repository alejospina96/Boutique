package mundo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Salida implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String concepto;
	private double valor;
	private Date fecha;
	
	public Salida(String concepto, double valor){
		this.concepto= concepto;
		this.valor= valor;
		fecha = new Date();
	}
	public Date darFecha(){
		return fecha;
	}
	public String fechaString(){
		return new SimpleDateFormat("dd/MM/yyy").format(fecha); 
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "\n\t\tconcepto= " + concepto + ", valor= $" + valor ;
	}

	/**
	 * @return the concepto
	 */
	public String darConcepto() {
		return concepto;
	}

	/**
	 * @return the valor
	 */
	public double darValor() {
		return valor;
	}

	

	
	
	
}

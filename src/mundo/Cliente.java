package mundo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Cliente implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** @pdOid f4fed41c-1009-499f-9744-3746bd4e7bf8 */
	   private long cedula;
	   /** @pdOid d7856b47-63e5-45bf-ac66-ebb1498ae1dc */
	   private java.lang.String nombres;
	   /** @pdOid cbbc8db2-b061-47a5-9da5-09fe030d046f */
	   private java.lang.String apellidos;
	   /** @pdOid e3e0c673-c013-46f1-831a-391b9c88b8b2 */
	   private java.lang.String direccion;
	   /** @pdOid 7e3546c1-3591-47ce-8144-eaec9ef459dc */
	   private double totalCredito = 0;
	   
	   /** @pdRoleInfo migr=no name=Venta assc=clienteVentas coll=java.util.ArrayList impl=java.util.ArrayList mult=* type=Composition */
	   public java.util.ArrayList<Venta> ventas;
	   
	   
	   /** @pdGenerated default darter */
	   public java.util.ArrayList<Venta> darVentas() {
	      return ventas;
	   }


	/**
	 * @return the cedula
	 */
	public long darCedula() {
		return cedula;
	}


	/**
	 * @return the nombres
	 */
	public java.lang.String darNombres() {
		return nombres;
	}


	/**
	 * @return the apellidos
	 */
	public java.lang.String darApellidos() {
		return apellidos;
	}


	/**
	 * @return the direccion
	 */
	public java.lang.String darDireccion() {
		return direccion;
	}


	/**
	 * @return the totalCredito
	 */
	public double darTotalCredito() {
		return totalCredito;
	}


	public double pagar(double valorPagar)throws Exception {
		if(valorPagar>totalCredito)throw new Exception("El valor a pagar excede el que se debe, actualmente se debe: "+totalCredito);
		totalCredito-=valorPagar;
		return totalCredito;
	}


	/**
	 * @param cedula
	 * @param nombres
	 * @param apellidos
	 * @param direccion
	 * @param totalCredito
	 */
	public Cliente(long cedula, String nombres, String apellidos, String direccion) {
		this.cedula = cedula;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.direccion = direccion;
		this.totalCredito = 0;
		ventas = new ArrayList<Venta>();
	}
	
	public void vender(Venta v) {
		ventas.add(v);
		totalCredito+=v.darValor();
	}
	public ArrayList<Venta> ventasDesde(Date d){
		ArrayList<Venta> v = new ArrayList<Venta>();
		for (int i = 0; i < ventas.size(); i++) {
			if(ventas.get(i).darFecha().after(d))v.add(ventas.get(i));
		}
		return v;
	}
	public String toString(){
		return cedula+"\n"+nombres+" "+apellidos;
	}
	   
}

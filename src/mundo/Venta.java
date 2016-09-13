package mundo;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import reportes.Factura;

public class Venta implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** @pdOid 4e91615c-f6b7-4271-944c-0910b3292a30 */
	private long numero;
	/** @pdOid 635adba2-7654-4abd-abfa-b43f9adf91a1 */
	private double valor;
	/** @pdOid 7984e14a-9c20-43c2-a72e-bd8bc8dd016c */
	private Date fecha;
	private Cliente cliente;
	
	/**
	 * @pdRoleInfo migr=no name=Producto assc=ventaProductos
	 *             coll=java.util.ArrayList impl=java.util.ArrayList mult=*
	 *             type=Composition
	 */
	public java.util.ArrayList<Producto> productosVendidos;

	public Venta(Cliente buscarCliente) {
		fecha = new Date();
		productosVendidos = new ArrayList<Producto>();
		valor = 0;
		cliente = buscarCliente;
	}

	public Cliente darCliente() {
		return cliente;
	}

	/** @pdGenerated default darter */
	public java.util.ArrayList<Producto> darProductosVendidos() {
		return productosVendidos;
	}

	public ArrayList<String> darReferencias() {
		ArrayList<String> res = new ArrayList<String>();
		for (int i = 0; i < productosVendidos.size(); i++) {
			res.add(productosVendidos.get(i).darReferencia());
		}
		return res;
	}

	public ArrayList<String> darDescripciones() {
		ArrayList<String> res = new ArrayList<String>();
		for (int i = 0; i < productosVendidos.size(); i++) {
			res.add("nombre: " + productosVendidos.get(i).darNombre() + " marca: "
					+ productosVendidos.get(i).darMarca());
		}
		return res;
	}

	public ArrayList<Double> darPrecios() {
		ArrayList<Double> res = new ArrayList<Double>();
		for (int i = 0; i < productosVendidos.size(); i++) {
			res.add(productosVendidos.get(i).darPrecioVenta());
		}
		return res;
	}

	/**
	 * @return the numero
	 */
	public long darNumero() {
		return numero;
	}

	/**
	 * @return the valor
	 */
	public double darValor() {
		return valor;
	}

	/**
	 * @return the fecha
	 */
	public Date darFecha() {
		return fecha;
	}

	

	public void vender(Producto p) {
		productosVendidos.add(p);
		valor += p.darValorTotal();
	}

	public void generarFactura(long numFact) {
		numero = numFact;
	}

	public String toString() {
		return "\t" + fecha.toString() + " productos: " + darProductosVendidos() + "\n";
	}

	public void facturar() {
		new Factura(this);
	}
}

package mundo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import controller.ClienteNoExisteException;

public class Boutique implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public final static String COMPRA = "Compra de productos";
	private long numFact = 0;
	/** @pdOid 39e5b4cb-7f54-4a48-8b41-ce80c2b51a8f */
	private int ingresos;

	/**
	 * @pdRoleInfo migr=no name=Producto assc=boutiqueProductos
	 *             coll=java.util.ArrayList impl=java.util.ArrayList mult=*
	 *             type=Composition
	 */
	public java.util.ArrayList<Producto> productos;
	/**
	 * @pdRoleInfo migr=no name=Salida assc=boutiqueSalidas
	 *             coll=java.util.ArrayList impl=java.util.ArrayList mult=*
	 *             type=Composition
	 */
	public java.util.ArrayList<Salida> salidas;
	/**
	 * @pdRoleInfo migr=no name=Cliente assc=boutiqueClientes
	 *             coll=java.util.ArrayList impl=java.util.ArrayList mult=*
	 *             type=Composition
	 */
	public java.util.ArrayList<Cliente> clientes;

	/**
	 * @param referencia
	 * @param marca
	 * @param precioCompra
	 * @param precioVenta
	 * @param talla
	 * @param color
	 * @param tela
	 * @param cantidad
	 * @throws Exception 
	 * @pdOid 78e5a2f6-4388-4cdb-8f76-23dc5e584cac
	 */
	public void agregarProducto(String referencia, String nombre, String marca, double precioCompra, double precioVenta, String talla,
			String color, String tela, int cantidad) throws Exception {
		if(buscarProducto(referencia)!=null)throw new Exception("El producto ya existe");
		productos.add(new Producto(referencia, nombre, marca, precioCompra, precioVenta, talla, color, tela, cantidad));
		ingresos-=precioCompra;
	}

	/**
	 * 
	 */
	public Boutique() {
		numFact = 0;
		ingresos = 0;
		productos = new ArrayList<Producto>();
		salidas = new ArrayList<Salida>();
		clientes = new ArrayList<Cliente>();
	}

	/**
	 * @param referencia
	 * @pdOid 4abd2b48-267a-4a78-82eb-ca53c9b02737
	 */
	public Producto buscarProducto(String referencia) {
		for (int i = 0; i < productos.size(); i++) {
			if(productos.get(i).darReferencia().equalsIgnoreCase(referencia))return productos.get(i);
		}
		return null;
	}
	public boolean existeCliente(long cedula){
		for (int i = 0; i < clientes.size(); i++) {
			if(clientes.get(i).darCedula()==cedula)return true;
		}
		return false;
	}
	/**
	 * @param productos
	 * @param cedulaCliente
	 * @param paga
	 * @pdOid e284bc9c-2332-4531-b3ae-01b01e46b7e8
	 */
	public void venderProductos(java.util.ArrayList<Producto> productos, long cedulaCliente, boolean paga)throws ClienteNoExisteException {
		System.out.println(this.productos);
		System.out.println(clientes);
		if(!existeCliente(cedulaCliente))throw new ClienteNoExisteException();
		Venta v = new Venta(buscarCliente(cedulaCliente));
		for (int i = 0; i < productos.size(); i++) {
			venderProducto(productos.get(i).darReferencia(),productos.get(i).darCantidad());
			v.vender(productos.get(i));
			
		}
		numFact++;
		v.generarFactura(numFact);
		Cliente c = buscarCliente(cedulaCliente);
		c.vender(v);
		if(paga){
			try {
				c.pagar(v.darValor());
				ingresos+=v.darValor();
			} catch (Exception e) {
				
			}
		}
		v.facturar();
	}
	private void venderProducto(String ref,int cantidad){
		Producto p =buscarProducto(ref);
		int restantes = p.venderCantidad(cantidad);
		if(restantes==0)productos.remove(p);
	}
	/**
	 * @param cedulaCliente
	 * @param valorPagar
	 * @throws ClienteNoExisteException 
	 * @pdOid 3b420942-d3af-44f8-9bbb-d5409b0efaaa
	 */
	public void pagarCredito(long cedulaCliente, double valorPagar) throws Exception, ClienteNoExisteException {
		if(!existeCliente(cedulaCliente))throw new ClienteNoExisteException();
		buscarCliente(cedulaCliente).pagar(valorPagar);
	}
	public Cliente buscarCliente(long cedula){
		for (int i = 0; i < clientes.size(); i++) {
			if(clientes.get(i).darCedula()==cedula)return clientes.get(i);
		}
		return null;
	}
	/**
	 * @param valor
	 * @param concepto
	 * @pdOid 0e9bfef0-36cd-4553-988a-9acb2b890732
	 */
	public void registrarSalida( String concepto,double valor) {
		salidas.add(new Salida(concepto, valor));
		ingresos-=valor;
	}

	/** @pdOid 5e49a0ce-de70-41bb-8279-58ef054e4d97 */
	public void generarReporteInventario() {
		// TODO: implement
	}

	/**
	 * @param fecha
	 * @pdOid c683def5-d8df-4cdc-bc89-8150306d72b5
	 */
	public void generarReporteClientes(Date fecha) {
		// TODO: implement
	}

	/** @pdOid 7768b60b-6d26-4d98-a3c3-741d944f70d1 */
	public void generarReporteCliente() {
		// TODO: implement
	}

	/** @pdGenerated default getter */
	public java.util.ArrayList<Producto> darProductos() {

		return productos;
	}

	public long darNumFact() {
		return numFact;
	}

	public int dartIngresos() {
		return ingresos;
	}

	public void agregarCliente(HashMap<String, String> hm) {
		clientes.add(new Cliente(Long.parseLong(hm.get("cedula")), hm.get("nombres"), hm.get("apellidos"), hm.get("direccion")));
	}

	public double darCreditoCliente(long cedula) {
		// TODO Auto-generated method stub
		return buscarCliente(cedula).darTotalCredito();
	}

	

}

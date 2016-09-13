package controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import mundo.Boutique;
import mundo.Producto;
import mundo.Venta;

public class ControllerReport {
	ArrayList<Producto> productos;
	ArrayList<Venta> ventas;

	public ControllerReport(ArrayList productos) {
		this.productos = productos;
		this.ventas = productos;
	}


	public Object darDescripcionesProductos() {
		List<String> desc = new ArrayList<String>();
		for (int i = 0; i < productos.size(); i++) {
			Producto item = ((Producto) productos.get(i));
			desc.add(item.toString().substring(item.toString().indexOf(" ") + 1, item.toString().length()));
		}
		return desc;
	}

	public Object darReferenciasProductos() {
		List<String> refs = new ArrayList<String>();
		for (int i = 0; i < productos.size(); i++) {
			refs.add(((Producto) productos.get(i)).darReferencia());
		}
		return refs;
	}

	public Object darPreciosVentaProductos() {
		List<Double> refs = new ArrayList<Double>();
		for (int i = 0; i < productos.size(); i++) {
			refs.add(((Producto) productos.get(i)).darPrecioVenta());
		}
		return refs;
	}

	public Object darReferenciasProductosNV() {
		List<String> desc = new ArrayList<String>();
		for (int i = 0; i < productos.size(); i++) {
			Producto item = ((Producto) productos.get(i));
			desc.add(item.darReferencia());
		}
		return desc;
	}

	public Object darNombresProductosNV() {
		List<String> refs = new ArrayList<String>();
		for (int i = 0; i < productos.size(); i++) {
			Producto item = ((Producto) productos.get(i));
			refs.add(item.darCantidad()+" "+item.darNombre());
		}
		return refs;
	}

	public Object darColoresProductosNV() {
		List<String> refs = new ArrayList<String>();
		for (int i = 0; i < productos.size(); i++) {
			Producto item = ((Producto) productos.get(i));
			refs.add(item.darColor());
		}
		return refs;
	}

	public Object darTallasProductosNV() {
		List<String> refs = new ArrayList<String>();
		for (int i = 0; i < productos.size(); i++) {
			Producto item = ((Producto) productos.get(i));
			refs.add(item.darTalla());
		}
		return refs;
	}

	public Object darPreciosCompraProductosNV() {
		List<Double> refs = new ArrayList<Double>();
		for (int i = 0; i < productos.size(); i++) {
			Producto item = ((Producto) productos.get(i));
			refs.add(item.darPrecioCompra());
		}
		return refs;
	}

	public Object darPreciosVentaProductosNV() {
		List<Double> refs = new ArrayList<Double>();
		for (int i = 0; i < productos.size(); i++) {
			Producto item = ((Producto) productos.get(i));
			refs.add(item.darPrecioVenta());
		}
		return refs;
	}

	public Object darMarcasProductosNV() {
		List<String> refs = new ArrayList<String>();
		for (int i = 0; i < productos.size(); i++) {
			Producto item = ((Producto) productos.get(i));
			refs.add(item.darMarca());
		}
		return refs;
	}

	public Object darTelProductosNV() {
		List<String> refs = new ArrayList<String>();
		for (int i = 0; i < productos.size(); i++) {
			Producto item = ((Producto) productos.get(i));
			refs.add(item.darTela());
		}
		return refs;
	}

	public Object darCantidadesProductosNV() {
		List<Integer> refs = new ArrayList<Integer>();
		for (int i = 0; i < productos.size(); i++) {
			Producto item = ((Producto) productos.get(i));
			refs.add(item.darCantidad());
		}
		return refs;
	}


	public Object darFechasVentas() {
		List<String> refs = new ArrayList<String>();
		for (int i = 0; i < productos.size(); i++) {
			Venta item = ((Venta) ventas.get(i));
			refs.add(new SimpleDateFormat("dd-MM-yyyy").format(item.darFecha()));
		}
		return refs;
	}


	public Object darNumerosVentas() {
		List<Long> refs = new ArrayList<Long>();
		for (int i = 0; i < productos.size(); i++) {
			Venta item = ((Venta) ventas.get(i));
			refs.add(item.darNumero());
		}
		return refs;
	}


	public Object darValoresVentas() {
		List<Double> refs = new ArrayList<Double>();
		for (int i = 0; i < productos.size(); i++) {
			Venta item = ((Venta) ventas.get(i));
			refs.add(item.darValor());
		}
		return refs;
	}
}

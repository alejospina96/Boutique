package reportes;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import controller.ControllerReport;
import mundo.Boutique;

public class Inventario extends Reporte {
	public final static String RUTA_INVENTARIO_DISENIO = "./data/reportes/Inventario.jrxml";
	private Boutique boutique;
	public Inventario(Boutique b) {
		super();
		boutique = b;
		tipo = "Reporte de inventario";
		nombre="\\ReporteInventario "+new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		generar(hash(),RUTA_INVENTARIO_DISENIO);
	}

	@Override
	public HashMap<String, Object> hash() {
		HashMap<String,Object> hm = new HashMap<String,Object>();
		ControllerReport cr = new ControllerReport(boutique.darProductos());
		hm.put("referencias", cr.darReferenciasProductosNV());
		hm.put("nombres", cr.darNombresProductosNV());
		hm.put("colores", cr.darColoresProductosNV());
		hm.put("tallas", cr.darTallasProductosNV());
		hm.put("preciosC", cr.darPreciosCompraProductosNV());
		hm.put("preciosV", cr.darPreciosVentaProductosNV());
		hm.put("marcas", cr.darMarcasProductosNV());
		hm.put("telas", cr.darTelProductosNV());
		hm.put("cantidades", cr.darCantidadesProductosNV());
		return hm;
	}
	
}

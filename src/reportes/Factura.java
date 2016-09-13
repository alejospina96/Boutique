package reportes;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import controller.ControllerReport;
import mundo.Venta;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

public class Factura extends Reporte {
	public final static String RUTA_FACTURA_DISENIO = "./data/reportes/Factura.jrxml";
	private Venta venta;
	public final static long NIT = 65728408;
	public Factura(Venta v) {
		super();
		venta = v;
		tipo = "Factura";
		nombre="\\Factura"+venta.darNumero();
		generar(hash(),RUTA_FACTURA_DISENIO);
	}
	
	public HashMap<String, Object> hash() {
		HashMap<String, Object> hm = new HashMap<String, Object>();
		hm.put("numeroFactura",venta.darNumero() );
		hm.put("nombreCliente",venta.darCliente().darNombres() );
		hm.put("nit", NIT);
		hm.put("domicilioCliente",venta.darCliente().darDireccion());
		ControllerReport cr = new ControllerReport(venta.darProductosVendidos());
		hm.put("referencias",cr.darReferenciasProductos());
		hm.put("descripciones",cr.darDescripcionesProductos() );
		hm.put("precios", cr.darPreciosVentaProductos());
		hm.put("total", venta.darValor());
		return hm;
	}

}

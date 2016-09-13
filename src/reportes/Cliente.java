package reportes;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import controller.ControllerReport;
import mundo.Boutique;

public class Cliente extends Reporte {
	public final static String RUTA_CLIENTE_DISEÑO = "./data/reportes/Cliente.jrxml";
	private Boutique boutique;
	private Date fechaInicio;
	private long cedula;
	public Cliente(Date fechaInicio, long cedula, Boutique b) {
		
		super();
		this.fechaInicio = fechaInicio;
		this.cedula = cedula;
		boutique = b;
		tipo = "Reporte de cliente "+cedula;
		nombre ="\\"+ tipo+" desde"+ new SimpleDateFormat("dd-MM-yyyy").format(fechaInicio);
		generar(hash(), RUTA_CLIENTE_DISEÑO);
	}
	@Override
	public HashMap<String, Object> hash() {
		HashMap<String,Object> hm = new HashMap<String,Object>();
		ControllerReport cr = new ControllerReport(boutique.buscarCliente(cedula).ventasDesde(fechaInicio));
		hm.put("fechas", cr.darFechasVentas());
		hm.put("numeros", cr.darNumerosVentas());
		hm.put("valores", cr.darValoresVentas());
		hm.put("fechaInicial", fechaInicio);
		hm.put("saldo", boutique.buscarCliente(cedula).darTotalCredito());
		hm.put("cliente", boutique.buscarCliente(cedula).toString());
		return hm;
	}

}

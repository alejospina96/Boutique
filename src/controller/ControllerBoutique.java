package controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ListModel;

import interfaz.InterfazPrincipal;
import mundo.Boutique;
import mundo.Producto;

public class ControllerBoutique {
	private Boutique mundo;

	public ControllerBoutique(InterfazPrincipal interfazPrincipal) {
		mundo = new ControllerPersistance().darBoutiqueCargada();
	}

	public void agregarProducto(String ref, String nombre, double precioVenta, double precioCompra, String marca,
			String talla, String color, String tela, int cantidad) throws Exception{
		System.out.println(mundo.darProductos());
		mundo.agregarProducto(ref, nombre, marca, precioCompra, precioVenta, talla, color, tela, cantidad);
		System.out.println(mundo.darProductos());
	}

	public void registrarSalida(String concepto, double monto) {
		mundo.registrarSalida(concepto, monto);
	}

	public ArrayList<Producto> darInventario() {
		return mundo.darProductos();
	}

	public Boutique darMundo() {
		return mundo;
	}

	public void vender(boolean p, ArrayList<Producto> vender, long cCliente) throws ClienteNoExisteException {
		
			mundo.venderProductos(vender, cCliente, p);
		
	}

	public void pagar(long cedula, double valor) throws Exception {
		mundo.pagarCredito(cedula, valor);
	}

	public String darCreditoCliente(long cedula) {
		return mundo.darCreditoCliente(cedula)+"";
	}

	public boolean existeCliente(long cedula) {
		
		return mundo.existeCliente(cedula);
	}

}

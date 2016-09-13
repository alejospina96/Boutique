package mundo;

import java.io.Serializable;
import java.util.ArrayList;

public class Producto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * @param referencia
	 * @param nombre
	 * @param marca
	 * @param precioCompra
	 * @param precioVenta
	 * @param talla
	 * @param color
	 * @param tela
	 * @param cantidad
	 */
	public Producto(String referencia, String nombre, String marca, double precioCompra, double precioVenta,
			String talla, String color, String tela, int cantidad) {
		this.referencia = referencia;
		this.nombre = nombre;
		this.marca = marca;
		this.precioCompra = precioCompra;
		this.precioVenta = precioVenta;
		this.talla = talla;
		this.color = color;
		this.tela = tela;
		this.cantidad = cantidad;
	}
	public Producto(Producto p, int cantidad2) {
		this.cantidad= cantidad2;
		this.color = p.color;
		this.marca =p.marca;
		this.nombre = p.nombre;
		this.precioCompra = p.precioCompra;
		this.precioVenta = p.precioVenta;
		this.referencia = p.referencia;
		this.talla = p.talla;
		this.tela = p.tela;
	}
	public double darValorTotal(){
		return precioVenta*cantidad;
	}
	/** @pdOid ac534ae2-e5bf-493f-a25e-0296f2e8d36a */
	   private String referencia;
	   /** @pdOid e89e7162-01cf-4e28-a2d4-4ab7852fe0b3 */
	   private String nombre;
	   /** @pdOid e7eddfc3-e8f1-4adf-9684-a37561d40c6b */
	   private String marca;
	   /** @pdOid 249b53ed-ff4c-4f51-9e0d-e2f683094123 */
	   private double precioCompra;
	   /** @pdOid 397f6785-dc0b-4371-93cc-046c9684bff9 */
	   private double precioVenta;
	   /** @pdOid 040519a3-2def-44c6-b705-543d933e4b71 */
	   private String talla;
	   /** @pdOid 1af633fa-ff0f-410a-a486-2e402ed96bb8 */
	   private String color;
	   /** @pdOid 38faf15b-fc0f-45d1-bc3f-ecec01a18636 */
	   private String tela;
	   /** @pdOid 26352159-d817-4cae-9a62-ec579e734c22 */
	   private int cantidad;
	   
	   /** @pdOid 8482c713-344a-44d5-94f4-edfde1d1a335 */
	   public static final String S = "S";
	   /** @pdOid 861a25b8-3295-4056-9b5c-bd4d2f213b96 */
	   public static final String M = "M";
	   /** @pdOid 732292c2-8d0a-4cf5-af8b-3688838904d5 */
	   public static final String L = "L";
	   /** @pdOid 678e3cee-f469-4f04-8008-d7bec9a110bc */
	   public static final String XL = "XL";
	   /** @pdOid 28aa1c2e-854c-4746-9d77-00cd76ed3d9c */
	   public static final String XXL = "XXL";
	
	/**
	 * @return the referencia
	 */
	public String darReferencia() {
		return referencia;
	}
	/**
	 * @return the nombre
	 */
	public String darNombre() {
		return nombre;
	}
	/**
	 * @return the marca
	 */
	public String darMarca() {
		return marca;
	}
	/**
	 * @return the precioCompra
	 */
	public double darPrecioCompra() {
		return precioCompra;
	}
	/**
	 * @return the precioVenta
	 */
	public double darPrecioVenta() {
		return precioVenta;
	}
	/**
	 * @return the talla
	 */
	public String darTalla() {
		return talla;
	}
	/**
	 * @return the color
	 */
	public String darColor() {
		return color;
	}
	/**
	 * @return the tela
	 */
	public String darTela() {
		return tela;
	}
	/**
	 * @return the cantidad
	 */
	public int darCantidad() {
		return cantidad;
	}
	public int venderCantidad(int cantidad2) {
		cantidad-=cantidad2;
		return cantidad;
	}
	public String toString(){
		return "Ref: "+referencia+", Nombre: "+nombre+", Cantidad: "+cantidad;
	}
}

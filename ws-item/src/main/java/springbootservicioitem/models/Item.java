package springbootservicioitem.models;

import java.io.Serializable;

import springbootserviciocommons.models.entity.Producto;

public class Item implements Serializable {

	private static final long serialVersionUID = -2234152375705551914L;

	private Producto producto;

	private int cantidad;

	public Item() {
	}

	public Item(Producto producto, int cantidad) {
		this.producto = producto;
		this.cantidad = cantidad;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Double getTotal() {
		return producto.getPrecio() * cantidad;
	}

}
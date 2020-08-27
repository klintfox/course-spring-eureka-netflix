package com.app.productos.models.service;

import java.util.List;

import springbootserviciocommons.models.entity.Producto;

public interface IProductoService {

	public List<Producto> findAll();

	public Producto findById(Long id);

	public Producto save(Producto producto);

	public void deleteById(Long id);
}

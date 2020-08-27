package com.app.productos.models.dao;

import org.springframework.data.repository.CrudRepository;

import springbootserviciocommons.models.entity.Producto;

public interface ProductoDao extends CrudRepository<Producto, Long>{
	

}

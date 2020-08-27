package springbootservicioitem.models.service;

import java.util.List;

import springbootserviciocommons.models.entity.Producto;
import springbootservicioitem.models.Item;

public interface ItemService {

	public List<Item> findAll();

	public Item findById(Long id, Integer cantidad);

	public Producto save(Producto producto);

	public Producto update(Producto producto, Long id);

	public void delete(Long id);
}

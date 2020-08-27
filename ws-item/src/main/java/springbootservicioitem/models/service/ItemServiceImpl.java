package springbootservicioitem.models.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import springbootserviciocommons.models.entity.Producto;
import springbootservicioitem.models.Item;

@Service("serviceRestTemplate")
public class ItemServiceImpl implements ItemService{

	@Autowired	
	private RestTemplate clienteRes;
	
	@Override
	public List<Item> findAll() {
		List<Producto> productos = Arrays.asList(clienteRes.getForObject("http://servicio-productos/listar", Producto[].class));
		return productos.stream().map(p ->new Item(p, 1)).collect(Collectors.toList());
	}

	@Override
	public Item findById(Long id, Integer cantidad) {
		Map<String, String> pathVariables = new HashMap<String, String>();
		pathVariables.put("id", id.toString());
		Producto producto = clienteRes.getForObject("http://servicio-productos/ver/{id}", Producto.class, pathVariables);		
		return new Item(producto, cantidad);
	}

	@Override
	public Producto save(Producto producto) {
		HttpEntity<Producto> body  = new HttpEntity<Producto>(producto);
		ResponseEntity<Producto> response = clienteRes.exchange("http://servicio-productos/crear", 
				HttpMethod.POST, body, Producto.class);
		Producto productoResponse = response.getBody();
		return productoResponse;
	}

	@Override
	public Producto update(Producto producto, Long id) {
		HttpEntity<Producto> body  = new HttpEntity<Producto>(producto);
		Map<String, String> pathVariables = new HashMap<String, String>();
		pathVariables.put("id", id.toString());
		ResponseEntity<Producto> response = clienteRes.exchange("http://servicio-productos/editar/{id}", 
				HttpMethod.PUT, body, Producto.class, pathVariables);
		return response.getBody();
	}

	@Override
	public void delete(Long id) {		
		Map<String, String> pathVariables = new HashMap<String, String>();
		pathVariables.put("id", id.toString());
		clienteRes.delete("http://servicio-productos/eliminar/{id}", pathVariables);
	}

}
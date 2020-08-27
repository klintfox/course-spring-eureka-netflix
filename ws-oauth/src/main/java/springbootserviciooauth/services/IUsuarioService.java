package springbootserviciooauth.services;

import springbootserviciousuarioscommons.entity.Usuario;

public interface IUsuarioService {

	public  Usuario findByUsername(String username);
	
	public Usuario update(Usuario usuario, Long id);
}

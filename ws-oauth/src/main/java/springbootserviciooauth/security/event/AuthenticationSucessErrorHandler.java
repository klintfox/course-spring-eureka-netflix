package springbootserviciooauth.security.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import brave.Tracer;
import feign.FeignException;
import springbootserviciooauth.services.IUsuarioService;
import springbootserviciousuarioscommons.entity.Usuario;

@Component
public class AuthenticationSucessErrorHandler implements AuthenticationEventPublisher {

	private Logger log = LoggerFactory.getLogger(AuthenticationSucessErrorHandler.class);

	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private Tracer tracer;

	@Override
	public void publishAuthenticationSuccess(Authentication authentication) {
		UserDetails user = (UserDetails) authentication.getPrincipal();
		System.out.println("Succes Login:" + user.getUsername());
		log.info("Succes Login:" + user.getUsername());
		
		Usuario usuario = usuarioService.findByUsername(authentication.getName());
		
		if(usuario.getIntentos() != null && usuario.getIntentos() > 0) {
			usuario.setIntentos(0);
			usuarioService.update(usuario, usuario.getId());
		}
	}

	@Override
	public void publishAuthenticationFailure(AuthenticationException exception, Authentication authentication) {
		String mensaje = "Error Login:" ;
		System.out.println(mensaje +  exception.getMessage());
		log.error(mensaje + exception.getMessage());

		try {
			StringBuilder errors = new StringBuilder();
			errors.append(mensaje);
			
			Usuario usuario = usuarioService.findByUsername(authentication.getName());
			
			if (usuario.getIntentos() == null) {
				usuario.setIntentos(0);
			}
			log.info("Intento actual es de: " + usuario.getIntentos());
			usuario.setIntentos(usuario.getIntentos()+1);
			log.info(" - Intento despues es de: " + usuario.getIntentos());
			
			errors.append("Intentos del login: " + usuario.getIntentos());
			
			if(usuario.getIntentos() >= 3) {
				String errorMax = "El usuario %s deshablitado por máximo intentos";
				log.error(String.format(errorMax, usuario.getUsername()));
				errors.append(" - " +errorMax);
				usuario.setEnabled(false);
			}
			usuarioService.update(usuario, usuario.getId());
			
			tracer.currentSpan().tag("error.mensaje", errors.toString());
		} catch (FeignException e) {
			log.error(String.format("El usuario %s no existe en el sistema" + authentication.getName()));
		}

	}

}

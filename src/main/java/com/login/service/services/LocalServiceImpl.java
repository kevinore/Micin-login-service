package com.login.service.services;


import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.login.service.emailservice.EmailService;
import com.login.service.models.Local;
import com.login.service.repository.LocalRepository;
import com.login.service.security.JwtUtil;
import io.jsonwebtoken.Jwts;


@Service
public class LocalServiceImpl implements LocalService{
	
	@Autowired
	private LocalRepository localRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	/**
	 * {@link com.login.service.services.LocalServiceImpl#register(Local)} 
	 * 
	 * Método para regitrar locales
	 * 
	 * @param Local entity
	 * 
	 * @return 200 si se eliminó con éxito
	 * 
	 */

	@Override
	public String register(Local local) {
		if(localRepository.ifExistsByNit(local.getNit())==true){
			return "306";
		}else if(localRepository.ifExistsByNombre(local.getNombre())==true){
			return "307";
		}else {
			try {
				local.setPassword(passwordEncoder.encode(local.getPassword()));
				local.setHabilitado(true);
				local.setActivo(true);
				System.out.println(local.getEmail());
				String token = JwtUtil.createToken(local.getNombre());
				EmailService.sendEmailVerifyAccount(local.getEmail(),"https://festive-brahmagupta-59be6c.netlify.app/confirmed/"+token);
				localRepository.save(local);
			}catch (Exception e) {
				e.printStackTrace();
			}
			return "200";
		}
	}
	
	/**
	 * {@link com.login.service.services.LocalServiceImpl#verifyAccount(String)} 
	 * 
	 * Método para validar la cuenta del usuario
	 * 
	 * @param token para la validacion del usuario
	 * 
	 * @return 200 si se eliminó con éxito
	 * 
	 */
	
	@Override
	public String verifyAccount(String token) { 
		String nombre="";
		if(token != null) {
			nombre = Jwts.parser()
					.setSigningKey("M@cr@nVerify")
					.parseClaimsJws(token.replace("Bearer", ""))
					.getBody()
					.getSubject();
			localRepository.verifyAccount(nombre);
			return "200";
		}else {
			return "400";
		}
		
	}
	
	/**
	 * {@link com.login.service.services.LocalServiceImpl#getIdLocal(String)} 
	 * 
	 * Método para obtener el id del local 
	 * 
	 * @param token
	 * 
	 * @return id de local
	 * 
	 */
	
	@Override
	public int getIdLocal(String token) {
		String nombre="";
		if(token != null) {
			nombre = Jwts.parser()
					.setSigningKey("M@cr@n")
					.parseClaimsJws(token.replace("Bearer", ""))
					.getBody()
					.getSubject();
		}
		int id = localRepository.getIdLocal(nombre);
		return id;
	}
	
	/**
	 * {@link com.login.service.services.LocalServiceImpl#validarIfUserIsActive(String)} 
	 * 
	 * Método para validar si el usuario esta activo o esta habilitado para iniciar sesión
	 * 
	 * @param nombre local
	 * 
	 * @return 200 si se eliminó con éxito
	 * 
	 */

	@Override
	public String validarIfUserIsActive(String nombre) {
		if(localRepository.getHabilitad(nombre)==false) {
			return "406";
		}else if(localRepository.getActivo(nombre)==false) {
			return "407";
		}else {
			return "200";
		}
		
	}

	@Override
	public String deleteCuenta(String nit, String nombre) {
		System.out.println(localRepository.getNit(nombre));
		if(localRepository.ifExistsNit(nit)==true && localRepository.getNit(nombre).equals(nit)) {
			localRepository.deleteAccountState(nit);
			return "200";
		}
		return "404";
	}

	@Override
	public String recuperarCuenta(String email) {
		if(localRepository.ifExistsEmail(email)==true){
			try {
				localRepository.recuperarAccount(email);
				EmailService.recuperarCuenta(email, "https://festive-brahmagupta-59be6c.netlify.app/recuperado");
				return "200";
			}catch (Exception e) {
				System.out.println(e);
			}
		}
		return "400";
	}
}

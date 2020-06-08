package com.login.service.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.login.service.models.Local;
import com.login.service.services.LocalService;

import io.jsonwebtoken.Jwts;

@RestController
public class LocalController{
	
	@Autowired
	private LocalService localService; 
	
	@RequestMapping(value="/registro", method=RequestMethod.POST)
	public void registro_local(HttpServletResponse response,@Valid @RequestBody Local local) throws IOException{
		String validation = localService.register(local);
		if(validation == "200"){
			response.setHeader("Custom-Header", "foo");
			response.setStatus(200);
			response.getWriter().println("Local was register");
		}else if(validation=="306") {
			response.setHeader("Custom-Header", "foo");
			response.setStatus(306);
			response.getWriter().println("Nit already exists");
		}else if(validation=="307") {
			response.setHeader("Custom-Header", "foo");
			response.setStatus(307);
			response.getWriter().println("Nombre already exists");
		}
	}
	
	@RequestMapping(value="/verify_account", method=RequestMethod.PUT)
	public void verifyAccount(HttpServletResponse response,@RequestParam("token") String token) throws IOException {
		String state = localService.verifyAccount(token);
		if(state == "200"){
			response.setHeader("Custom-Header", "foo");
			response.setStatus(200);
			response.getWriter().println("Local was update");
		}else {
			response.setHeader("Custom-Header", "foo");
			response.setStatus(400);
			response.getWriter().println("Bad request");
		}
	}
	
	@RequestMapping(value="/getIdLocal", method=RequestMethod.GET)
	public void getIdLocal(HttpServletResponse res, HttpServletRequest request) throws IOException {
		String token = request.getHeader("Authorization");
		int local_id = localService.getIdLocal(token);
		res.setContentType("application/json");
		res.setCharacterEncoding("UTF-8");
		res.getWriter().write(
				"{\"" + "local_id" + "\":\"" + local_id + "\"}"
				);
	}
	
	@RequestMapping(value="/validate_user", method=RequestMethod.GET)
	public void validar_user(HttpServletResponse res, HttpServletRequest request, @RequestParam("nombre") String nombre) throws IOException {
		String validar_user = localService.validarIfUserIsActive(nombre);
		if(validar_user == "406") {
			res.setHeader("Custom-Header", "foo");
			res.setStatus(406);
			res.getWriter().println("El usuario no esta habilitado");
		}else if(validar_user == "407") {
			res.setHeader("Custom-Header", "foo");
			res.setStatus(407);
			res.getWriter().println("El usuario no esta activo");
		}else {
			res.setHeader("Custom-Header", "foo");
			res.setStatus(200);
			res.getWriter().println("ok");
		}
		
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public void deleteAccount(HttpServletResponse res, HttpServletRequest request, @RequestParam("nit") String nit) throws IOException {
		String deleteState = null;
		String token = request.getHeader("Authorization");
		if (token != null) {
			String nombre = Jwts.parser()
					.setSigningKey("M@cr@n")
					.parseClaimsJws(token.replace("Bearer", ""))
					.getBody()
					.getSubject();
			deleteState = localService.deleteCuenta(nit, nombre);
		}
		
		if(deleteState == "200") {
			res.setHeader("Custom-Header", "foo");
			res.setStatus(200);
			res.getWriter().println("El local fue eliminado");
		}else {
			res.setHeader("Custom-Header", "foo");
			res.setStatus(404);
			res.getWriter().println("Local does't exists");
		}
		
	}
	
	@RequestMapping(value="/recuperar", method=RequestMethod.POST)
	public void recuperarCuenta(HttpServletResponse res, @RequestParam("email") String email) throws IOException {
		String recuperarCuenta = localService.recuperarCuenta(email);
		if(recuperarCuenta == "200") {
			res.setHeader("Custom-Header", "foo");
			res.setStatus(200);
			res.getWriter().println("La cuenta fue recuperada");
		}else {
			res.setHeader("Custom-Header", "foo");
			res.setStatus(404);
			res.getWriter().println("Local does't exists");
		}
		
	}
	
}

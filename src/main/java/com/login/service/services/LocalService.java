package com.login.service.services;


import com.login.service.models.Local;


public interface LocalService {
	
	String register (Local local);
	String verifyAccount(String token);
	int getIdLocal(String token);
	String validarIfUserIsActive(String nombre);
	String deleteCuenta(String email);
}

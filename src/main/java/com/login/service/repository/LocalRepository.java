package com.login.service.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.login.service.models.Local;

public interface LocalRepository extends CrudRepository<Local, Integer> {
	
	@Query(value="SELECT EXISTS(SELECT l.* FROM local l WHERE l.nit=?1)", nativeQuery=true)
	boolean ifExistsByNit(String nit);
	
	@Query(value="SELECT EXISTS(SELECT l.* FROM local l WHERE LOWER(l.nombre)=LOWER(?1))", nativeQuery=true)
	boolean ifExistsByNombre(String nombre);
	
	@Query(value="SELECT l.codigo FROM local l WHERE LOWER(l.nombre)=LOWER(?1)", nativeQuery=true)
	int getIdLocal(String nombre);
	
	@Query(value="SELECT l.habilitado FROM local l WHERE LOWER(l.nombre)=LOWER(?1)", nativeQuery=true)
	boolean getHabilitad(String nombre);
	
	@Query(value="SELECT l.activo FROM local l WHERE LOWER(l.nombre)=LOWER(?1)", nativeQuery=true)
	boolean getActivo(String nombre);
	
	@Query(value="SELECT EXISTS(SELECT l.* FROM local l WHERE l.nit=?1)", nativeQuery=true)
	boolean ifExistsNit(String nit);
	
	@Query(value="SELECT EXISTS(SELECT l.* FROM local l WHERE LOWER(l.email)=LOWER(?1))", nativeQuery=true)
	boolean ifExistsEmail(String email);
	
	@Query(value="SELECT l.nit FROM local l WHERE LOWER(l.nombre)=LOWER(?1)", nativeQuery=true)
	String getNit(String nombre);
	
	@Transactional
	@Modifying
	@Query(value="UPDATE local SET habilitado='true' WHERE LOWER(nombre)=LOWER(?1)",nativeQuery=true)
	void verifyAccount(String nombre);
	
	@Transactional
	@Modifying
	@Query(value="UPDATE local SET activo='false' WHERE nit=?1",nativeQuery=true)
	void deleteAccountState(String nit);
	
	@Transactional
	@Modifying
	@Query(value="UPDATE local SET activo='true' WHERE LOWER(email)=LOWER(?1)",nativeQuery=true)
	void recuperarAccount(String email);
	
	@Transactional
	@Modifying
	@Query(value="DELETE FROM local where nit=?1", nativeQuery = true)
	void deleteLocal(int nit);
}

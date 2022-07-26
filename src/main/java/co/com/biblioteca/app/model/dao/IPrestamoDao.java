package co.com.biblioteca.app.model.dao;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.com.biblioteca.app.model.entity.Prestamo;

public interface IPrestamoDao extends JpaRepository<Prestamo, Long> {
	
	/*
	@Query("select p FROM prestamos p where p.id_usuario = :id_usuario")
	public Prestamo findByIdUsuario(@Param("id_usuario") String id_usuario);*/
	
	@Query("select p from Prestamo p where p.numIdUsuario=?1")
	public Prestamo findByIdUsuario(String numIdUsuario); 
	

}

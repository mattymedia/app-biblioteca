package co.com.biblioteca.app.model.service;

import java.util.List;

import co.com.biblioteca.app.model.entity.Prestamo;

public interface IPrestamoService {

	public List<Prestamo> findAll();
	
	public Prestamo findByIdUsuario(String id_usuario);
	
	public Prestamo save(Prestamo prestamo);
	
	
}

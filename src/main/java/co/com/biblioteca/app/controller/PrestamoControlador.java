package co.com.biblioteca.app.controller;


import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.biblioteca.app.model.entity.Prestamo;
import co.com.biblioteca.app.model.service.IPrestamoService;

@RestController
@RequestMapping("/prestamos")
public class PrestamoControlador {

	@Autowired
	private IPrestamoService prestamoService;
	
	@GetMapping("/listar")
	public List<Prestamo> findAll(){
		return prestamoService.findAll();
	}
	
	@PostMapping("/crear")
	public ResponseEntity<?> save(@RequestBody Prestamo prestamo) {
		Map<String, Object> mensajes = new HashMap<>();
		Prestamo guardarPrestamo = null;
		Prestamo pendiente = prestamoService.findByIdUsuario(prestamo.getNumIdUsuario());
		
		if(pendiente != null) {
			mensajes.put("mensaje", "el usuario ya tiene un libro prestado.");
			return new ResponseEntity<Map<String, Object>>(mensajes, HttpStatus.BAD_REQUEST);
		}
		
		try {
			
			if(prestamo.getIdTipoUsuario().getId() == 1) {
				prestamo.setEntrega(fechaDevolucion(10));
			} 
			
			else if(prestamo.getIdTipoUsuario().getId() == 2) {
				prestamo.setEntrega(fechaDevolucion(8));
			} 
			
			else if (prestamo.getIdTipoUsuario().getId() == 3){
				prestamo.setEntrega(fechaDevolucion(7));
			}
			
			else {
				mensajes.put("mensaje", "tipo de usuario no permitido.");
				return new ResponseEntity<Map<String, Object>>(mensajes, HttpStatus.BAD_REQUEST);
			}
				
			guardarPrestamo = prestamoService.save(prestamo);
			
		} catch(DataAccessException e) {
			mensajes.put("mensaje", "Error al realizar el registro en la Base de datos.");
			mensajes.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(mensajes, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		mensajes.put("mensaje", "nuevo prestamo creado con exito.");
		mensajes.put("conductor", guardarPrestamo);
		
		return new ResponseEntity<Map<String, Object>>(mensajes, HttpStatus.CREATED);
	}
	
	
	public LocalDate fechaDevolucion(int dias) {
		LocalDate fechaActual = LocalDate.now();
		int nDias = dias;
		int addDias = 0;
		
		while (addDias < nDias) {
			 fechaActual = fechaActual.plusDays(1);
	        if (!(fechaActual.getDayOfWeek() == DayOfWeek.SATURDAY ||
	        		fechaActual.getDayOfWeek() == DayOfWeek.SUNDAY)) {
	            ++addDias;
	        }
		}
		return fechaActual;
	}
}

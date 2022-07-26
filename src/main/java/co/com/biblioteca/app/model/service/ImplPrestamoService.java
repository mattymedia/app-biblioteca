package co.com.biblioteca.app.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.com.biblioteca.app.model.dao.IPrestamoDao;
import co.com.biblioteca.app.model.entity.Prestamo;

@Service
public class ImplPrestamoService implements IPrestamoService {

	@Autowired
	private IPrestamoDao prestamoDao; 
	
	
	@Override
	@Transactional(readOnly = true)
	public List<Prestamo> findAll() {
		return prestamoDao.findAll();
	}

	
	@Override
	@Transactional(readOnly = true)
	public Prestamo findByIdUsuario(String id_usuario) {
		return prestamoDao.findByIdUsuario(id_usuario);
	}
	

	@Override
	@Transactional
	public Prestamo save(Prestamo prestamo) {
		return prestamoDao.save(prestamo);
	}


}

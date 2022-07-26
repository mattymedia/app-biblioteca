package co.com.biblioteca.app.model.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "prestamos")
public class Prestamo implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	@Size(max = 10)
	private String isbn;

	@Size(max = 10)
	@Column(name="id_usuario")
	private String numIdUsuario;

	@OneToOne
	@JoinColumn(name = "usuario_tipo")
	private TipoUsuario idTipoUsuario;
	
	@Column(name = "create_at")
	private Date createAt;
	
	private LocalDate entrega;
	
	
	@PrePersist
	public void actualDate() {
		createAt = new Date();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getNumIdUsuario() {
		return numIdUsuario;
	}

	public void setNumIdUsuario(String numIdUsuario) {
		this.numIdUsuario = numIdUsuario;
	}

	public TipoUsuario getIdTipoUsuario() {
		return idTipoUsuario;
	}

	public void setIdTipoUsuario(TipoUsuario idTipoUsuario) {
		this.idTipoUsuario = idTipoUsuario;
	}
	
	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public LocalDate getEntrega() {
		return entrega;
	}

	public void setEntrega(LocalDate entrega) {
		this.entrega = entrega;
	}

	private static final long serialVersionUID = 1L;


}

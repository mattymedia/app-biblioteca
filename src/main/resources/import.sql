INSERT INTO usuarios_tipos (id, descripcion) VALUES(1, 'Afiliado');
INSERT INTO usuarios_tipos (id, descripcion) VALUES(2, 'Empleado');
INSERT INTO usuarios_tipos (id, descripcion) VALUES(3, 'Invitado');

INSERT INTO prestamos (isbn, id_usuario, usuario_tipo, create_at) VALUES('SDHKA11', 'DSA231', 3, '2022-06-13');
CREATE SCHEMA serviciosYA;

CREATE TABLE usuarios (
  id VARCHAR(255) AUTO_INCREMENT PRIMARY KEY NOT NULL,
  nombre VARCHAR(50) NOT NULL,
  apellido VARCHAR(50) NOT NULL,
  email VARCHAR(100) NOT NULL,
  tel VARCHAR(15),
  contrasenia VARCHAR(255) NOT NULL,
  rol VARCHAR(14),
  activo BOOLEAN
);

INSERT INTO usuarios (id, nombre, apellido, email, tel, contrasenia, rol, activo)
VALUES ('11','adminPrueba', 'adminPrueba', 'admin@admin.com', '123456789', 'admin1234', 'ADMIN', true);

INSERT INTO usuarios (id, nombre, apellido, email, tel, contrasenia, rol, activo)
VALUES ('22','userPrueba', 'userPrueba', 'user@user.com', '123456789', 'user1234', 'USER', true);

INSERT INTO usuarios (id, nombre, apellido, email, tel, contrasenia, rol, activo)
VALUES ('33','proveedorPrueba', 'proveedorPrueba', 'proveedor@proveedor.com', '123456789', 'proveedor1234', 'PROVEEDOR', true);
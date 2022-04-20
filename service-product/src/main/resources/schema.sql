DROP TABLE IF EXISTS tbl_categorias;

CREATE TABLE tbl_categorias (
  id BIGINT AUTO_INCREMENT  PRIMARY KEY,
  nombre VARCHAR(250) NOT NULL
);


DROP TABLE IF EXISTS tbl_productos;

CREATE TABLE tbl_productos (
  id BIGINT AUTO_INCREMENT  PRIMARY KEY,
  nombre VARCHAR(250) NOT NULL,
  descripcion VARCHAR(250) NOT NULL,
  stock DOUBLE,
  precio DOUBLE,
  estado VARCHAR(250) NOT NULL,
  fecha_creacion TIMESTAMP,
  categoria BIGINT
);
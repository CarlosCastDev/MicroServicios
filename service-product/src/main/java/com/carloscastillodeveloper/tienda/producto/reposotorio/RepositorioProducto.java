package com.carloscastillodeveloper.tienda.producto.reposotorio;

import com.carloscastillodeveloper.tienda.producto.entity.Categoria;
import com.carloscastillodeveloper.tienda.producto.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepositorioProducto extends JpaRepository<Producto, Long> {

    public List<Producto> findByCategoria(Categoria categoria);

}

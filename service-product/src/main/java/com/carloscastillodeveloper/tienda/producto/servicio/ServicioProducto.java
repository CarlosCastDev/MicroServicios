package com.carloscastillodeveloper.tienda.producto.servicio;

import com.carloscastillodeveloper.tienda.producto.entity.Categoria;
import com.carloscastillodeveloper.tienda.producto.entity.Producto;

import java.util.List;

public interface ServicioProducto {
    public List<Producto> listaTodosProducto();
    public Producto getProducto(Long id);
    public Producto crearProducto(Producto producto);
    public Producto actualizarProducto(Producto producto);
    public Producto eliminarProducto(Long id);
    public List<Producto> encontrarPorCategoria(Categoria categoria);
    public Producto actualizarStock(Long id, Double cantidad);
}

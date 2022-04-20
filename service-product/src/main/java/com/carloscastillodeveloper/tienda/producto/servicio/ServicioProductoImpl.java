package com.carloscastillodeveloper.tienda.producto.servicio;

import com.carloscastillodeveloper.tienda.producto.entity.Categoria;
import com.carloscastillodeveloper.tienda.producto.entity.Producto;
import com.carloscastillodeveloper.tienda.producto.reposotorio.RepositorioProducto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicioProductoImpl implements ServicioProducto{

    private final RepositorioProducto repositorioProducto;

    @Override
    public List<Producto> listaTodosProducto() {
        return repositorioProducto.findAll();
    }

    @Override
    public Producto getProducto(Long id) {
        return repositorioProducto.findById(id).orElse(null);
    }

    @Override
    public Producto crearProducto(Producto producto) {
        producto.setEstado("CREADO");
        producto.setFechaCreacion(new Date());
        return repositorioProducto.save(producto);
    }

    @Override
    public Producto actualizarProducto(Producto producto) {
        Producto productoBD = getProducto(producto.getId());
        if(null==productoBD)
        {
            return null;
        }

        productoBD.setNombre(producto.getNombre());
        productoBD.setDescripcion(producto.getDescripcion());
        productoBD.setPrecio(producto.getPrecio());
        productoBD.setCategoria(producto.getCategoria());

        return repositorioProducto.save(productoBD);
    }

    @Override
    public Producto eliminarProducto(Long id) {
        Producto productoBD = getProducto(id);
        if(null==productoBD)
        {
            return null;
        }
        productoBD.setEstado("ELIMINADO");

        return repositorioProducto.save(productoBD);
    }

    @Override
    public List<Producto> encontrarPorCategoria(Categoria categoria) {
        return repositorioProducto.findByCategoria(categoria);
    }

    @Override
    public Producto actualizarStock(Long id, Double cantidad) {
        Producto productoBD = getProducto(id);
        if(null==productoBD)
        {
            return null;
        }
        Double stock = productoBD.getStock()+cantidad;
        productoBD.setStock(stock);

        return repositorioProducto.save(productoBD);
    }
}

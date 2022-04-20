package com.carloscastillodeveloper.tienda.producto;

import com.carloscastillodeveloper.tienda.producto.entity.Categoria;
import com.carloscastillodeveloper.tienda.producto.entity.Producto;
import com.carloscastillodeveloper.tienda.producto.reposotorio.RepositorioProducto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.List;

@DataJpaTest
public class RepositorioProductoMockTest {

    @Autowired
    private RepositorioProducto repositorioProducto;

    @Test
    public void cuandoFindByCategoria_entoncesReturnListProductos(){
        Producto producto01 = Producto.builder()
                .id(1L)
                .nombre("computadora")
                .categoria
                        (
                            Categoria.builder()
                                    .id(1L)
                                    .build()
                        )
                .descripcion("")
                .stock(Double.parseDouble("10"))
                .precio(Double.parseDouble("112404.99"))
                .estado("Creado")
                .fechaCreacion(new Date())
                .build();

        repositorioProducto.save(producto01);

        List<Producto> encontrados = repositorioProducto.findByCategoria(producto01.getCategoria());

        Assertions.assertThat(encontrados.size()).isEqualTo(3);
    }

}

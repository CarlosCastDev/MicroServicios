package com.carloscastillodeveloper.tienda.producto;

import com.carloscastillodeveloper.tienda.producto.entity.Categoria;
import com.carloscastillodeveloper.tienda.producto.entity.Producto;
import com.carloscastillodeveloper.tienda.producto.reposotorio.RepositorioProducto;
import com.carloscastillodeveloper.tienda.producto.servicio.ServicioProducto;
import com.carloscastillodeveloper.tienda.producto.servicio.ServicioProductoImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.Optional;

@SpringBootTest
public class ServicioProductoMockTest {
    @Mock
    private RepositorioProducto repositorioProducto;

    private ServicioProducto servicioProducto;

    @BeforeEach
    public void setup()
    {
        MockitoAnnotations.initMocks(this);
        //MockitoAnnotations.openMocks(this);
        servicioProducto = new ServicioProductoImpl(repositorioProducto);
        Producto computadora = Producto.builder()
                .id(1L)
                .nombre("computadora")
                .categoria
                        (
                                Categoria.builder()
                                        .id(1L)
                                        .build()
                        )
                .descripcion("")
                .stock(Double.parseDouble("5"))
                .precio(Double.parseDouble("12.50"))
                .estado("Creado")
                .fechaCreacion(new Date())
                .build();

        Mockito.when(repositorioProducto.findById(1L)).thenReturn(Optional.of(computadora));
        Mockito.when(repositorioProducto.save(computadora)).thenReturn(computadora);
    }

    @Test
    public void whenValidGetID_ThenReturnProducto()
    {
        Producto encontrado = servicioProducto.getProducto(1L);
        Assertions.assertThat(encontrado.getNombre()).isEqualTo("computadora");
    }

    @Test
    public void whenValidUpdateStock_ThenReturnNewStock(){
        Producto nuevoStock = servicioProducto.actualizarStock(1L,Double.parseDouble("8"));
        Assertions.assertThat(nuevoStock.getStock()).isEqualTo(Double.parseDouble("13"));
    }
}

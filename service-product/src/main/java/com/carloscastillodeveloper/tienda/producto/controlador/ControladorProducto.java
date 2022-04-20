package com.carloscastillodeveloper.tienda.producto.controlador;

import com.carloscastillodeveloper.tienda.producto.entity.Categoria;
import com.carloscastillodeveloper.tienda.producto.entity.Producto;
import com.carloscastillodeveloper.tienda.producto.servicio.ServicioProducto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/productos")
public class ControladorProducto {

    @Autowired
    private ServicioProducto servicioProducto;

    @GetMapping
    public ResponseEntity<List<Producto>> listaProducto(@RequestParam(name = "idCategoria", required = false) Long idCategoria)
    {
        List<Producto> productos = null;

        if(idCategoria == null)
        {
            productos = servicioProducto.listaTodosProducto();
            if(productos.isEmpty())
            {
                return  ResponseEntity.noContent().build();
            }
        }else
        {
            productos = servicioProducto.encontrarPorCategoria(Categoria.builder().id(idCategoria).build());
            if(productos.isEmpty())
            {
                return  ResponseEntity.notFound().build();
            }
        }

        return ResponseEntity.ok(productos);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Producto> getProducto(@PathVariable("id") Long id)
    {
        Producto producto = servicioProducto.getProducto(id);
        if(null==producto)
        {
            return ResponseEntity.notFound().build();
        }
            return  ResponseEntity.ok(producto);
    }

    @PostMapping
    public ResponseEntity<Producto> crearProducto(@Valid @RequestBody Producto producto, BindingResult result)
    {
        if(result.hasErrors())
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatearMensaje(result));
        }
        Producto productoCreado = servicioProducto.crearProducto(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(producto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable(value = "id") Long id, @RequestBody Producto producto)
    {
        producto.setId(id);
        Producto productoActualizado = servicioProducto.actualizarProducto(producto);
        if(productoActualizado==null)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return  ResponseEntity.ok(productoActualizado);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Producto> eliminaProducto(@PathVariable("id") Long id)
    {
        Producto producto = servicioProducto.eliminarProducto(id);
        if(producto == null)
        {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(producto);
    }

    @GetMapping(value = "/{id}/stock")
    public ResponseEntity<Producto> actualizarStock(@PathVariable("id") Long id, @RequestParam(name = "cantidad", required = true) Double cantidad){
        Producto producto = servicioProducto.actualizarStock(id, cantidad);
        if(producto == null)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(producto);
    }

    private String formatearMensaje(BindingResult result)
    {
        List<Map<String, String>> errores = result.getFieldErrors().stream()
                .map(err ->{
                    Map<String, String> error = new HashMap<>();
                    error.put(err.getField(),err.getDefaultMessage());
                    return  error;
                }).collect(Collectors.toList());

        MensajeError mensajeError = MensajeError.builder().codigo("01").mensajes(errores).build();

        ObjectMapper mapper = new ObjectMapper();
        String jsonString="";
        try
        {
            jsonString = mapper.writeValueAsString(mensajeError);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return jsonString;
    }
}

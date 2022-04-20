package com.carloscastillodeveloper.tienda.producto.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "tbl_categorias")
@AllArgsConstructor
@NoArgsConstructor
@Builder
/*anotacion lombok ayuda a generar getter setter equals y to string de los atributos de la clase*/
@Data
public class Categoria {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
}

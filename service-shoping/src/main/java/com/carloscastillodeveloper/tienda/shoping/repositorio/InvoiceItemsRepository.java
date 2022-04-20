package com.carloscastillodeveloper.tienda.shoping.repositorio;


import com.carloscastillodeveloper.tienda.shoping.repositorio.entity.InvoiceItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceItemsRepository extends JpaRepository<InvoiceItem,Long> {
}

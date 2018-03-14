package com.jsm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jsm.domain.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido,Long> {

}

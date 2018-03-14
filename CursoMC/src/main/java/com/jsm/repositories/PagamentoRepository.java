package com.jsm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jsm.domain.Pagamento;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento,Long> {

}

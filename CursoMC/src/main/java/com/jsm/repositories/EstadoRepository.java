package com.jsm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jsm.domain.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado,Long> {

}
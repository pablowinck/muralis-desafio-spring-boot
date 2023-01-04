package com.github.pablowinck.muralisdesafiospringboot.outbound.repository;

import com.github.pablowinck.muralisdesafiospringboot.core.domain.entity.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    Page<Cliente> findByNomeLikeIgnoreCase(String nome,
                                           Pageable pageable);
}
package com.example.repo;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entities.NotaPedido;

@Repository
public interface IPedidoRepository extends JpaRepository<NotaPedido, Serializable>{

}

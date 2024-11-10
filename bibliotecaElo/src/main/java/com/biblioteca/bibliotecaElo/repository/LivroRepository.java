package com.biblioteca.bibliotecaElo.repository;

import com.biblioteca.bibliotecaElo.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LivroRepository extends JpaRepository<Livro, Long> {
    List<Livro> findByCategoriaAndIdNotIn(String categoria, List<Long> ids);
}

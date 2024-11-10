package com.biblioteca.bibliotecaElo.repository;

import com.biblioteca.bibliotecaElo.model.Emprestimo;
import com.biblioteca.bibliotecaElo.model.Livro;
import com.biblioteca.bibliotecaElo.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {
    List<Emprestimo> findByUsuario(Usuario usuario);
    boolean existsByLivroAndStatus(Livro livro, String status);
}

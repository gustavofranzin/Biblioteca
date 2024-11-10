package com.biblioteca.bibliotecaElo.service;

import com.biblioteca.bibliotecaElo.model.Emprestimo;
import com.biblioteca.bibliotecaElo.model.Livro;
import com.biblioteca.bibliotecaElo.model.Usuario;
import com.biblioteca.bibliotecaElo.repository.EmprestimoRepository;
import com.biblioteca.bibliotecaElo.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RecomendacaoService {

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @Autowired
    private LivroRepository livroRepository;

    public List<Livro> recomendarLivros(Usuario usuario) {
        List<Emprestimo> emprestimos = emprestimoRepository.findByUsuario(usuario);

        Set<String> categorias = emprestimos.stream()
                .map(emprestimo -> emprestimo.getLivro().getCategoria())
                .collect(Collectors.toSet());

        List<Long> idsEmprestados = emprestimos.stream()
                .map(emprestimo -> emprestimo.getLivro().getId())
                .collect(Collectors.toList());

        return categorias.stream()
                .flatMap(categoria -> livroRepository.findByCategoriaAndIdNotIn(categoria, idsEmprestados).stream())
                .collect(Collectors.toList());
    }
}

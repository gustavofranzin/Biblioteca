package com.biblioteca.bibliotecaElo.service;

import com.biblioteca.bibliotecaElo.model.Emprestimo;
import com.biblioteca.bibliotecaElo.model.Livro;
import com.biblioteca.bibliotecaElo.model.Usuario;
import com.biblioteca.bibliotecaElo.repository.EmprestimoRepository;
import com.biblioteca.bibliotecaElo.repository.LivroRepository;
import com.biblioteca.bibliotecaElo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmprestimoService {

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private LivroRepository livroRepository;

    public Emprestimo createLoan(Long userId, Long bookId, Emprestimo loanDetails) {
        Usuario user = usuarioRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o ID: " + userId));
        Livro book = livroRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado com o ID: " + bookId));


        if (emprestimoRepository.existsByLivroAndStatus(book, "EMPRESTADO")) {
            throw new RuntimeException("Este livro já está emprestado.");
        }

        loanDetails.setUsuario(user);
        loanDetails.setLivro(book);
        loanDetails.setStatus("EMPRESTADO");
        return emprestimoRepository.save(loanDetails);
    }

    public List<Emprestimo> getAllLoans() {
        return emprestimoRepository.findAll();
    }

    public Emprestimo updateLoan(Long id, Emprestimo loanDetails) {
        Emprestimo loan = emprestimoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empréstimo não encontrado com o ID: " + id));

        loan.setStatus(loanDetails.getStatus());
        loan.setDataDevolucao(loanDetails.getDataDevolucao());
        return emprestimoRepository.save(loan);
    }
}

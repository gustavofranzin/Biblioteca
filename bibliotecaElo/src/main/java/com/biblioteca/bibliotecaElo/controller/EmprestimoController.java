package com.biblioteca.bibliotecaElo.controller;

import com.biblioteca.bibliotecaElo.model.Emprestimo;
import com.biblioteca.bibliotecaElo.repository.EmprestimoRepository;
import com.biblioteca.bibliotecaElo.repository.LivroRepository;
import com.biblioteca.bibliotecaElo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emprestimos")
public class EmprestimoController {

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private LivroRepository livroRepository;

    @GetMapping
    public List<Emprestimo> listarEmprestimos() {
        return emprestimoRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Emprestimo> criarEmprestimo(@RequestBody Emprestimo emprestimo) {
        if (usuarioRepository.existsById(emprestimo.getUsuario().getId()) &&
                livroRepository.existsById(emprestimo.getLivro().getId())) {
            return ResponseEntity.ok(emprestimoRepository.save(emprestimo));
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Emprestimo> atualizarEmprestimo(@PathVariable Long id, @RequestBody Emprestimo emprestimo) {
        return emprestimoRepository.findById(id)
                .map(emprestimoExistente -> {
                    emprestimoExistente.setDataDevolucao(emprestimo.getDataDevolucao());
                    emprestimoExistente.setStatus(emprestimo.getStatus());
                    Emprestimo emprestimoAtualizado = emprestimoRepository.save(emprestimoExistente);
                    return ResponseEntity.ok(emprestimoAtualizado);
                })
                .orElse(ResponseEntity.notFound().build());
    }
}

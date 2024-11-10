package com.biblioteca.bibliotecaElo.controller;

import com.biblioteca.bibliotecaElo.model.Livro;
import com.biblioteca.bibliotecaElo.model.Usuario;
import com.biblioteca.bibliotecaElo.service.RecomendacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recomendacao")
public class RecomendacaoController {

    @Autowired
    private RecomendacaoService recomendacaoService;

    @GetMapping("/{usuarioId}")
    public ResponseEntity<List<Livro>> recomendarLivros(@PathVariable Long usuarioId) {
        Usuario usuario = new Usuario();
        usuario.setId(usuarioId);

        List<Livro> recomendacoes = recomendacaoService.recomendarLivros(usuario);
        return ResponseEntity.ok(recomendacoes);
    }
}

package com.biblioteca.bibliotecaElo.service;

import com.biblioteca.bibliotecaElo.model.Livro;
import com.biblioteca.bibliotecaElo.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    public Livro createBook(Livro book) {
        return livroRepository.save(book);
    }

    public List<Livro> getAllBooks() {
        return livroRepository.findAll();
    }

    public Optional<Livro> getBookById(Long id) {
        return livroRepository.findById(id);
    }

    public Livro updateBook(Long id, Livro bookDetails) {
        Livro book = livroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado com o ID: " + id));

        book.setTitulo(bookDetails.getTitulo());
        book.setAutor(bookDetails.getAutor());
        book.setIsbn(bookDetails.getIsbn());
        book.setDataPublicacao(bookDetails.getDataPublicacao());
        book.setCategoria(bookDetails.getCategoria());

        return livroRepository.save(book);
    }

    public void deleteBook(Long id) {
        livroRepository.deleteById(id);
    }
}

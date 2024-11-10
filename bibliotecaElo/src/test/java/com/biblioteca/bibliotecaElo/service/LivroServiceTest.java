package com.biblioteca.bibliotecaElo.service;

import com.biblioteca.bibliotecaElo.model.Livro;
import com.biblioteca.bibliotecaElo.repository.LivroRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LivroServiceTest {

    @InjectMocks
    private LivroService livroService;

    @Mock
    private LivroRepository livroRepository;

    private Livro livro;

    @BeforeEach
    void setUp() {
        livro = new Livro();
        livro.setId(1L);
        livro.setTitulo("O Senhor dos Anéis");
        livro.setAutor("J.R.R. Tolkien");
        livro.setIsbn("123456789");
        livro.setDataPublicacao(LocalDate.parse("1954-07-29"));
        livro.setCategoria("Fantasia");
    }

    @Test
    void testCreateBook() {
        when(livroRepository.save(livro)).thenReturn(livro);

        Livro createdBook = livroService.createBook(livro);

        assertNotNull(createdBook);
        assertEquals("O Senhor dos Anéis", createdBook.getTitulo());
        verify(livroRepository, times(1)).save(livro);
    }

    @Test
    void testGetAllBooks() {
        List<Livro> livros = Arrays.asList(livro);
        when(livroRepository.findAll()).thenReturn(livros);

        List<Livro> allBooks = livroService.getAllBooks();

        assertNotNull(allBooks);
        assertEquals(1, allBooks.size());
        verify(livroRepository, times(1)).findAll();
    }

    @Test
    void testGetBookById() {
        when(livroRepository.findById(1L)).thenReturn(Optional.of(livro));

        Optional<Livro> foundBook = livroService.getBookById(1L);

        assertTrue(foundBook.isPresent());
        assertEquals("O Senhor dos Anéis", foundBook.get().getTitulo());
        verify(livroRepository, times(1)).findById(1L);
    }

    @Test
    void testUpdateBook() {
        when(livroRepository.findById(1L)).thenReturn(Optional.of(livro));
        when(livroRepository.save(livro)).thenReturn(livro);

        Livro updatedDetails = new Livro();
        updatedDetails.setTitulo("O Hobbit");
        updatedDetails.setAutor("J.R.R. Tolkien");
        updatedDetails.setIsbn("000000001");
        updatedDetails.setDataPublicacao(LocalDate.parse("1937-09-21"));
        updatedDetails.setCategoria("Fantasia");

        Livro updatedBook = livroService.updateBook(1L, updatedDetails);

        assertNotNull(updatedBook);
        assertEquals("O Hobbit", updatedBook.getTitulo());
        verify(livroRepository, times(1)).findById(1L);
        verify(livroRepository, times(1)).save(livro);
    }

    @Test
    void testDeleteBook() {
        doNothing().when(livroRepository).deleteById(1L);

        livroService.deleteBook(1L);

        verify(livroRepository, times(1)).deleteById(1L);
    }
}

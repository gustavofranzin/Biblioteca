package com.biblioteca.bibliotecaElo.service;

import com.biblioteca.bibliotecaElo.model.Usuario;
import com.biblioteca.bibliotecaElo.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

    @InjectMocks
    private UsuarioService usuarioService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Test
    public void testCreateUser() {
        Usuario usuario = new Usuario();
        usuario.setNome("Test User");
        usuario.setEmail("test@example.com");

        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        Usuario createdUser = usuarioService.createUser(usuario);

        assertNotNull(createdUser);
        assertEquals("Test User", createdUser.getNome());
        assertEquals("test@example.com", createdUser.getEmail());
        verify(usuarioRepository, times(1)).save(usuario);
    }

    @Test
    public void testGetAllUsers() {
        List<Usuario> usuarios = new ArrayList<>();
        Usuario usuario1 = new Usuario();
        Usuario usuario2 = new Usuario();
        usuarios.add(usuario1);
        usuarios.add(usuario2);

        when(usuarioRepository.findAll()).thenReturn(usuarios);

        List<Usuario> result = usuarioService.getAllUsers();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(usuarioRepository, times(1)).findAll();
    }

    @Test
    public void testGetUserById() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNome("Test User");

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));

        Optional<Usuario> result = usuarioService.getUserById(1L);

        assertTrue(result.isPresent());
        assertEquals("Test User", result.get().getNome());
        verify(usuarioRepository, times(1)).findById(1L);
    }

    @Test
    public void testUpdateUser() {
        Usuario existingUser = new Usuario();
        existingUser.setId(1L);
        existingUser.setNome("Old Name");

        Usuario updatedUserDetails = new Usuario();
        updatedUserDetails.setNome("Updated Name");
        updatedUserDetails.setEmail("updated@example.com");

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(existingUser));
        when(usuarioRepository.save(existingUser)).thenReturn(existingUser);

        Usuario updatedUser = usuarioService.updateUser(1L, updatedUserDetails);

        assertNotNull(updatedUser);
        assertEquals("Updated Name", updatedUser.getNome());
        assertEquals("updated@example.com", updatedUser.getEmail());
        verify(usuarioRepository, times(1)).findById(1L);
        verify(usuarioRepository, times(1)).save(existingUser);
    }

    @Test
    public void testDeleteUser() {
        Long userId = 1L;

        doNothing().when(usuarioRepository).deleteById(userId);

        usuarioService.deleteUser(userId);

        verify(usuarioRepository, times(1)).deleteById(userId);
    }
}

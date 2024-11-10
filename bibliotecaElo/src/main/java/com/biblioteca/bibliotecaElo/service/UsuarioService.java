package com.biblioteca.bibliotecaElo.service;

import com.biblioteca.bibliotecaElo.model.Usuario;
import com.biblioteca.bibliotecaElo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository userRepository;

    public Usuario createUser(Usuario user) {
        return userRepository.save(user);
    }

    public List<Usuario> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<Usuario> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public Usuario updateUser(Long id, Usuario userDetails) {
        Usuario user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o ID: " + id));

        user.setNome(userDetails.getNome());
        user.setEmail(userDetails.getEmail());
        user.setDataCadastro(userDetails.getDataCadastro());
        user.setTelefone(userDetails.getTelefone());

        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}

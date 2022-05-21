package com.APIsecurity.service;

import com.APIsecurity.model.UsuarioModel;
import com.APIsecurity.repository.UsuarioRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author Miguel Castro
 */
@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    PasswordEncoder encoder;

    public UsuarioModel inserir(UsuarioModel usuarioModel) {

        usuarioModel.setSenha(encoder.encode(usuarioModel.getSenha()));
        return usuarioRepository.save(usuarioModel);
    }

    public List<UsuarioModel> listar() {

        return usuarioRepository.findAll();
    }

    public boolean validarSenha(String login, String senha) {

        Optional<UsuarioModel> usuarioModel = usuarioRepository.findByLogin(login);
        if (!usuarioModel.isPresent()) {
            return false;
        }
        boolean validacao = encoder.matches(senha, usuarioModel.get().getSenha());
        return validacao;
    }
}

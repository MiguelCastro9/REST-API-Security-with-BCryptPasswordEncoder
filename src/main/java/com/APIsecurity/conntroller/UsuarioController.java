package com.APIsecurity.conntroller;

import com.APIsecurity.model.UsuarioModel;
import com.APIsecurity.repository.UsuarioRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Miguel Castro
 */
@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PasswordEncoder encoder;

    @GetMapping(value = "/lista", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UsuarioModel>> lista() {

        return ResponseEntity.ok(usuarioRepository.findAll());
    }

    @PostMapping(value = "/insere", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UsuarioModel> insere(@RequestBody UsuarioModel usuarioModel) {

        usuarioModel.setSenha(encoder.encode(usuarioModel.getSenha()));
        return ResponseEntity.ok(usuarioRepository.save(usuarioModel));
    }
    
    @GetMapping("/validaSenha")
    public ResponseEntity<Boolean> validaSenha(@RequestParam String login, @RequestParam String senha) {
        
        Optional<UsuarioModel> optionalUsuario = usuarioRepository.findByLogin(login);
        if (optionalUsuario.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
        }
        boolean valid = encoder.matches(senha, optionalUsuario.get().getSenha()); 
        HttpStatus status = (valid) ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;
        return ResponseEntity.status(status).body(valid);
    }
}

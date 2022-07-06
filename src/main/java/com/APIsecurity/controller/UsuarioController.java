package com.APIsecurity.controller;

import com.APIsecurity.model.UsuarioModel;
import com.APIsecurity.service.UsuarioService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    UsuarioService usarioService;
    
    @GetMapping(value = "/lista", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UsuarioModel>> lista() {

        return ResponseEntity.ok(usarioService.listar());
    }

    @PostMapping(value = "/insere", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UsuarioModel> insere(@RequestBody UsuarioModel usuarioModel) {

        return ResponseEntity.ok(usarioService.inserir(usuarioModel));
    }
    
    @GetMapping("/validaSenha")
    public ResponseEntity<Boolean> validaSenha(@RequestParam String login, @RequestParam String senha) {
        
        HttpStatus status = (usarioService.validarSenha(login, senha)) ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;
        return ResponseEntity.status(status).build();
    }
}

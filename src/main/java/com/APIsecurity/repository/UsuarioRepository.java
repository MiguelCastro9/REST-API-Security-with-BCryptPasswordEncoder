package com.APIsecurity.repository;

import com.APIsecurity.model.UsuarioModel;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Miguel Castro
 */
public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long> {
    
    public Optional<UsuarioModel> findByLogin(String login);
}

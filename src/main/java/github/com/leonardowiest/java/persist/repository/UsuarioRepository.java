package github.com.leonardowiest.java.persist.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import github.com.leonardowiest.java.persist.domain.Usuario;
import github.com.leonardowiest.java.persist.repository.custom.UsuarioRepositoryCustom;

@Repository
public interface UsuarioRepository extends UsuarioRepositoryCustom, PagingAndSortingRepository<Usuario, Long> {

}

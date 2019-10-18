package github.com.leonardowiest.java.persist.repository.impl;

import static github.com.leonardowiest.java.persist.constants.JavaPersistConstants.JAVA_PERSIST_PERSISTENCE_UNIT_NAME;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import github.com.leonardowiest.java.persist.domain.Usuario;
import github.com.leonardowiest.java.persist.repository.custom.UsuarioRepositoryCustom;

@Repository
public class UsuarioRepositoryImpl extends QuerydslRepositorySupport implements UsuarioRepositoryCustom {

    public UsuarioRepositoryImpl() {
        super(Usuario.class);
    }

    @Override
    @PersistenceContext(unitName = JAVA_PERSIST_PERSISTENCE_UNIT_NAME)
    public void setEntityManager(EntityManager entityManager) {
        super.setEntityManager(entityManager);
    }

}

package br.com.estudos.persistence;

import br.com.estudos.model.Cliente;

import javax.persistence.EntityManager;
import java.util.Optional;

public class ClienteRepository {

    private EntityManager entityManager;

    public ClienteRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Optional<Cliente> findById(Integer id) {
        return Optional.of(entityManager.find(Cliente.class, id));
    }

    public void save(Cliente cliente) {
        entityManager.persist(cliente);
    }

    public void delete(Integer id) {
        findById(id).ifPresent(c -> entityManager.remove(c));
    }

    public void update(Integer id, String newName) {
     findById(id).ifPresent(c -> c.setNome(newName));
    }
}

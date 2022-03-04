package br.com.estudos;

import br.com.estudos.model.Cliente;
import br.com.estudos.persistence.ClienteRepository;
import br.com.estudos.wrapper.EntityManagerWrapper;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.Optional;

public class App {
    public static void main(String[] args) {
        EntityManagerWrapper wrapper = new EntityManagerWrapper();
        EntityManagerFactory factory = wrapper.getEntityManagerFactory();
        EntityManager entityManager = factory.createEntityManager();

        System.out.println("Iniciando processamento para consulta de cliente utilizando JPA...");
        ClienteRepository clienteRepository = new ClienteRepository(entityManager);
        Optional<Cliente> cliente = clienteRepository.findById(1);
        cliente.ifPresent(System.out::println);

        System.out.println("Abrindo transação para persistir novo cliente");
        begin(entityManager);

        Cliente novoCliente = new Cliente();
        novoCliente.setNome("Buturussu Pneus");
        clienteRepository.save(novoCliente);

        clienteRepository.delete(1);
        System.out.println("Enviando sinal de commit para finalizar a transação e registrar cliente");

        clienteRepository.update(1, "Mercado Nagumo");
        commit(entityManager);

        closeEntityManager(entityManager);
        closeEntityManagerFactory(factory);
        System.out.println("Processamento finalizado com sucesso");
    }

    private static void  closeEntityManagerFactory(EntityManagerFactory factory) {
         factory.close();
    }

    private static void closeEntityManager(EntityManager entityManager) {
        entityManager.close();
    }

    private static void begin(EntityManager entityManager) {
        entityManager.getTransaction().begin();
    }

    private static void commit(EntityManager entityManager) {
        entityManager.getTransaction().commit();
    }
}

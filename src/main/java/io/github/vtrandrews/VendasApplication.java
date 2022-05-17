package io.github.vtrandrews;

import io.github.vtrandrews.domain.entity.Cliente;
import io.github.vtrandrews.domain.repositorio.Clientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class VendasApplication {

    @Bean
    public CommandLineRunner init(@Autowired Clientes clientes){
        return args -> {
            System.out.println("Salvando Clientes...");
            clientes.salvar(new Cliente("Vitor Andrews"));
            clientes.salvar(new Cliente("Ricaro Alexandre"));

            System.out.println("Buscando Todos Os Clientes...");
            List<Cliente> todosClientes = clientes.obterTodos();
            todosClientes.forEach(System.out::println);

            System.out.println("Atualizando Clientes...");
            todosClientes.forEach(c -> {
                c.setNome(c.getNome() + " Atualizado.");
                clientes.atualizar(c);
            });

            System.out.println("Buscando Clientes Por Nome...");
            clientes.buscarPorNome("Vi").forEach(System.out::println);

            System.out.println("Deletando Clientes...");
            clientes.obterTodos().forEach(c -> {
               clientes.deletar(c);
            });

            System.out.println("Buscando Todos Os Clientes...");
            todosClientes = clientes.obterTodos();
            if(todosClientes.isEmpty()){
                System.out.println("Nenhum Cliente Encontrado!");
            }else{
                todosClientes.forEach(System.out::println);
            }
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}

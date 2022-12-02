package br.com.devdojo.awesome.devdojospringboot.repository;

import br.com.devdojo.awesome.devdojospringboot.model.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StudentRepository extends CrudRepository<Student, Long> {
    List<Student> findByNameContainingIgnoreCase(String name);
}

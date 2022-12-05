package br.com.devdojo.awesome.devdojospringboot.repository;

import br.com.devdojo.awesome.devdojospringboot.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByNameContainingIgnoreCase(String name);
}

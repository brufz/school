package br.com.devdojo.awesome.devdojospringboot;

import br.com.devdojo.awesome.devdojospringboot.model.Student;
import br.com.devdojo.awesome.devdojospringboot.repository.StudentRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Objects;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class StudentRepositoryTest {
    @Autowired
    private StudentRepository studentRepository;

    @Test
    @DisplayName("Deve retornar um estudante")
    public void shouldReturnOneStudent(){
        studentRepository.deleteAll();
        Student s1 = new Student("Joao", "joao@gmail.com");
        studentRepository.save(s1);
        List<Student> nameStudent = studentRepository.findByNameContainingIgnoreCase("Joao");
        Assertions.assertEquals(1,nameStudent.size());
        Assertions.assertTrue(Objects.equals(nameStudent.get(0).getName(), "Joao"));
    }

    @Test
    @DisplayName("Lista de usuários")
    public void return2Users(){
        studentRepository.deleteAll();
        Student student1 = studentRepository.save(new Student("Bruna da Silva", "bfz@gmail.com"));
        Student student2 = studentRepository.save(new Student("Joao", "joao@gmail.com"));
        Student student3 = studentRepository.save(new Student("Fatima", "fatima@gmail.com"));
        Student student4 = studentRepository.save(new Student("Fatima da Silva", "fatimasilva@gmail.com"));
        List<Student> listStudents = studentRepository.findByNameContainingIgnoreCase("silva");
        Assertions.assertEquals(2, listStudents.size());
        Assertions.assertTrue(listStudents.get(0).getName().equals("Bruna da Silva"));
        Assertions.assertTrue(listStudents.get(1).getName().equals("Fatima da Silva"));
    }



    @Test
    @DisplayName("Atualizar usuário")
    public void updateStudent(){
        studentRepository.deleteAll();
        Student s1 = new Student("Heitor", "hheitor@gmail.com");
        s1.setEmail("heitor@gmail.com");
        studentRepository.save(s1);
        List<Student> listStudent = studentRepository.findAll();
        Assertions.assertEquals(1, listStudent.size());
        Assertions.assertTrue(listStudent.get(0).getEmail().equals("heitor@gmail.com"));
    }



}

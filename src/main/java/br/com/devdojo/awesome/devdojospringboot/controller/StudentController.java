package br.com.devdojo.awesome.devdojospringboot.controller;

import br.com.devdojo.awesome.devdojospringboot.error.ResourceNotFoundException;
import br.com.devdojo.awesome.devdojospringboot.model.Student;
import br.com.devdojo.awesome.devdojospringboot.repository.StudentRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentRepository studentDAO;


    @GetMapping
    public ResponseEntity<?> listAll(Pageable pageable){
        return new ResponseEntity<>(studentDAO.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable("id") Long id){
        verifyIfStudentExist(id);
        return new ResponseEntity<>(studentDAO.findById(id), HttpStatus.OK);

    }


    @GetMapping("/findByName/{name}")
    public ResponseEntity<?> findStudentByName(@PathVariable String name){
        return new ResponseEntity<>(studentDAO.findByNameContainingIgnoreCase(name), HttpStatus.OK);
    }

    @Transactional(rollbackOn = Exception.class)
    @PostMapping
    public ResponseEntity<?> postStudent(@Valid @RequestBody Student student){
        studentDAO.save(student);
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id){
        verifyIfStudentExist(id);
        studentDAO.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<?> updateStudent(@Valid @RequestBody Student student){
        verifyIfStudentExist(student.getId());
        studentDAO.save(student);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public void verifyIfStudentExist(Long id){
        if (studentDAO.findById(id).isEmpty()){
            throw new ResourceNotFoundException("Student not found for id: " + id);
        }
    }


}

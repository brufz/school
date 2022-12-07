package br.com.devdojo.awesome.devdojospringboot.controller;

import br.com.devdojo.awesome.devdojospringboot.model.UserModel;
import br.com.devdojo.awesome.devdojospringboot.service.UserServiceImplements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserServiceImplements userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserServiceImplements userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserModel createNewUser(@Valid @RequestBody UserModel user){
        String criptPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(criptPassword);
        return userService.saveUser(user);
    }

//    @GetMapping
//    public ResponseEntity<?> findAllUsers(){
//        return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
//    }
//
//    @PostMapping
//    public ResponseEntity<?> createNewUser(@Valid @RequestBody UserModel user){
//        return new ResponseEntity<>(userRepository.save(user), HttpStatus.CREATED);
//    }
}

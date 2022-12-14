package br.com.devdojo.awesome.devdojospringboot.controller;

import br.com.devdojo.awesome.devdojospringboot.dto.CredentialsDTO;
import br.com.devdojo.awesome.devdojospringboot.dto.TokenDTO;
import br.com.devdojo.awesome.devdojospringboot.error.PasswordIncorretException;
import br.com.devdojo.awesome.devdojospringboot.model.UserModel;
import br.com.devdojo.awesome.devdojospringboot.service.JwtService;
import br.com.devdojo.awesome.devdojospringboot.service.UserServiceImplements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserServiceImplements userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Autowired
    public UserController(UserServiceImplements userService, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserModel createNewUser(@Valid @RequestBody UserModel user){
        String criptPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(criptPassword);
        return userService.saveUser(user);
    }

    @GetMapping
    public ResponseEntity<?> findAllUsers(){
        return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK);
    }

    @PostMapping("/auth")
    public TokenDTO authentication(@RequestBody CredentialsDTO credentialsDTO){
        try {
            UserModel userBuild = UserModel
                    .UserModelBuilder
                    .newBuilder()
                    .login(credentialsDTO.getLogin())
                    .password(credentialsDTO.getPassword())
                    .build();
            UserDetails authentication = userService.authentication(userBuild);
            String token = jwtService.createToken(userBuild);
            return new TokenDTO(userBuild.getLogin(), token);
        }catch (UsernameNotFoundException e){
            throw new UsernameNotFoundException("Username not found");
        } catch (PasswordIncorretException e){
            throw new PasswordIncorretException("Password is incorect");
        }
    }
}

package br.com.devdojo.awesome.devdojospringboot.service;

import br.com.devdojo.awesome.devdojospringboot.error.PasswordIncorretException;
import br.com.devdojo.awesome.devdojospringboot.model.UserModel;
import br.com.devdojo.awesome.devdojospringboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserServiceImplements implements UserDetailsService {
    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public UserModel saveUser(UserModel user){
        return userRepository.save(user);
    }

    @Transactional
    public List<UserModel> findAllUsers(){
        return userRepository.findAll();
    }

    public UserDetails authentication(UserModel user){
        UserDetails userDetails = loadUserByUsername(user.getLogin());
        boolean matches = encoder.matches(user.getPassword(), userDetails.getPassword());
        if(matches){
            return userDetails;
        }
        throw new PasswordIncorretException("Password is invalid");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel user = userRepository.findByLogin(username).orElseThrow(() ->
                new UsernameNotFoundException("User not found in the database"));
        String [] roles = user.isAdmin() ? new String[]{"ADMIN", "USER"} : new String[]{"USER"};
        return User.builder()
                .username(user.getLogin())
                .password(user.getPassword())
                .roles(roles)
                .build();
    }
}

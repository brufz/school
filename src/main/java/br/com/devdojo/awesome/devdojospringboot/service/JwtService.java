package br.com.devdojo.awesome.devdojospringboot.service;

import br.com.devdojo.awesome.devdojospringboot.DevdojoSpringbootApplication;
import br.com.devdojo.awesome.devdojospringboot.model.UserModel;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class JwtService {
    @Value("${security.jwt.expiration}")
    private String expiration;
    @Value("${security.jwt.signkey}")
    private String signKey;

    public String createToken(UserModel userModel){
        long expString = Long.valueOf(expiration); //transformando em long
        LocalDateTime dateHourExpiration = LocalDateTime.now().plusMinutes(expString); //somando a data e hora atual com o tempo de exp
        Instant instant = dateHourExpiration.atZone(ZoneId.systemDefault()).toInstant(); //transformando LocalDateTime em instant
        Date date = Date.from(instant);
        return Jwts
                .builder()
                .setSubject(userModel.getLogin())
                .setExpiration(date)
                .signWith(SignatureAlgorithm.HS512, signKey)
                .compact();

    }
    private Claims getClaims(String token) throws ExpiredJwtException {
        return Jwts
                .parser()
                .setSigningKey(signKey)
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean isTokenValid(String token){
        try {
            Claims claims = getClaims(token);
            Date expDate = claims.getExpiration();
            LocalDateTime date = expDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();//transformando Date em LocalDateTime
            return !LocalDateTime.now().isAfter(date);
        }catch (Exception e){
            return false;
        }
    }

    public String getUserLogin(String token) throws ExpiredJwtException {
        return (String) getClaims(token).getSubject();
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(DevdojoSpringbootApplication.class);
        System.out.println("Teste JWT");
        JwtService service = run.getBean(JwtService.class);
        UserModel usuario = UserModel.UserModelBuilder.newBuilder().login("fulano").build();
        String token = service.createToken(usuario);
        System.out.println(token);

        boolean tokenValid = service.isTokenValid(token);
        System.out.println("Token valid: " + tokenValid);

        System.out.println("User login: " + service.getUserLogin(token));

    }

}



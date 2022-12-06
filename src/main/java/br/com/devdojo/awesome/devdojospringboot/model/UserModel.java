package br.com.devdojo.awesome.devdojospringboot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="tb_user")
public class UserModel extends AbstractEntity{
    @NotEmpty
    @Column(unique = true)
    private String username;
    @NotEmpty
    private String password;

    @Column
    private boolean admin;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean admin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        admin = admin;
    }
}

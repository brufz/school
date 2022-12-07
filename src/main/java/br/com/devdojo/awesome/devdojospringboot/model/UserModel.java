package br.com.devdojo.awesome.devdojospringboot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="tb_user")
public class UserModel extends AbstractEntity{
    @NotEmpty(message = "Login cannot be empty")
    @Column(unique = true)
    private String login;
    @NotEmpty(message = "password cannot be empty")
    private String password;
    @Column
    private boolean admin;

    public UserModel() {
    }

    public UserModel(String login, String password, boolean admin) {
        this.login = login;
        this.password = password;
        this.admin = admin;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}

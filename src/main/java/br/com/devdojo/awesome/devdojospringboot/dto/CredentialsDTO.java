package br.com.devdojo.awesome.devdojospringboot.dto;

public class CredentialsDTO {
    private String login;
    private String password;

    public CredentialsDTO() {
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
}

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


    public static final class UserModelBuilder {
        private Long id;
        private @NotEmpty(message = "Login cannot be empty") String login;
        private @NotEmpty(message = "password cannot be empty") String password;
        private boolean admin;

        private UserModelBuilder() {
        }

        public static UserModelBuilder newBuilder() {
            return new UserModelBuilder();
        }

        public UserModelBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public UserModelBuilder login(String login) {
            this.login = login;
            return this;
        }

        public UserModelBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UserModelBuilder admin(boolean admin) {
            this.admin = admin;
            return this;
        }

        public UserModel build() {
            UserModel userModel = new UserModel();
            userModel.setId(id);
            userModel.setLogin(login);
            userModel.setPassword(password);
            userModel.setAdmin(admin);
            return userModel;
        }
    }
}

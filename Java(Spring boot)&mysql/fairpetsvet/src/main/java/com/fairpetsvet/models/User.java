package com.fairpetsvet.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue
    private long id;

    @NotEmpty
    private String email;

    @NotEmpty
    private String password;

    public User(){}
    public User(String email,String password){
        setEmail(email);
        setPassword(password);
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}

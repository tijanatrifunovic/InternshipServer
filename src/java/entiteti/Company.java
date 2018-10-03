/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entiteti;

/**
 *
 * @author tijana.trifunovic
 */
public class Company {
    private int idUser;
    private String email;
    private String password;
    private String name;
    private String address;

    public Company() {
    }

    public Company(int idUser, String email, String password, String name, String address) {
        this.idUser = idUser;
        this.email = email;
        this.password = password;
        this.name = name;
        this.address = address;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Company{" + "idUser=" + idUser + ", email=" + email + ", password=" + password + ", name=" + name + ", address=" + address + '}';
    }

    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entiteti;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author tijana.trifunovic
 */
@XmlRootElement
public class CompanyResponse {
    private int idUser;
    private String address;
    private String name;
    private String email;

    public CompanyResponse() {
    }

    public CompanyResponse(int idUser, String address, String name, String email) {
        this.idUser = idUser;
        this.address = address;
        this.name = name;
        this.email = email;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "CompanyResponse{" + "idUser=" + idUser + ", address=" + address + ", name=" + name + ", email=" + email + '}';
    }

    
 
    
    
    
}

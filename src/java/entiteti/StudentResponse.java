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
public class StudentResponse {
    private int idUser;
    private String indexNumber;
    private String name;
    private String surname;
    private String email;
    private int idDepartment;
    private String department;

    public StudentResponse() {
    }

    public StudentResponse(int idUser, String indexNumber, String name, String surname, String email, int idDepartment, String department) {
        this.idUser = idUser;
        this.indexNumber = indexNumber;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.idDepartment = idDepartment;
        this.department = department;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getIndexNumber() {
        return indexNumber;
    }

    public void setIndexNumber(String indexNumber) {
        this.indexNumber = indexNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getIdDepartment() {
        return idDepartment;
    }

    public void setIdDepartment(int idDepartment) {
        this.idDepartment = idDepartment;
    }

    @Override
    public String toString() {
        return "StudentResponse{" + "idUser=" + idUser + ", indexNumber=" + indexNumber + ", name=" + name + ", surname=" + surname + ", email=" + email + ", idDepartment=" + idDepartment + ", department=" + department + '}';
    }

 
    
    
    
}

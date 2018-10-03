/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entiteti;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Tijana
 */
@XmlRootElement
public class Student {
    private String indexNumber;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String idDepartment;

    public Student() {
    }

    public Student(String indexNumber, String name, String surname, String email, String password, String idDepartment) {
        this.indexNumber = indexNumber;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.idDepartment = idDepartment;
    }

    public String getIndexNumber() {
        return indexNumber;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getIdDepartment() {
        return idDepartment;
    }

    public void setIndexNumber(String indexNumber) {
        this.indexNumber = indexNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setIdDepartment(String idDepartment) {
        this.idDepartment = idDepartment;
    }

    @Override
    public String toString() {
        return "Student{" + "indexNumber=" + indexNumber + ", name=" + name + ", surname=" + surname + ", email=" + email + ", password=" + password + ", idDepartment=" + idDepartment + '}';
    }

    
}

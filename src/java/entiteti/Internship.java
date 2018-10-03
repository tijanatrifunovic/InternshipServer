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
public class Internship {    
    private int idInternship;
    private int idCompany;
    private String company;
    private String name;
    private int idDepartment;
    private String department;
    private String startDate;
    private String endDate;
    private String duration;
    private int taken;
    private int idstudent;
    private String studentName;
    private String studentSurname;
    private String indexNumber;
    private int done;
    private String description;

    public Internship() {
    }

    public Internship(int idInternship, int idCompany, String company, String name, int idDepartment, String department, String startDate, String endDate, String duration, int taken, int idstudent, String studentName, String studentSurname, String indexNumber, int done, String description) {
        this.idInternship = idInternship;
        this.idCompany = idCompany;
        this.company = company;
        this.name = name;
        this.idDepartment = idDepartment;
        this.department = department;
        this.startDate = startDate;
        this.endDate = endDate;
        this.duration = duration;
        this.taken = taken;
        this.idstudent = idstudent;
        this.studentName = studentName;
        this.studentSurname = studentSurname;
        this.indexNumber = indexNumber;
        this.done = done;
        this.description = description;
    }

    public Internship(int idInternship, int idCompany, String company, String name, int idDepartment, String department, String startDate, String endDate, String duration, int taken, int done, String description) {
        this.idInternship = idInternship;
        this.idCompany = idCompany;
        this.company = company;
        this.name = name;
        this.idDepartment = idDepartment;
        this.department = department;
        this.startDate = startDate;
        this.endDate = endDate;
        this.duration = duration;
        this.taken = taken;
        this.done = done;
        this.description = description;
    }
    
    public Internship(int idInternship, int idCompany, int idStudent, String name, int idDepartment, String department, String startDate, String endDate, String duration, int taken, int done, String description) {
        this.idInternship = idInternship;
        this.idCompany = idCompany;
        this.idstudent = idStudent;
        this.name = name;
        this.idDepartment = idDepartment;
        this.department = department;
        this.startDate = startDate;
        this.endDate = endDate;
        this.duration = duration;
        this.taken = taken;
        this.done = done;
        this.description = description;
    }
    

    public int getIdInternship() {
        return idInternship;
    }

    public void setIdInternship(int idInternship) {
        this.idInternship = idInternship;
    }

    public int getIdCompany() {
        return idCompany;
    }

    public void setIdCompany(int idCompany) {
        this.idCompany = idCompany;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdDepartment() {
        return idDepartment;
    }

    public void setIdDepartment(int idDepartment) {
        this.idDepartment = idDepartment;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public int getTaken() {
        return taken;
    }

    public void setTaken(int taken) {
        this.taken = taken;
    }

    public int getIdstudent() {
        return idstudent;
    }

    public void setIdstudent(int idstudent) {
        this.idstudent = idstudent;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentSurname() {
        return studentSurname;
    }

    public void setStudentSurname(String studentSurname) {
        this.studentSurname = studentSurname;
    }

    public String getIndexNumber() {
        return indexNumber;
    }

    public void setIndexNumber(String indexNumber) {
        this.indexNumber = indexNumber;
    }

    public int getDone() {
        return done;
    }

    public void setDone(int done) {
        this.done = done;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Internship{" + "idInternship=" + idInternship + ", idCompany=" + idCompany + ", company=" + company + ", name=" + name + ", idDepartment=" + idDepartment + ", department=" + department + ", startDate=" + startDate + ", endDate=" + endDate + ", duration=" + duration + ", taken=" + taken + ", idstudent=" + idstudent + ", studentName=" + studentName + ", studentSurname=" + studentSurname + ", indexNumber=" + indexNumber + ", done=" + done + ", description=" + description + '}';
    }

    
    
}

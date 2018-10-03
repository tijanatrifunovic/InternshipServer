package servisi;

import db.DB;
import entiteti.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Tijana Trifunovic
 */
@Path("student")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class StudentServis {

    @GET
    @Path("/{idStudent}")
    public Response getInternshipData(@PathParam("idStudent") int idStudent){
        try {
            String query = "SELECT i.idinternship, i.idcompany, c.name as company, i.name, i.iddepartment, d.name as department, i.startDate, i.endDate, i.duration, i.taken, i.idstudent, i.done, i.description FROM internship.internship i LEFT OUTER JOIN company c ON c.iduser = i.idcompany LEFT OUTER JOIN department d ON d.iddepartment = i.iddepartment WHERE i.idstudent = " + idStudent;
          
            Connection connection = DriverManager.getConnection(DB.dbName,DB.dbUsername,DB.dbPassword);
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
                       
            if(!rs.next()) return Response.status(404).build();
            
            Internship internship = new Internship(rs.getInt("idinternship"), rs.getInt("idcompany"), rs.getString("company"), rs.getString("name"), rs.getInt("iddepartment"), rs.getString("department"), rs.getString("startDate"), rs.getString("endDate"), rs.getString("duration"), rs.getInt("taken"), rs.getInt("done"), rs.getString("description"));
                       
            Response response = Response.status(200).entity(internship).build();
            
            return response;
        } catch (SQLException ex) {
            Logger.getLogger(StudentServis.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Response response = Response.status(404).build();
        return response;
    }
    
    
    @GET
    public Response getAllStudents() {
        try {
            String upit = "Select * from student";

            Connection connection = DriverManager.getConnection(DB.dbName, DB.dbUsername, DB.dbPassword);
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(upit);
            if (!rs.next()) {
                return Response.status(404).build();
            }
            rs.previous();

            List<Student> students = new ArrayList<>();
            while (rs.next()) {
                Student student = new Student();
//                student.set(rs.getInt("idstudent"));
                student.setName(rs.getString("name"));

                students.add(student);
            }
            GenericEntity<List<Student>> genericStudents = new GenericEntity<List<Student>>(students) {
            };
            return Response.status(200).entity(genericStudents).build();
        } catch (SQLException ex) {
            Logger.getLogger(StudentServis.class.getName()).log(Level.SEVERE, null, ex);
        }

        Response response = Response.status(404).build();
        return response;
    }

    @GET
    @Path("/departments")
    public Response getAllDepartments() {
        try {
            String query = "Select * from department";

            Connection connection = DriverManager.getConnection(DB.dbName, DB.dbUsername, DB.dbPassword);
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            if (!rs.next()) {
                return Response.status(404).build();
            }
            rs.previous();

            List<Department> departments = new ArrayList<>();
            while (rs.next()) {
                departments.add(new Department(rs.getInt("idDepartment"), rs.getString("name")));
            }
            GenericEntity<List<Department>> genericDepartments = new GenericEntity<List<Department>>(departments) {
            };
            return Response.status(200).entity(genericDepartments).build();
        } catch (SQLException ex) {
            Logger.getLogger(StudentServis.class.getName()).log(Level.SEVERE, null, ex);
        }

        Response response = Response.status(404).build();
        return response;
    }

    @POST
    public Response registerStudent(Student student) {
        if (student.getEmail().isEmpty() || student.getIdDepartment().isEmpty() || student.getIndexNumber().isEmpty() || student.getName().isEmpty() || student.getPassword().isEmpty() || student.getSurname().isEmpty()) {
            Response response = Response.status(400).build();
            return response;
        }
        try {
            Connection connection = DriverManager.getConnection(DB.dbName, DB.dbUsername, DB.dbPassword);
            Statement statement = connection.createStatement();

            String query = "SELECT COALESCE(MAX(idUser), 1) as id FROM user";
            ResultSet rs = statement.executeQuery(query);
            int idStudent = 1;
            if (rs.next()) {
                idStudent = rs.getInt("id") + 1;
            }

            query = "INSERT INTO user VALUES (" + idStudent + ", '" + student.getEmail() + "', '" + student.getPassword() + "', 1)";
            statement.executeUpdate(query);

            query = "INSERT INTO student VALUES (" + idStudent + ", '" + student.getName() + "', '" + student.getSurname() + "', '" + student.getIndexNumber() + "', " + student.getIdDepartment() + ")";
            statement.executeUpdate(query);

            Response response = Response.status(201).build();
            return response;
        } catch (SQLException ex) {
            Logger.getLogger(StudentServis.class.getName()).log(Level.SEVERE, null, ex);
        }

        Response response = Response.status(409).build();
        return response;
    }

    @POST
    @Path("/login")
    public Response login(LoginRequest student) {
        if (student.getEmail().isEmpty() || student.getPassword().isEmpty()) {
            Response response = Response.status(400).build();
            return response;
        }
        try {
            Connection connection = DriverManager.getConnection(DB.dbName, DB.dbUsername, DB.dbPassword);
            Statement statement = connection.createStatement();

            String query = "select u.iduser, u.email, s.name, s.surname, s.indexnumber, d.iddepartment, d.name as department from internship.user u left outer join student s on s.iduser = u.iduser left outer join department d on d.iddepartment = s.iddepartment WHERE email = '" + student.getEmail() + "' and password = '" + student.getPassword() + "' and idusertype = 1";
            ResultSet rs = statement.executeQuery(query);
            StudentResponse studentResponse = new StudentResponse();
            if (rs.next()) {

                studentResponse.setIdUser(rs.getInt("iduser"));
                studentResponse.setEmail(rs.getString("email"));
                studentResponse.setName(rs.getString("name"));
                studentResponse.setSurname(rs.getString("surname"));
                studentResponse.setIndexNumber(rs.getString("indexnumber"));
                studentResponse.setIdDepartment(rs.getInt("iddepartment"));
                studentResponse.setDepartment(rs.getString("department"));

            } else {
                Response response = Response.status(404).build();
                return response;
            }

            Response response = Response.status(200).entity(studentResponse).build();
            return response;
        } catch (SQLException ex) {
            Logger.getLogger(StudentServis.class.getName()).log(Level.SEVERE, null, ex);
        }

        Response response = Response.status(409).build();
        return response;
    }
//
//    @PUT
//    @Consumes("text/xml")
//    @Path("{id}")
//    public Response updateKamion(@PathParam("id") int id, Kamion kamion){
//        try {
//            Connection connection = DriverManager.getConnection(DB.dbName,DB.dbUsername,DB.dbPassword);
//            Statement statement = connection.createStatement();
//
//            String u = "SELECT * FROM kamion WHERE IDKam = " + id;
//            ResultSet rs = statement.executeQuery(u);
//            if(!rs.next()) return Response.status(404).build();
//            rs.close();
//
//            String upit = "UPDATE kamion SET Marka = '" + kamion.getMarka() + "', Nosivost = " + kamion.getNosivost() + ", Godiste = " + kamion.getGodiste() + ", BrPopravljanja = " + kamion.getBrPopravljanja()
//                    + " WHERE IDKam = " + id;
//
//            statement.executeUpdate(upit);
//
//            Response response = Response.status(200).build();
//            return response;
//        } catch (SQLException ex) {
//            System.out.println(ex.toString());
//            Logger.getLogger(KamionServis.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        Response response = Response.status(404).build();
//        return response;
//    }
//
//    @DELETE
//    @Path("{id}")
//    public Response deleteKamion(@PathParam("id") int id){
//        try {
//            Connection connection = DriverManager.getConnection(DB.dbName,DB.dbUsername,DB.dbPassword);
//            Statement statement = connection.createStatement();
//
//            String u = "SELECT * FROM kamion WHERE IDKam = " + id;
//            ResultSet rs = statement.executeQuery(u);
//            if(!rs.next()) return Response.status(404).build();
//            rs.close();
//
//            String upit = "DELETE FROM kamion WHERE IDKam = " + id;
//            statement.executeUpdate(upit);
//
//            return Response.status(204).build();
//        } catch (SQLException ex) {
//            Logger.getLogger(KamionServis.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        return Response.status(404).build();
//    }
//
//    @POST
//    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
//    public Response createKamionForm(@FormParam("id") int id, @FormParam("marka") String marka, @FormParam("nosivost") int nosivost, @FormParam("godiste") int godiste, @FormParam("brPopravljanja") int brPopravaljanja){
//        try {
//            Connection connection = DriverManager.getConnection(DB.dbName,DB.dbUsername,DB.dbPassword);
//            Statement statement = connection.createStatement();
//
//            String u = "SELECT * FROM kamion WHERE IDKam = " + id;
//            ResultSet rs = statement.executeQuery(u);
//            if(rs.next()) return Response.status(409).build();
//            rs.close();
//
//            String upit = "Insert into kamion values(" + id + ",'" + marka + "'," + nosivost + "," + godiste + "," + brPopravaljanja + ")";
//
//            statement.executeUpdate(upit);
//
//            return Response.status(201).build();
//        } catch (SQLException ex) {
//            Logger.getLogger(KamionServis.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return Response.status(409).build();
//    }
//
//    @GET
//    @Produces(MediaType.TEXT_XML)
//    public Response getKamioni(@QueryParam("minGod") int min, @QueryParam("maxGod") int max){
//        try {
//            String upit = "Select * from kamion where Godiste >= " + min + " AND Godiste <=" + max;
//
//            Connection connection = DriverManager.getConnection(DB.dbName,DB.dbUsername,DB.dbPassword);
//            Statement statement = connection.createStatement();
//            ResultSet rs = statement.executeQuery(upit);
//            if(!rs.next()) return Response.status(404).build();
//            rs.previous();
//
//            List<Kamion> kamioni = new ArrayList<>();
//            while(rs.next()){
//                Kamion kamion = new Kamion();
//
//                kamion.setMarka(rs.getString("Marka"));
//                kamion.setBrPopravljanja(rs.getInt("BrPopravljanja"));
//                kamion.setGodiste(rs.getInt("Godiste"));
//                kamion.setNosivost(rs.getInt("Nosivost"));
//                kamion.setIdKam(rs.getInt("IDKam"));
//
//                kamioni.add(kamion);
//            }
//            GenericEntity<List<Kamion>> genericKamioni = new GenericEntity<List<Kamion>>(kamioni){};
//            return Response.status(200).entity(genericKamioni).build();
//        } catch (SQLException ex) {
//            Logger.getLogger(KamionServis.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return Response.status(404).build();
//    }
}

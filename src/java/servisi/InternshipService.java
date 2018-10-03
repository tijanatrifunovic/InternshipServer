package servisi;

import db.DB;
import entiteti.Company;
import entiteti.CompanyResponse;
import entiteti.Department;
import entiteti.Internship;
import entiteti.LoginRequest;
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
@Path("internship")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class InternshipService {

    @GET
    @Path("/company/{idCompany}")
    public Response getInternships(@PathParam("idCompany") int idCompany){
        try {
            String query = "SELECT i.idinternship, i.idcompany, i.name, i.iddepartment, d.name as department, i.startDate, i.endDate, i.duration, i.taken, i.idstudent, i.done, i.description FROM internship.internship i left outer join department as d on d.iddepartment = i.iddepartment where idcompany = " + idCompany;
          
            Connection connection = DriverManager.getConnection(DB.dbName,DB.dbUsername,DB.dbPassword);
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
                       
            List<Internship> internships = new ArrayList<>();
            while (rs.next()) {
                Internship internship = new Internship(rs.getInt("idinternship"), rs.getInt("idcompany"), rs.getInt("idstudent"), rs.getString("name"), rs.getInt("iddepartment"), rs.getString("department"), rs.getString("startDate"), rs.getString("endDate"), rs.getString("duration"), rs.getInt("taken"), rs.getInt("done"), rs.getString("description"));
                internships.add(internship);
            }
            GenericEntity<List<Internship>> genericInternshipss = new GenericEntity<List<Internship>>(internships) {
            };
            return Response.status(200).entity(genericInternshipss).build();
            
        } catch (SQLException ex) {
            Logger.getLogger(StudentServis.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Response response = Response.status(404).build();
        return response;
    }
    
    @POST
    @Path("/login")
    public Response login(LoginRequest company) {
        if (company.getEmail().isEmpty() || company.getPassword().isEmpty()) {
            Response response = Response.status(400).build();
            return response;
        }
        try {
            Connection connection = DriverManager.getConnection(DB.dbName, DB.dbUsername, DB.dbPassword);
            Statement statement = connection.createStatement();

            String query = "select u.iduser, u.email, c.name, c.address from internship.user u left outer join company c on c.iduser = u.iduser WHERE email = '" + company.getEmail() + "' and password = '" + company.getPassword() + "' and idusertype = 2";
            ResultSet rs = statement.executeQuery(query);
            CompanyResponse companyResponse = new CompanyResponse();
            if (rs.next()) {

                companyResponse.setIdUser(rs.getInt("iduser"));
                companyResponse.setEmail(rs.getString("email"));
                companyResponse.setName(rs.getString("name"));
                companyResponse.setAddress(rs.getString("address"));

            } else {
                Response response = Response.status(404).build();
                return response;
            }

            Response response = Response.status(200).entity(companyResponse).build();
            return response;
        } catch (SQLException ex) {
            Logger.getLogger(InternshipService.class.getName()).log(Level.SEVERE, null, ex);
        }

        Response response = Response.status(409).build();
        return response;
    }
    
    
    @POST
    public Response registerCompany(Company company) {
        if (company.getEmail().isEmpty() || company.getName().isEmpty() || company.getPassword().isEmpty() || company.getAddress().isEmpty()) {
            Response response = Response.status(400).build();
            return response;
        }
        try {
            Connection connection = DriverManager.getConnection(DB.dbName, DB.dbUsername, DB.dbPassword);
            Statement statement = connection.createStatement();
            
            String query = "SELECT COALESCE(MAX(idUser), 1) as id FROM user";
            ResultSet rs = statement.executeQuery(query);
            int idUser = 1;
            if (rs.next()) {
                idUser = rs.getInt("id") + 1;
            }

            query = "INSERT INTO user VALUES (" + idUser + ", '" + company.getEmail() + "', '" + company.getPassword() + "', 2)";
            statement.executeUpdate(query);

            query = "INSERT INTO company VALUES (" + idUser + ", '" + company.getName() + "', '" + company.getAddress()+ "')";
            statement.executeUpdate(query);

            Response response = Response.status(201).build();
            return response;
        } catch (SQLException ex) {
            Logger.getLogger(InternshipService.class.getName()).log(Level.SEVERE, null, ex);
        }

        Response response = Response.status(409).build();
        return response;
    }

}

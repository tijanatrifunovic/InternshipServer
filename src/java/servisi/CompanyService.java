package servisi;

import db.DB;
import entiteti.Company;
import entiteti.CompanyResponse;
import entiteti.Department;
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
@Path("company")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class CompanyService {

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
            Logger.getLogger(CompanyService.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(CompanyService.class.getName()).log(Level.SEVERE, null, ex);
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

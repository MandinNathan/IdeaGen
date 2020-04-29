/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serveur_rest_idees;

import serveur_rest_idees.idees.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author utilisateur
 */
@Path("idees")
public class RessourceIdees {
    
    Idee id;

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of RessourceIdees
     */
    public RessourceIdees() {
    }

    /**
     * Retrieves representation of an instance of serveur_rest_idees.RessourceIdees
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getServiceDescription() {
        String warning = "Available Service";
        int code = 0;
        String description = "Service which generate ideas on drawing, photo, video, video games and 'invention'";
        String ressource = "{}";
        
        return "{\"warning\":\"" + warning + "\", \"code\":" + Integer.toString(code) + 
                ", \"description\":\"" + description + "\", \"ressource\":" + ressource + "}";
    }

    /**
     * PUT method for updating or creating an instance of RessourceIdea
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
    
    @GET
    @Path("/aleatoire")
    @Produces(MediaType.APPLICATION_JSON)
    public String ideeAleatoire(){
        String[] listDomaines = new String[]{"dessin","photo","video","jv","invention"};
        int tmpInd = (int) (listDomaines.length*Math.random());
        return "{\"domaine\":\"" + listDomaines[tmpInd] + "\"}";
    }
    
    @GET
    @Path("/dessin")
    @Produces(MediaType.APPLICATION_JSON)
    public String ideeDessin(@QueryParam("sujet") String suj) {
        
        Dessin d = new Dessin();
        d.addTechnique();
        d.addSujet(suj);
        d.addDescription();
        
        id = d;
        
        String warning = "Available Service";
        int code = 0;
        String description = "Return a complete description of an idee of a drawing";
        String ressource = d.toJSON();
        
        return "{\"warning\":\"" + warning + "\", \"code\":" + Integer.toString(code) + 
                ", \"description\":\"" + description + "\", \"ressource\":" + ressource + "}";
    }  
    
    @GET
    @Path("/photo")
    @Produces(MediaType.APPLICATION_JSON)
    public String ideePhoto() {
        String warning = "Unavailable Service";
        int code = -1;
        String description = "Not yet implemented : will return a complete description of an idee of a photo";
        String ressource = "{}";
        
        return "{\"warning\":\"" + warning + "\", \"code\":" + Integer.toString(code) + 
                ", \"description\":\"" + description + "\", \"ressource\":" + ressource + "}";
    }
    
    @GET
    @Path("/video")    
    @Produces(MediaType.APPLICATION_JSON)
    public String ideeVideo() {
        String warning = "Unavailable Service";
        int code = -1;
        String description = "Not yet implemented : will return a complete description of an idee of a video";
        String ressource = "{}";
        
        return "{\"warning\":\"" + warning + "\", \"code\":" + Integer.toString(code) + 
                ", \"description\":\"" + description + "\", \"ressource\":" + ressource + "}";
    }

    @GET
    @Path("/jv")    
    @Produces(MediaType.APPLICATION_JSON)
    public String ideeJeuVideo() {
        String warning = "Unavailable Service";
        int code = -1;
        String description = "Not yet implemented : will return a complete description of an idee of a video game";
        String ressource = "{}";
        
        return "{\"warning\":\"" + warning + "\", \"code\":" + Integer.toString(code) + 
                ", \"description\":\"" + description + "\", \"ressource\":" + ressource + "}";
    }

    @GET
    @Path("/invention")    
    @Produces(MediaType.APPLICATION_JSON)
    public String ideeInvention() {
        String warning = "Unavailable Service";
        int code = -1;
        String description = "Not yet implemented : will return a complete description of an idee of an 'invention'";
        String ressource = "{}";
        
        return "{\"warning\":\"" + warning + "\", \"code\":" + Integer.toString(code) + 
                ", \"description\":\"" + description + "\", \"ressource\":" + ressource + "}";
    }
}

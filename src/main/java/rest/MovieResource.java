package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import facades.MovieFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/movie")
public class MovieResource {

    private static final EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
    private static final MovieFacade facade = MovieFacade.getMovieFacade(emf);
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Path("/{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getMovieById(@PathParam("id") int id) {
        return gson.toJson(facade.getMovieById(id));
    }

    @Path("/all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllMovies() {
        return gson.toJson(facade.getAllMovies());
    }

    @Path("/count")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getMovieCount() {
        return gson.toJson(facade.getMovieCount());
    }
}
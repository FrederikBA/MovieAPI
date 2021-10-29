package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import facades.MovieFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;

@Path("/movie")
public class MovieResource {

    private static final EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
    private static final MovieFacade facade = MovieFacade.getMovieFacade(emf);
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Path("/{id}")
    @GET
    @Produces("application/json")
    public String getMovieById(@PathParam("id") int id) {
        return gson.toJson(facade.getMovieById(id));
    }

    @Path("/all")
    @GET
    @Produces("application/json")
    public String getAllMovies() {
        return gson.toJson(facade.getAllMovies());
    }

    @Path("/count")
    @GET
    @Produces("application/json")
    public String getMovieCount() {
        return gson.toJson(facade.getMovieCount());
    }


}
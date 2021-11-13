package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.MovieDTO;
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

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String addMovie(String movie) throws WebApplicationException {
        MovieDTO m = gson.fromJson(movie, MovieDTO.class);
        MovieDTO mNew = facade.addMovie(m);
        return gson.toJson(mNew);
    }

    @Path("/{id}")
    @PUT
    public String editMovie(@PathParam("id") int id, String movie) throws WebApplicationException {
        MovieDTO m = gson.fromJson(movie, MovieDTO.class);
        m.setId(id);
        MovieDTO mEdited = facade.editMovie(m);
        return gson.toJson(mEdited);
    }

    @DELETE
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public String deleteMovie(@PathParam("id") int id) throws WebApplicationException {
        MovieDTO mDeleted = facade.deleteMovie(id);
        return gson.toJson(mDeleted);
    }
}
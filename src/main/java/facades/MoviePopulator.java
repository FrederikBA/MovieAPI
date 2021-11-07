package facades;

import dtos.MovieDTO;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.WebApplicationException;


public class MoviePopulator {

    public static void populate() throws WebApplicationException {
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        MovieFacade facade = MovieFacade.getMovieFacade(emf);

        facade.addMovie(new MovieDTO(2021, "TestMovie", "https://www.imdb.com/title/tt1853728/?ref_=nv_sr_srsg_0", 8.4));
    }

    public static void main(String[] args) {
        populate();
    }
}

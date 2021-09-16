package facades;

import org.junit.jupiter.api.Test;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;

import static org.junit.jupiter.api.Assertions.*;

class MovieFacadeTest {
    EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
    MovieFacade facade = MovieFacade.getMovieFacade(emf);


    @Test
    public void getMovieByIdTest() {
        String expected = "Crimson Tide";
        String actual = facade.getMovieById(1).getTitle();

        assertEquals(expected, actual);
    }

    @Test
    public void getAllMoviesTest() {
        int expected = 5;
        int actual = facade.getAllMovies().size();

        assertEquals(expected, actual);
    }
}
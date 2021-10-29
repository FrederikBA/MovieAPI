package facades;

import dtos.MovieDTO;
import dtos.MoviesDTO;
import entities.Movie;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class MovieFacade {
    private static MovieFacade instance;
    private static EntityManagerFactory emf;


    public static MovieFacade getMovieFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new MovieFacade();
        }
        return instance;
    }

    public MovieDTO createMovie(MovieDTO m) {
        EntityManager em = emf.createEntityManager();
        Movie movie = new Movie(m.getYear(), m.getTitle(), m.getImdb());

        try {
            em.getTransaction().begin();
            em.persist(movie);
            em.getTransaction().commit();

            return new MovieDTO(movie);

        } finally {
            em.close();
        }
    }

    public MovieDTO getMovieById(int id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        try {
            Movie movie = em.find(Movie.class, id);

            return new MovieDTO(movie);

        } finally {
            em.close();
            emf.close();
        }
    }

    public MoviesDTO getAllMovies() {
        EntityManager em = emf.createEntityManager();

        try {
            TypedQuery<Movie> query = em.createQuery("SELECT movie FROM Movie movie", Movie.class);
            List<Movie> movies = query.getResultList();

            return new MoviesDTO(movies);

        } finally {
            em.close();
        }
    }
}
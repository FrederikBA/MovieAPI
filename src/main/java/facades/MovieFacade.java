package facades;

import dtos.MovieDTO;
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

    public void createMovie(MovieDTO m) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(new Movie(m.getYear(), m.getTitle(), m.getActors()));
            em.getTransaction().commit();
        } finally {
            em.close();
            emf.close();
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

    @SuppressWarnings("unchecked")
    public List<MovieDTO> getAllMovies() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();

        try {
            TypedQuery<Movie> query = em.createQuery("SELECT movie FROM Movie movie", Movie.class);
            List<Movie> movies = query.getResultList();
            return (List<MovieDTO>) (List<?>) movies;
        } finally {
            em.close();
            emf.close();
        }
    }

    public List<String> addActors(String a1, String a2, String a3, String a4, String a5) {
        List<String> actors = new ArrayList<>();
        actors.add(a1);
        actors.add(a2);
        actors.add(a3);
        actors.add(a4);
        actors.add(a5);
        return actors;
    }
}
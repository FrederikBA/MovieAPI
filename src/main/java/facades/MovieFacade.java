package facades;

import dtos.MovieDTO;
import dtos.MoviesDTO;
import entities.Movie;
import errorhandling.InsufficientRatingException;
import errorhandling.MovieNotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
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
        Movie movie = new Movie(m.getYear(), m.getTitle(), m.getImdb(), m.getRating());

        try {
            em.getTransaction().begin();
            em.persist(movie);
            em.getTransaction().commit();

            return new MovieDTO(movie);

        } finally {
            em.close();
        }
    }

    public MovieDTO editMovie(MovieDTO movieDTO) throws InsufficientRatingException {
        EntityManager em = emf.createEntityManager();
        try {
            Movie movie = em.find(Movie.class, movieDTO.getId());

            movie.setYear(movieDTO.getYear());
            movie.setTitle(movieDTO.getTitle());
            movie.setImdb(movieDTO.getImdb());
            if (movieDTO.getRating() > 0 && movieDTO.getRating() < 10) {
                movie.setRating(movieDTO.getRating());
            } else {
                throw new InsufficientRatingException("Movie Rating must be a value between 0 and 10");
            }
            return new MovieDTO(movie);

        } finally {
            em.close();
        }
    }

    public MovieDTO deleteMovie(int id) throws MovieNotFoundException {
        EntityManager em = emf.createEntityManager();
        Movie movie = em.find(Movie.class, id);
        if (movie == null) {
            throw new MovieNotFoundException(String.format("Movie with id: (%d) not found", id));
        } else {
            try {
                em.getTransaction().begin();
                em.remove(movie);
                em.getTransaction().commit();

                return new MovieDTO(movie);

            } finally {
                em.close();
            }
        }
    }

    public MovieDTO getMovieById(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            Movie movie = em.find(Movie.class, id);

            return new MovieDTO(movie);

        } finally {
            em.close();
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

    public Integer getMovieCount() {
        EntityManager em = emf.createEntityManager();

        try {
            TypedQuery<Integer> query = em.createQuery("SELECT COUNT(m) FROM Movie m", Integer.class);
            int count = query.getSingleResult();

            return count;

        } finally {
            em.close();
        }
    }

}
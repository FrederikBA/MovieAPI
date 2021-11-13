package facades;

import dtos.MovieDTO;
import dtos.MoviesDTO;
import entities.Movie;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.ws.rs.WebApplicationException;
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

    public MovieDTO addMovie(MovieDTO movieDTO) throws WebApplicationException {
        EntityManager em = emf.createEntityManager();
        if (movieDTO.getRating() > 10 || movieDTO.getRating() < 0) {
            throw new WebApplicationException("The rating: " + movieDTO.getRating() + " doesn't match the criteria: (0 - 10)");
        } else {
            Movie movie = new Movie(movieDTO.getYear(), movieDTO.getTitle(), movieDTO.getImdb(), movieDTO.getRating());

            try {
                em.getTransaction().begin();
                em.persist(movie);
                em.getTransaction().commit();

                return new MovieDTO(movie);
            } finally {
                em.close();
            }
        }

    }

    public MovieDTO editMovie(MovieDTO movieDTO) throws WebApplicationException {
        EntityManager em = emf.createEntityManager();
        try {
            Movie movie = em.find(Movie.class, movieDTO.getId());

            movie.setYear(movieDTO.getYear());
            movie.setTitle(movieDTO.getTitle());
            movie.setImdb(movieDTO.getImdb());


            if (movieDTO.getRating() > 10 || movieDTO.getRating() < 0) {
                throw new WebApplicationException("The rating: " + movieDTO.getRating() + " doesn't match the criteria: (0 - 10)");
            } else {
                movie.setRating(movieDTO.getRating());
            }
            em.getTransaction().begin();
            em.merge(movie);
            em.getTransaction().commit();
            return new MovieDTO(movie);

        } finally {
            em.close();
        }
    }

    public MovieDTO deleteMovie(int id) throws WebApplicationException {
        EntityManager em = emf.createEntityManager();
        Movie movie = em.find(Movie.class, id);
        if (movie == null) {
            throw new WebApplicationException("There's no movie matching the Id");
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
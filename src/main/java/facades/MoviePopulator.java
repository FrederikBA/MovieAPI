package facades;

import dtos.MovieDTO;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;


public class MoviePopulator {

    public static void populate() {
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        MovieFacade facade = MovieFacade.getMovieFacade(emf);
    }

    public static void main(String[] args) {
        populate();
    }
}

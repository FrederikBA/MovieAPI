package facades;

import dtos.MovieDTO;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;


public class MoviePopulator {

    public static void populate() {
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        MovieFacade facade = MovieFacade.getMovieFacade(emf);
        facade.createMovie(new MovieDTO(1995, "Crimson Tide", facade.addActors("Gene Hackman", "Denzel Washington", "Matt Craven", "James Gandolfini", "Viggo Mortensen")));
        facade.createMovie(new MovieDTO(2007, "Bucket List", facade.addActors("Jack Nicholson", "Morgan Freeman", "Sean Heyes", "Beverly Todd", "Rob Morrow")));
        facade.createMovie(new MovieDTO(1993, "Schindler's List", facade.addActors("Liam Neeson", "Ben Kingsley", "Ralph Fiennes", "Caroline Goodall", "Jonathan Sagall")));
        facade.createMovie(new MovieDTO(2008, "The Dark Knight", facade.addActors("Christian Bale", "Heath Ledger", "Michael Caine", "Morgan Freeman", "Gary Oldman")));
        facade.createMovie(new MovieDTO(2003, "Lord of the Rings: Return of the King", facade.addActors("Elijah Wood", "Viggo Mortensen", "Ian Mckellen", "Orlando Bloom", "Sean Bean")));
    }

    public static void main(String[] args) {
        populate();
    }
}

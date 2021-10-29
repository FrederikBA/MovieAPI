package dtos;

import entities.Movie;

import java.util.ArrayList;
import java.util.List;

public class MoviesDTO {
    List<MovieDTO> all = new ArrayList<>();

    public MoviesDTO(List<Movie> personEntities) {
        personEntities.forEach((m) -> {
            all.add(new MovieDTO(m));
        });
    }
}


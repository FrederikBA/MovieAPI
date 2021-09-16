package dtos;

import entities.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieDTO {
    private int year;
    private String title;
    private List<String> actors;

    public MovieDTO(Movie m) {
        this.year = m.getYear();
        this.title = m.getTitle();
        this.actors = m.getActors();
    }

    public MovieDTO(int year, String title, List<String> actors) {
        this.year = year;
        this.title = title;
        this.actors = actors;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getActors() {
        return actors;
    }

    public void setActors(List<String> actors) {
        this.actors = actors;
    }
}

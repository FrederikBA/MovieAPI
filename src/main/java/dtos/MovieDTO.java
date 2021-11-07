package dtos;

import entities.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieDTO {
    private int id;
    private int year;
    private String title;
    private String imdb;
    private double rating;

    public MovieDTO(Movie m) {
        this.id = m.getId();
        this.year = m.getYear();
        this.title = m.getTitle();
        this.imdb = m.getImdb();
        this.rating = m.getRating();
    }

    public MovieDTO(int year, String title, String imdb, double rating) {
        this.id = -1;
        this.year = year;
        this.title = title;
        this.imdb = imdb;
        this.rating = rating;
    }

    public MovieDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getImdb() {
        return imdb;
    }

    public void setImdb(String imdb) {
        this.imdb = imdb;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}

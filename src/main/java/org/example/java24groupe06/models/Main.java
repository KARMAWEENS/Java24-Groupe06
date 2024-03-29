package org.example.java24groupe06.models;

import java.io.Serial;

public class Main {
    public static void main(String []args){
        Movie movie = new Movie.MovieBuilder()
                .setDuration(52)
                .setTitle("fd")
                .build();
        System.out.println(movie.getTitle() + movie.getDuration());


    }
}

package com.example.digitalmovie.controllers;

import com.example.digitalmovie.models.CustomizedResponse;
import com.example.digitalmovie.models.Movie;
import com.example.digitalmovie.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @PostMapping
    public ResponseEntity<?> createMovie(@RequestBody Movie movie) {
        Movie savedMovie = movieService.addMovie(movie);
        return ResponseEntity.ok(new CustomizedResponse("Movie added successfully", savedMovie));
    }

    @GetMapping
    public ResponseEntity<?> getAllMovies() {
        List<Movie> movies = movieService.getAllMovies();
        return ResponseEntity.ok(new CustomizedResponse("List of movies", movies));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMovieById(@PathVariable Integer id) {
        Movie movie = movieService.getMovieById(id);
        if (movie == null) {
            return ResponseEntity.badRequest().body(new CustomizedResponse("Movie not found", null));
        }
        return ResponseEntity.ok(new CustomizedResponse("Movie found", movie));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateMovie(@PathVariable Integer id, @RequestBody Movie movieDetails) {
        Movie updatedMovie = movieService.updateMovie(id, movieDetails);
        if (updatedMovie == null) {
            return ResponseEntity.badRequest().body(new CustomizedResponse("Movie not found", null));
        }
        return ResponseEntity.ok(new CustomizedResponse("Movie updated successfully", updatedMovie));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMovie(@PathVariable Integer id) {
        movieService.deleteMovie(id);
        return ResponseEntity.ok(new CustomizedResponse("Movie deleted successfully", null));
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchMoviesByTitle(@RequestParam String title) {
        List<Movie> movies = movieService.searchMoviesByTitle(title);
        return ResponseEntity.ok(new CustomizedResponse("List of movies", movies));
    }

    @GetMapping("/featured")
    public ResponseEntity<?> getFeaturedMovies() {
        List<Movie> movies = movieService.getFeaturedMovies();
        return ResponseEntity.ok(new CustomizedResponse("List of featured movies", movies));
    }

    @GetMapping("/featuredTvShows")
    public ResponseEntity<?> getFeaturedTvShows() {
        List<Movie> tvShows = movieService.getFeaturedTvShows();
        return ResponseEntity.ok(new CustomizedResponse("List of featured TV shows", tvShows));
    }
}

package com.example.digitalmovie.services;

import com.example.digitalmovie.models.Movie;
import com.example.digitalmovie.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    public Movie addMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Movie getMovieById(Integer id) {
        return movieRepository.findById(id).orElse(null);
    }

    public Movie updateMovie(Integer id, Movie movieDetails) {
        Movie movie = movieRepository.findById(id).orElse(null);
        if (movie != null) {
            movie.setTitle(movieDetails.getTitle());
            movie.setPoster(movieDetails.getPoster());
            movie.setDescription(movieDetails.getDescription());
            movie.setReleaseDate(movieDetails.getReleaseDate());
            movie.setGenre(movieDetails.getGenre());
            movie.setRating(movieDetails.getRating());
            movie.setDuration(movieDetails.getDuration());
            movie.setPriceRent(movieDetails.getPriceRent());
            movie.setPriceBuy(movieDetails.getPriceBuy());
            movie.setFeatured(movieDetails.isFeatured());
            movie.setType(movieDetails.getType()); // Update type
            return movieRepository.save(movie);
        }
        return null;
    }

    public void deleteMovie(Integer id) {
        movieRepository.deleteById(id);
    }

    public List<Movie> searchMoviesByTitle(String title) {
        return movieRepository.findByTitleContaining(title);
    }

    public List<Movie> getFeaturedMovies() {
        return movieRepository.findByIsFeaturedAndType(true, "movie");
    }

    public List<Movie> getFeaturedTvShows() {
        return movieRepository.findByIsFeaturedAndType(true, "tvshow");
    }
}

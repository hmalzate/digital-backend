package com.example.digitalmovie.repositories;

import com.example.digitalmovie.models.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends MongoRepository<Movie, Integer> {
    List<Movie> findByTitleContaining(String title);
    List<Movie> findByIsFeatured(boolean isFeatured);
    List<Movie> findByIsFeaturedAndType(boolean isFeatured, String type);
}

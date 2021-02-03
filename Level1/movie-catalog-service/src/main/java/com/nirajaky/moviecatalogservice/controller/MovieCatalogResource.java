package com.nirajaky.moviecatalogservice.controller;

import com.nirajaky.moviecatalogservice.model.CatalogItem;
import com.nirajaky.moviecatalogservice.model.Movie;
import com.nirajaky.moviecatalogservice.model.Rating;
import com.nirajaky.moviecatalogservice.model.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){

        //get all rated movies Id
        UserRating ratings = restTemplate.getForObject("http://rating-data-service/ratingsdata/users/" + userId, UserRating.class);

        return ratings.getUserRating().stream().map(rating ->
        {
            // for each movieId call movie-info-service and get movie details
            // eureka consumer
            Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);

            return new CatalogItem(movie.getName(), "Test Name", rating.getRating());
        }).collect(Collectors.toList());   //put them all together
    }
}


//@Autowired
//private WebClient.Builder webClientBuilder;

// ALTERNATIVE WAY
            /*Movie movie = webClientBuilder.build()
                    .get()
                    .uri("http://localhost:8082/movies/" + rating.getMovieId())
                    .retrieve()
                    .bodyToMono(Movie.class)   // Mono : get a promice of data {Async call}
                    .block();*/
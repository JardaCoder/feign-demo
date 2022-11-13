package com.demo.feign.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.demo.feign.clients.dto.MovieDto;
import com.demo.feign.clients.dto.enums.MovieType;


@FeignClient(value = "Open-movie-database-client", url = "http://www.omdbapi.com/?apikey=c5e3f72f")
public interface OpenMovieDatabaseClient {
	
	
	@GetMapping
	MovieDto findMovie(@RequestParam("t") String title, @RequestParam("type") MovieType type);

	@GetMapping
	MovieDto getMovieByImdbId(@RequestParam("i") String imdbId);

}
 
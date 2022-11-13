package com.demo.feign;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.demo.feign.clients.dto.MovieDto;
import com.demo.feign.clients.dto.enums.MovieType;
import com.demo.feign.domain.exception.BusinessException;
import com.demo.feign.domain.service.MovieService;

@SpringBootTest
@EnableFeignClients
class MovieServiceIntegrationTests {
	
	@Autowired
	private MovieService movieService;
	
	private static final String IMDBID = "tt0076759"; 
	

	@Test
	void testSuccessFindMovie() {
		MovieDto movie = movieService.findMovie("Star wars", MovieType.MOVIE);
		
		assertNotNull(movie);
		assertNotNull(movie.getTitle());
	}
	
	@Test
	void testFailFindMovie() {
		assertThrows(BusinessException.class, () ->{
			movieService.findMovie(null, null);
		});
	}
	
	@Test
	void testSuccessGetMovieByImdbId() {
		MovieDto movie = movieService.getMovieByImdbId(IMDBID);
		
		assertNotNull(movie);
		assertEquals(movie.getImdbID(), IMDBID);
	}
	
	@Test
	void testFailGetMovieByImdbId() {
		assertThrows(BusinessException.class, () ->{
			movieService.getMovieByImdbId(null);
		});
		
		assertThrows(BusinessException.class, () ->{
			movieService.getMovieByImdbId("");
		});
	}

}

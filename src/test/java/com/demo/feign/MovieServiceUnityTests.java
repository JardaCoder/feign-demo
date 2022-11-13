package com.demo.feign;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.demo.feign.clients.OpenMovieDatabaseClient;
import com.demo.feign.clients.dto.MovieDto;
import com.demo.feign.clients.dto.RatingDto;
import com.demo.feign.clients.dto.enums.MovieType;
import com.demo.feign.domain.exception.BusinessException;
import com.demo.feign.domain.service.MovieService;

@SpringBootTest
class MovieServiceUnityTests {
	
	
	@Autowired
	private MovieService movieService;
	
	@MockBean
	private OpenMovieDatabaseClient openMovieDatabaseClient;
	
	
	private static final String IMDBID = "tt0076759"; 
	private MovieDto testMovie = null;
	
	
	@BeforeEach
	public void init() {
		
		RatingDto rating = RatingDto.builder()
				.source("test")
				.value("9/10")
				.build();
		
		testMovie =  MovieDto.builder()
				.title("Star wars")
				.imdbID(IMDBID)
				.ratings(List.of(rating))
				.build();
	}
	

	@Test
	void testSuccessFindMovie() {
		
		Mockito
		.when(openMovieDatabaseClient.findMovie("Star wars", MovieType.EPISODE))
		.thenReturn(testMovie);
		
		MovieDto movie = movieService.findMovie("Star wars", MovieType.EPISODE);
		
		assertEquals(movie, testMovie);
		Mockito.verify(openMovieDatabaseClient, times(1)).findMovie("Star wars", MovieType.EPISODE);
		
	}
	
	@Test
	void testFailFindMovie() {
		assertThrows(BusinessException.class, () ->{
			movieService.findMovie(null, null);
		});
		
		Mockito.verify(openMovieDatabaseClient, times(0)).findMovie("Star wars", MovieType.EPISODE);
	}
	
	@Test
	void testSuccessGetMovieByImdbId() {
		
		Mockito
		.when(openMovieDatabaseClient.getMovieByImdbId(IMDBID))
		.thenReturn(testMovie);
		
		MovieDto movie = movieService.getMovieByImdbId(IMDBID);
		
		assertNotNull(movie);
		assertEquals(movie.getImdbID(), IMDBID);
		Mockito.verify(openMovieDatabaseClient, times(1)).getMovieByImdbId(IMDBID);
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

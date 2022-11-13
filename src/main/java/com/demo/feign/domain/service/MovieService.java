package com.demo.feign.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.demo.feign.clients.OpenMovieDatabaseClient;
import com.demo.feign.clients.dto.MovieDto;
import com.demo.feign.clients.dto.enums.MovieType;
import com.demo.feign.domain.exception.BusinessException;

@Service
public class MovieService {
	
	@Autowired
	private OpenMovieDatabaseClient openMovieDatabaseClient;
	
	
	
	public MovieDto findMovie(String title, MovieType type) {
		
		if(title == null && type == null) {
			throw new BusinessException("The arguments cant be null");
		}
		
		return openMovieDatabaseClient.findMovie(title, type);
	}
	
	public MovieDto getMovieByImdbId(String imdbId) {
		
		if(!StringUtils.hasLength(imdbId)) {
			throw new BusinessException("The imdbId cant be null");
		}
		
		return openMovieDatabaseClient.getMovieByImdbId(imdbId);
	}

}

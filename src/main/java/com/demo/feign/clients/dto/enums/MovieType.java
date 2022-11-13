package com.demo.feign.clients.dto.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum MovieType {

	@JsonProperty("movie")
	MOVIE, 
	@JsonProperty("series")
	SERIES,
	@JsonProperty("episode")
	EPISODE
	
	
	
}

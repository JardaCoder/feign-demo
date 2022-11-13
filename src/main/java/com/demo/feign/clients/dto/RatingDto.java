package com.demo.feign.clients.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RatingDto{
	
    @JsonProperty("Source") 
    public String source;
    @JsonProperty("Value") 
    public String value;
}
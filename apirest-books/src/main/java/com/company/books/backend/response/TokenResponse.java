package com.company.books.backend.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TokenResponse { //ESTA CLASE LA CREAMOS COMO OBJETO DEL TOKEN, OSEA LO QUE VA A CONTENER

	@JsonProperty("token")
	private String jwtToken;

	public TokenResponse(String jwtToken) { //importante, público el constructor
		this.jwtToken = jwtToken;
	}

	public String getJwtToken() {
		return jwtToken;
	}

	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}

	
}

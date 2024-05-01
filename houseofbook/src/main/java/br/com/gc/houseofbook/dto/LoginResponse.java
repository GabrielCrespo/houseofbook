package br.com.gc.houseofbook.dto;

public record LoginResponse(String token, Long expiration) {
}

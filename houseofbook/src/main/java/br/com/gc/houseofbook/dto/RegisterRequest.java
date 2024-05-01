package br.com.gc.houseofbook.dto;

import java.time.LocalDate;

public record RegisterRequest(String name, String email, String password, LocalDate birthDate) {
}

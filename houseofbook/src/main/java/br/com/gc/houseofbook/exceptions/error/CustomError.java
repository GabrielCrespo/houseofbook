package br.com.gc.houseofbook.exceptions.error;

import java.time.Instant;
public record CustomError(Instant instant, Integer status, String error, String message, String path) {
}

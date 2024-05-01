package br.com.gc.houseofbook.controllers;

import br.com.gc.houseofbook.dto.LoginRequest;
import br.com.gc.houseofbook.dto.LoginResponse;
import br.com.gc.houseofbook.dto.RegisterRequest;
import br.com.gc.houseofbook.dto.RegisterResponse;
import br.com.gc.houseofbook.services.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/auth")
public class AuthController {

    private final AuthenticationService authenticationService;

    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest request) {
        var registerResponse = authenticationService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(registerResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        var loginResponse = authenticationService.login(request);
        return ResponseEntity.ok().body(loginResponse);
    }
}


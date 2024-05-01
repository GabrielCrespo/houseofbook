package br.com.gc.houseofbook.services;

import br.com.gc.houseofbook.dto.LoginRequest;
import br.com.gc.houseofbook.dto.LoginResponse;
import br.com.gc.houseofbook.dto.RegisterRequest;
import br.com.gc.houseofbook.dto.RegisterResponse;
import br.com.gc.houseofbook.entities.User;
import br.com.gc.houseofbook.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    public AuthenticationService(UserRepository userRepository,
                                 PasswordEncoder passwordEncoder,
                                 AuthenticationManager authenticationManager,
                                 JwtService jwtService) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;

    }

    public RegisterResponse register(RegisterRequest request) {

        User user =  new User();
        user.setEmail(request.email());
        user.setName(request.name());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setBirthDate(request.birthDate());

        user = userRepository.save(user);

        return RegisterResponse.toRegisterResponser(user);

    }

    public LoginResponse login(LoginRequest request) {

        var usernameAuthenticationToken =
                new UsernamePasswordAuthenticationToken(request.email(), request.password());

        var authentication = authenticationManager.authenticate(usernameAuthenticationToken);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtService.generateToken(authentication);
        long expirationTime = jwtService.getExpirationTime();

        return new LoginResponse(token, expirationTime);
    }
}

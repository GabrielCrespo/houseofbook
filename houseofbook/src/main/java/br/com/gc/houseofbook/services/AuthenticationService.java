package br.com.gc.houseofbook.services;

import br.com.gc.houseofbook.dto.LoginRequest;
import br.com.gc.houseofbook.dto.LoginResponse;
import br.com.gc.houseofbook.dto.RegisterRequest;
import br.com.gc.houseofbook.dto.RegisterResponse;
import br.com.gc.houseofbook.entities.Role;
import br.com.gc.houseofbook.entities.User;
import br.com.gc.houseofbook.repositories.RoleRepository;
import br.com.gc.houseofbook.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    Logger logger = LoggerFactory.getLogger(AuthenticationService.class);

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    public AuthenticationService(UserRepository userRepository,
                                 RoleRepository roleRepository,
                                 PasswordEncoder passwordEncoder,
                                 AuthenticationManager authenticationManager,
                                 JwtService jwtService) {

        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;

    }

    public RegisterResponse register(RegisterRequest request) {

        var roleClient = Role.Values.CLIENT.name();
        var role = roleRepository.findByName(roleClient)
                .orElseThrow(() -> new EntityNotFoundException("Entity not found"));

        User user = new User();
        user.setEmail(request.email());
        user.setName(request.name());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setBirthDate(request.birthDate());
        user.getRoles().add(role);

        user = userRepository.save(user);

        logger.info("User created successfully");
        return RegisterResponse.toRegisterResponse(user);

    }

    public LoginResponse login(LoginRequest request) {

        var usernameAuthenticationToken =
                new UsernamePasswordAuthenticationToken(request.email(), request.password());

        var authentication = authenticationManager.authenticate(usernameAuthenticationToken);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtService.generateToken(authentication);
        long expirationTime = jwtService.getExpirationTime();

        logger.info("Login successfully");
        return new LoginResponse(token, expirationTime);
    }
}

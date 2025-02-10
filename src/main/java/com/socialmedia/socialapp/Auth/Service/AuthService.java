package com.socialmedia.socialapp.Auth.Service;


import com.socialmedia.socialapp.Auth.DTO.AuthResponse;
import com.socialmedia.socialapp.Auth.DTO.LoginRequest;
import com.socialmedia.socialapp.Auth.DTO.RegisterRequest;
import com.socialmedia.socialapp.Jwt.JwtService;
import com.socialmedia.socialapp.User.Role;
import com.socialmedia.socialapp.User.User;
import com.socialmedia.socialapp.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtService jwtService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {
        // Obtener el usuario desde la base de datos
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        System.out.println(user);
        System.out.println(request);

        // Comparar la contraseña ingresada con la almacenada en la BD
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Incorrect password");
        }

        // Autenticar manualmente si la contraseña es correcta
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(), request.getPassword()
        ));

        // Generar el token JWT
        String token = jwtService.getToken(user);

        return new AuthResponse(token);
    }




    public AuthResponse register(RegisterRequest request) {
        System.out.println(request);
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        user.setfirstname(request.getfirstname());
        user.setlastname(request.getlastname());
        user.setRole(Role.USER);

        userRepository.save(user);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setToken(jwtService.getToken(user));

        return authResponse;
    }
}

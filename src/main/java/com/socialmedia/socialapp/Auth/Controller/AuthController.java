package com.socialmedia.socialapp.Auth.Controller;

import com.socialmedia.socialapp.Auth.DTO.AuthResponse;
import com.socialmedia.socialapp.Auth.Service.AuthService;
import com.socialmedia.socialapp.Auth.DTO.LoginRequest;
import com.socialmedia.socialapp.Auth.DTO.RegisterRequest;
import com.socialmedia.socialapp.Jwt.JwtService;
import com.socialmedia.socialapp.Jwt.TokenUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")

public class AuthController {

    @Autowired
    AuthService authService;

    @Autowired
    JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {

        AuthResponse authResponse = authService.login(request); // Obtiene el token
        String token = authResponse.getToken(); // Asume que hay un `getToken()`

        ResponseCookie cookie = ResponseCookie.from("token", token)
                .httpOnly(false)  // Evita acceso desde JavaScript
                .secure(false)   // TRUE si usas HTTPS
                .path("/")        // Disponible en toda la app
                .maxAge(24 * 60 * 60) // Expira en 1 día
                .build();

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(authResponse);
    }




    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request){
        System.out.println(request);
        AuthResponse authResponse = authService.register(request); // Obtiene el token
        String token = authResponse.getToken(); // Asume que hay un `getToken()`
        System.out.println(token);
        ResponseCookie cookie = ResponseCookie.from("token", token)
                .httpOnly(false)  // Evita acceso desde JavaScript
                .secure(false)   // TRUE si usas HTTPS
                .path("/")        // Disponible en toda la app
                .maxAge(60 * 60) // Expira en 1 hora
                .build();

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(authResponse);

    }

    @PostMapping("/validate")
    public ResponseEntity<?> validateToken(HttpServletRequest request) {
        try {
            // Extraer el token desde la cookie
            String token = TokenUtil.extractToken(request);

            if (token == null || !jwtService.validateToken(token)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token inválido");
            }

            return ResponseEntity.ok("Token válido");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token inválido");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletResponse response) {
        // Crear la cookie con el mismo nombre que la original
        Cookie cookie = new Cookie("token", "");
        cookie.setHttpOnly(false); // No accesible desde JavaScript
        cookie.setSecure(false);   // Asegúrate de que sea true si usas HTTPS
        cookie.setPath("/");      // Apunta a toda la aplicación
        cookie.setMaxAge(0);      // Elimina la cookie


        // Agregar la cookie anulada a la respuesta
        response.addCookie(cookie);

        return ResponseEntity.ok("Logged out successfully");
    }


}

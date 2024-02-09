package mx.edu.utez.FirstApp.service.auth;

import mx.edu.utez.FirstApp.config.ApiResponse;
import mx.edu.utez.FirstApp.model.user.User;
import mx.edu.utez.FirstApp.security.jwt.JwtProvider;
import mx.edu.utez.FirstApp.service.user.UserServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class AuthService {

    private final UserServices services;
    private final AuthenticationManager manager;
    private final JwtProvider provider;

    public AuthService(UserServices services, AuthenticationManager manager, JwtProvider provider) {
        this.services = services;
        this.manager = manager;
        this.provider = provider;
    }

    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse> signIn(String username, String password){
        try{
            Optional<User> founduser = services.findUserByUsername(username);
            if (founduser.isEmpty())
                return new ResponseEntity<>(new ApiResponse(HttpStatus.NOT_FOUND, true, "UserNotFound"), HttpStatus.BAD_REQUEST);
            User user = founduser.get();
            if(user.getBlocked() || !user.getStatus())
                return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, true, "Unauthorized"), HttpStatus.BAD_REQUEST);
            Authentication auth = manager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
            SecurityContextHolder.getContext().setAuthentication(auth);
            String token = provider.generatedToken(auth);
            // token, username, id, fullname role
            return new ResponseEntity<>(new ApiResponse(token, HttpStatus.OK), HttpStatus.OK);
        } catch (Exception e){
            String message = "CredentialsMismatch";
            if(e instanceof DisabledException)
                message = "UserDisable";
            return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, true, message), HttpStatus.BAD_REQUEST);
        }
    }
}

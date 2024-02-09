package mx.edu.utez.FirstApp.security.jwt;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.edu.utez.FirstApp.security.services.UserDetailsServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtProvider provider;

    @Autowired
    private UserDetailsServicesImpl services;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = provider.resolveToken(request);
            if (token == null)
                filterChain.doFilter(request, response);
            Claims claims = provider.resolveClaims(request);
            if(claims.isEmpty() && provider.validateClaims(claims, token)){
                String username = claims.getSubject();
                UserDetails user = services.loadUserByUsername(username);
                Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            filterChain.doFilter(request, response);
        } catch (Exception e){
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());

        }
    }
}

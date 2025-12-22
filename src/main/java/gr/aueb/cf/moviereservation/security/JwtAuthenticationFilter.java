package gr.aueb.cf.moviereservation.security;

import gr.aueb.cf.moviereservation.authentication.CustomUserDetailsService;
import gr.aueb.cf.moviereservation.authentication.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {


    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request,@NotNull HttpServletResponse response, @NotNull FilterChain filterChain)
            throws ServletException, IOException {

        System.out.println(">>> JWT FILTER HIT for path: " + request.getRequestURI());


        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String username;

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            System.out.println(">>> NO AUTH HEADER OR NOT BEARER");
            filterChain.doFilter(request, response);
            return;
        }
        System.out.println(">>> AUTH HEADER FOUND");

        jwt = authHeader.substring(7).trim();
        System.out.println(">>> JWT: " + jwt);
        username = jwtService.extractSubject(jwt);
        System.out.println(">>> USERNAME FROM TOKEN: " + username);

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                        );

                SecurityContextHolder.getContext().setAuthentication(authToken);
            System.out.println(">>> AUTH SET IN CONTEXT");

        }

        filterChain.doFilter(request, response);

    }

}

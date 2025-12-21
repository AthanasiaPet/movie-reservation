package gr.aueb.cf.moviereservation.authentication;

import gr.aueb.cf.moviereservation.dto.AuthenticationRequestDTO;
import gr.aueb.cf.moviereservation.dto.AuthenticationResponseDTO;
import lombok.RequiredArgsConstructor;
import gr.aueb.cf.moviereservation.model.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthenticationResponseDTO login(AuthenticationRequestDTO request) {

        Authentication authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        User user = (User) authentication.getPrincipal();
        String token = jwtService.generateToken(authentication.getName(), user.getRole().name());

        return new AuthenticationResponseDTO(token);
    }

}

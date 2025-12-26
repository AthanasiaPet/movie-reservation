package gr.aueb.cf.moviereservation.api;

import gr.aueb.cf.moviereservation.authentication.AuthenticationService;
import gr.aueb.cf.moviereservation.dto.AuthenticationRequestDTO;
import gr.aueb.cf.moviereservation.dto.AuthenticationResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthRestController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponseDTO> login(@RequestBody AuthenticationRequestDTO authenticationRequestDTO) {

        AuthenticationResponseDTO authenticationResponseDTO = authenticationService.login(authenticationRequestDTO);
        return new ResponseEntity<>(authenticationResponseDTO, HttpStatus.OK);

    }
}

package gr.aueb.cf.moviereservation.api;

import gr.aueb.cf.moviereservation.dto.UserRegisterRequestDTO;
import gr.aueb.cf.moviereservation.model.User;
import gr.aueb.cf.moviereservation.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserRestController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User register(@RequestBody UserRegisterRequestDTO userRegisterRequestDTO) {
        return userService.register(userRegisterRequestDTO);
    }

}

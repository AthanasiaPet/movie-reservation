package gr.aueb.cf.moviereservation.api;

import gr.aueb.cf.moviereservation.dto.UserReadDTO;
import gr.aueb.cf.moviereservation.dto.UserRegisterDTO;
import gr.aueb.cf.moviereservation.mapper.UserMapper;
import gr.aueb.cf.moviereservation.model.User;
import gr.aueb.cf.moviereservation.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserRestController {

    private final UserService userService;
    private final UserMapper userMapper;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserReadDTO register(@RequestBody UserRegisterDTO userRegisterDTO) {
        User user = userService.register(userRegisterDTO);
        return userMapper.toReadDTO(user);

    }

}

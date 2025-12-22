package gr.aueb.cf.moviereservation.service;

import gr.aueb.cf.moviereservation.core.enums.Role;
import gr.aueb.cf.moviereservation.dto.UserRegisterRequestDTO;
import gr.aueb.cf.moviereservation.model.User;
import gr.aueb.cf.moviereservation.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User register(UserRegisterRequestDTO dto) {

        User user = User.builder()
                .username(dto.getUsername())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .role(Role.USER)
                .isActive(true)
                .build();

        return userRepository.save(user);
    }



}

package gr.aueb.cf.moviereservation.service;

import gr.aueb.cf.moviereservation.core.enums.Role;
import gr.aueb.cf.moviereservation.dto.UserRegisterDTO;
import gr.aueb.cf.moviereservation.model.User;
import gr.aueb.cf.moviereservation.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User register(UserRegisterDTO dto) {

        User user = User.builder()
                .username(dto.username())
                .email(dto.email())
                .password(passwordEncoder.encode(dto.password()))
                .role(Role.USER)
                .isActive(true)
                .build();

        return userRepository.save(user);
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }


}

package gr.aueb.cf.moviereservation.service;

import gr.aueb.cf.moviereservation.core.enums.Role;
import gr.aueb.cf.moviereservation.core.exceptions.ResourceAlreadyExistsException;
import gr.aueb.cf.moviereservation.dto.UserRegisterDTO;
import gr.aueb.cf.moviereservation.model.User;
import gr.aueb.cf.moviereservation.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User register(UserRegisterDTO dto) {

        if (userRepository.findByUsername(dto.username()).isPresent()) {
            throw new ResourceAlreadyExistsException("User", "username", dto.username());
        }

        if (userRepository.findByEmail(dto.email()).isPresent()) {
            throw new ResourceAlreadyExistsException("User", "email", dto.email());
        }

        User user = User.builder()
                .username(dto.username())
                .email(dto.email())
                .password(passwordEncoder.encode(dto.password()))
                .role(Role.USER)
                .isActive(true)
                .build();

        return userRepository.save(user);
    }

}

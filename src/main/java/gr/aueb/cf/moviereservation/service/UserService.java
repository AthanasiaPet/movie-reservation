package gr.aueb.cf.moviereservation.service;

import gr.aueb.cf.moviereservation.core.enums.Role;
import gr.aueb.cf.moviereservation.core.exceptions.ResourceAlreadyExistsException;
import gr.aueb.cf.moviereservation.dto.UserRegisterDTO;
import gr.aueb.cf.moviereservation.model.User;
import gr.aueb.cf.moviereservation.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User register(UserRegisterDTO dto) {

        log.info("Attempting to register user with username={} and email={}", dto.username(), dto.email());

        if (userRepository.findByUsername(dto.username()).isPresent()) {
            log.warn("Username already exists: {}", dto.username());
            throw new ResourceAlreadyExistsException("User", "username", dto.username());
        }

        if (userRepository.findByEmail(dto.email()).isPresent()) {
            log.warn("Email already exists: {}", dto.email());
            throw new ResourceAlreadyExistsException("User", "email", dto.email());
        }

        User user = User.builder()
                .username(dto.username())
                .email(dto.email())
                .password(passwordEncoder.encode(dto.password()))
                .role(Role.USER)
                .isActive(true)
                .build();

        User savedUser = userRepository.save(user);

        log.info("User with id={} and username={} registered successfully",
                savedUser.getId(), savedUser.getUsername());

        return savedUser;
    }

}

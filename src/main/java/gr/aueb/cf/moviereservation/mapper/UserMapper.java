package gr.aueb.cf.moviereservation.mapper;

import gr.aueb.cf.moviereservation.core.enums.Role;
import gr.aueb.cf.moviereservation.dto.UserReadDTO;
import gr.aueb.cf.moviereservation.dto.UserRegisterDTO;
import gr.aueb.cf.moviereservation.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final PasswordEncoder passwordEncoder;

    // Entity User to DTO
    public UserReadDTO toReadDTO(User user) {
        return new UserReadDTO(
                user.getId(),
                user.getUuid(),
                user.getUsername(),
                user.getEmail(),
                user.getRole(),
                user.getIsActive()
        );
    }

    // DTO to Entity User
    public User toEntity(UserRegisterDTO dto) {
        return User.builder()
                .username(dto.username())
                .password(passwordEncoder.encode(dto.password()))
                .email(dto.email())
                .role(Role.USER)
                .isActive(true)
                .build();
    }


}

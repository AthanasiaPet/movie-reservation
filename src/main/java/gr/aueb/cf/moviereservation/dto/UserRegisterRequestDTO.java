package gr.aueb.cf.moviereservation.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserRegisterRequestDTO {

    @NotNull
    private String username;

    @NotNull
    private String password;

    @NotNull
    private String email;
}

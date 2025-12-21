package gr.aueb.cf.moviereservation.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AuthenticationRequestDTO {

    @NotNull
    private String username;

    @NotNull
    private String password;

}

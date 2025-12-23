package gr.aueb.cf.moviereservation.dto;


public record UserRegisterDTO (
        String username,
        String password,
        String email
) {}



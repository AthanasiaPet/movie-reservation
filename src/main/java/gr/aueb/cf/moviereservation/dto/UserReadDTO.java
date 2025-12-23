package gr.aueb.cf.moviereservation.dto;

import gr.aueb.cf.moviereservation.core.enums.Role;

public record UserReadDTO(
        Long id,
        String uuid,
        String username,
        String email,
        Role role,
        Boolean isActive
) {}

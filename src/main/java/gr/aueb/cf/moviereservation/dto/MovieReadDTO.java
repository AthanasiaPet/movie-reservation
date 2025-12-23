package gr.aueb.cf.moviereservation.dto;


import gr.aueb.cf.moviereservation.core.enums.Genre;

public record MovieReadDTO (
    Long id,
    String uuid,
    String title,
    String description,
    Integer duration,
    Genre genre,
    Boolean isActive
) {}


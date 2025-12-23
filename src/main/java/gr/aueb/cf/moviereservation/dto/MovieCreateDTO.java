package gr.aueb.cf.moviereservation.dto;

import gr.aueb.cf.moviereservation.core.enums.Genre;

public record MovieCreateDTO (
        String title,
        String description,
        Integer duration,
        Genre genre
) {}

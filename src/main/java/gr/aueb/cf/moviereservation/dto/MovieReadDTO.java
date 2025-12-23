package gr.aueb.cf.moviereservation.dto;


import gr.aueb.cf.moviereservation.core.enums.Genre;

public record MovieReadDTO (
    String uuid,
    String title,
    String description,
    String duration,
    Genre genre
) {}


package gr.aueb.cf.moviereservation.mapper;

import gr.aueb.cf.moviereservation.dto.CinemaHallCreateDTO;
import gr.aueb.cf.moviereservation.dto.CinemaHallReadDTO;
import gr.aueb.cf.moviereservation.model.CinemaHall;

public class CinemaHallMapper {

    // Entity CinemaHall to DTO
    public static CinemaHallReadDTO toReadDTO(CinemaHall cinemaHall) {
        return new CinemaHallReadDTO(cinemaHall.getId(),
                cinemaHall.getUuid(),
                cinemaHall.getHallName(),
                cinemaHall.getCapacity(),
                cinemaHall.getIsActive());
    }

    // DTO to Entity CinemaHall
    public static CinemaHall toEntity(CinemaHallCreateDTO dto) {
        return CinemaHall.builder()
                .hallName(dto.hallName())
                .capacity(dto.capacity())
                .isActive(true)
                .build();
    }
}

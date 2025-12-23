package gr.aueb.cf.moviereservation.mapper;

import gr.aueb.cf.moviereservation.dto.ReservationReadDTO;
import gr.aueb.cf.moviereservation.model.Reservation;

public class ReservationMapper {

    // Entity Reservation to DTO
    public static ReservationReadDTO toReadDTO(Reservation reservation) {
        return new ReservationReadDTO(
                reservation.getUuid(), reservation.getScreening().getMovie().getTitle(), reservation.getScreening().getCinemaHall().getHallName(),
                reservation.getSeatNumber(), reservation.getStatus());
    }
}

package gr.aueb.cf.moviereservation.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReservationRequestDTO {

    @NotNull
    private Long screeningId;

    @NotNull
    private String seatNumber;
}

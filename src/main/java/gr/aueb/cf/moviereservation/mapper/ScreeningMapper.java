package gr.aueb.cf.moviereservation.mapper;

import gr.aueb.cf.moviereservation.dto.ScreeningReadDTO;
import gr.aueb.cf.moviereservation.model.Screening;

public class ScreeningMapper {

    //Entity Screening to DTO
    public static ScreeningReadDTO toReadDTO(Screening screening) {
        return new ScreeningReadDTO(screening.getId(),
                screening.getUuid(),
                screening.getMovie().getTitle(),
                screening.getCinemaHall().getHallName(),
                screening.getScreeningDateTime(),
                screening.getPrice(),
                screening.getIsActive());
    }
}

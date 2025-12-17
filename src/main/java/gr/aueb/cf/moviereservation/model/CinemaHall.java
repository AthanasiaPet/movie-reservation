package gr.aueb.cf.moviereservation.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "cinema_halls")
public class CinemaHall {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String uuid;

    @Column(nullable = false)
    private String hallName;

    @Column(nullable = false)
    private Integer capacity;

    @ColumnDefault("true")
    private Boolean isActive;

    @PrePersist
    public void initializeUUID() {
        if (uuid == null) uuid = UUID.randomUUID().toString();

    }

    @Getter(AccessLevel.PRIVATE)
    @OneToMany(mappedBy = "cinemaHall")
    private Set<Screening> screenings = new HashSet<>();
}

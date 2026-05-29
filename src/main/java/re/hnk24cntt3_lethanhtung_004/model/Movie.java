package re.hnk24cntt3_lethanhtung_004.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, length = 100)
    private String description;
//    duration_minutes;
    @Column(nullable = false)
    private  int durationMinutes;
    //genre(enum ATION, COMEDY, DRAMA, HORROR, SCI_FI)
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private String genre;
//    is_deleted;
    @Column(nullable = false)
    private boolean isDeleted = false;
}

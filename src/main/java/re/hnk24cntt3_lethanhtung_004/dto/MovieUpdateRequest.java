package re.hnk24cntt3_lethanhtung_004.dto;

import lombok.*;
import re.hnk24cntt3_lethanhtung_004.model.Genre;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovieUpdateRequest {
    private String title;
    private String director;
    private Integer durationMinutes;
    private Genre genre;
}
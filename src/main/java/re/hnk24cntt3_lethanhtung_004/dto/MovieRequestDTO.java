package re.hnk24cntt3_lethanhtung_004.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;
import re.hnk24cntt3_lethanhtung_004.model.Genre;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovieRequestDTO {

    @NotBlank(message = "Title không được để trống")
    private String title;

    @NotBlank(message = "Director không được để trống")
    private String director;

    @Positive(message = "Thời lượng phải lớn hơn 0")
    private Integer durationMinutes;

    private Genre genre;
}
package re.hnk24cntt3_lethanhtung_004.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import re.hnk24cntt3_lethanhtung_004.model.Movie;
import re.hnk24cntt3_lethanhtung_004.service.MovieService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/movies")
@RequiredArgsConstructor
public class MovieController {
    private final MovieController movieController;

    @GetMapping
    public List<Movie> findAll() {
        return movieController.findAll();
    }

    @GetMapping
    public List<Movie> findAllByGenre(String genre) {
        return movieController.findAllByGenre(genre);
    }
}


package re.hnk24cntt3_lethanhtung_004.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import re.hnk24cntt3_lethanhtung_004.dto.MovieRequestDTO;
import re.hnk24cntt3_lethanhtung_004.dto.MovieResponseDTO;
import re.hnk24cntt3_lethanhtung_004.dto.MovieUpdateRequest;
import re.hnk24cntt3_lethanhtung_004.service.MovieService;

@RestController
@RequestMapping("/api/v1/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @PostMapping
    public ResponseEntity<MovieResponseDTO> create(@Valid @RequestBody MovieRequestDTO request) {
        return new ResponseEntity<>(movieService.createMovie(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<MovieResponseDTO>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "${page.size:3}") int size) {
        return ResponseEntity.ok(movieService.getAllMovies(page, size));
    }

    @GetMapping("/search")
    public ResponseEntity<Page<MovieResponseDTO>> search(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "${page.size:3}") int size) {
        return ResponseEntity.ok(movieService.searchMovies(keyword, page, size));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieResponseDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody MovieRequestDTO request) {
        return ResponseEntity.ok(movieService.updateMovie(id, request));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<MovieResponseDTO> partialUpdate(
            @PathVariable Long id,
            @RequestBody MovieUpdateRequest request) {
        return ResponseEntity.ok(movieService.partialUpdate(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id,
            @RequestParam(defaultValue = "false") boolean hard) {
        movieService.deleteMovie(id, hard);
        return ResponseEntity.noContent().build();
    }
}
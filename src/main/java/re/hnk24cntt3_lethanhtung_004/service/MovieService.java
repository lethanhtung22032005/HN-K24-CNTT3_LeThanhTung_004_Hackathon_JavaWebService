package re.hnk24cntt3_lethanhtung_004.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import re.hnk24cntt3_lethanhtung_004.dto.MovieRequestDTO;
import re.hnk24cntt3_lethanhtung_004.dto.MovieResponseDTO;
import re.hnk24cntt3_lethanhtung_004.dto.MovieUpdateRequest;
import re.hnk24cntt3_lethanhtung_004.model.Movie;
import re.hnk24cntt3_lethanhtung_004.repository.MovieRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    @Transactional
    public MovieResponseDTO createMovie(MovieRequestDTO request) {
        Movie movie = Movie.builder()
                .title(request.getTitle())
                .director(request.getDirector())
                .durationMinutes(request.getDurationMinutes())
                .genre(request.getGenre())
                .isDeleted(false)
                .build();

        Movie saved = movieRepository.save(movie);
        return convertToResponse(saved);
    }

    public Page<MovieResponseDTO> getAllMovies(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        Page<Movie> movies = movieRepository.findAllActive(pageable);
        return movies.map(this::convertToResponse);
    }

    @Transactional
    public MovieResponseDTO updateMovie(Long id, MovieRequestDTO request) {
        Movie movie = findMovieById(id);
        movie.setTitle(request.getTitle());
        movie.setDirector(request.getDirector());
        movie.setDurationMinutes(request.getDurationMinutes());
        movie.setGenre(request.getGenre());

        return convertToResponse(movieRepository.save(movie));
    }

    @Transactional
    public MovieResponseDTO partialUpdate(Long id, MovieUpdateRequest request) {
        Movie movie = findMovieById(id);

        if (request.getTitle() != null) movie.setTitle(request.getTitle());
        if (request.getDirector() != null) movie.setDirector(request.getDirector());
        if (request.getDurationMinutes() != null) movie.setDurationMinutes(request.getDurationMinutes());
        if (request.getGenre() != null) movie.setGenre(request.getGenre());

        return convertToResponse(movieRepository.save(movie));
    }

    @Transactional
    public void deleteMovie(Long id, boolean hardDelete) {
        Movie movie = findMovieById(id);
        if (hardDelete) {
            movieRepository.deleteById(id);
        } else {
            movie.setIsDeleted(true);
            movieRepository.save(movie);
        }
    }

    public Page<MovieResponseDTO> searchMovies(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        Page<Movie> movies = movieRepository.searchMovies(keyword, pageable);
        return movies.map(this::convertToResponse);
    }

    private Movie findMovieById(Long id) {
        return movieRepository.findById(id)
                .filter(m -> !m.getIsDeleted())
                .orElseThrow(() -> new EntityNotFoundException("Movie not found with id: " + id));
    }

    private MovieResponseDTO convertToResponse(Movie movie) {
        return MovieResponseDTO.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .director(movie.getDirector())
                .durationMinutes(movie.getDurationMinutes())
                .genre(movie.getGenre())
                .isDeleted(movie.getIsDeleted())
                .build();
    }
}

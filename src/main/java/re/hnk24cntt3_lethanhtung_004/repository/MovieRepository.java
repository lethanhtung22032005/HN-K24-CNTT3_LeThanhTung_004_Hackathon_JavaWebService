package re.hnk24cntt3_lethanhtung_004.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import re.hnk24cntt3_lethanhtung_004.model.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
    @Query("SELECT m FROM Movie m WHERE m.title = :title")
    Optional<Movie> findByTitle(String title);

    @Query("SELECT m FROM Movie m WHERE m.genre = :genre")
    List<Movie> findByGenre(String genre);

}

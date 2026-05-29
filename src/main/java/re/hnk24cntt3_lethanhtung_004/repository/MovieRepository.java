package re.hnk24cntt3_lethanhtung_004.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import re.hnk24cntt3_lethanhtung_004.model.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query("SELECT m FROM Movie m WHERE m.isDeleted = false")
    Page<Movie> findAllActive(Pageable pageable);

    @Query("SELECT m FROM Movie m WHERE m.isDeleted = false " +
            "AND (LOWER(m.title) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(m.director) LIKE LOWER(CONCAT('%', :keyword, '%')))")
    Page<Movie> searchMovies(String keyword, Pageable pageable);

    boolean existsByIdAndIsDeletedFalse(Long id);
}

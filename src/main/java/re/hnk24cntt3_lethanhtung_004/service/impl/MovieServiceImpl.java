package re.hnk24cntt3_lethanhtung_004.service.impl;

import re.hnk24cntt3_lethanhtung_004.repository.MovieRepository;
import re.hnk24cntt3_lethanhtung_004.service.MovieService;

public class MovieServiceImpl extends MovieService {
    public final MovieRepository movieRepository;

    private MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }
    public static MovieServiceImpl create(MovieRepository movieRepository) {
        return new MovieServiceImpl(movieRepository);
    }

}

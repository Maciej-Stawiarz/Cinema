package ms.cinema.movies.utilities;

import ms.cinema.movies.models.dtos.MovieDto;
import ms.cinema.movies.models.entities.Movie;

public final class MovieMapper {
	
	private MovieMapper() {}
	
	public static Movie toEntity(MovieDto movieDto) {
		if (movieDto == null) {
			return new Movie();
		}
		
		return Movie.builder()
				.title(movieDto.getTitle())
				.description(movieDto.getDescription())
				.releaseDate(movieDto.getReleaseDate())
				.duration(movieDto.getDuration())
				.movieCast(movieDto.getCast())
				.director(movieDto.getDirector())
				.placeAndTimeOfProduction(movieDto.getPlaceAndTimeOfProduction())
				.genres(movieDto.getGenres())
				.originalLanguage(movieDto.getOriginalLanguage())
				.build();
	}
	
	public static MovieDto toDTO(Movie movie) {
		if (movie == null) {
			return new MovieDto();
		}
		
		return MovieDto.builder()
				.title(movie.getTitle())
				.description(movie.getDescription())
				.releaseDate(movie.getReleaseDate())
				.duration(movie.getDuration())
				.cast(movie.getMovieCast())
				.director(movie.getDirector())
				.placeAndTimeOfProduction(movie.getPlaceAndTimeOfProduction())
				.genres(movie.getGenres())
				.originalLanguage(movie.getOriginalLanguage())
				.build();
	}
}
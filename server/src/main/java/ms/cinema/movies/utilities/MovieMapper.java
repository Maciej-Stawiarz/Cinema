package ms.cinema.movies.utilities;

import ms.cinema.movies.models.dtos.MovieDto;
import ms.cinema.movies.models.entities.Movie;

public final class MovieMapper {
	
	private MovieMapper() {}
	
	public static Movie toEntity(MovieDto dto) {
		if (dto == null) {
			return new Movie();
		}
		
		return Movie.builder()
				.title(dto.getTitle())
				.description(dto.getDescription())
				.releaseDate(dto.getReleaseDate())
				.duration(dto.getDuration())
				.movieCast(dto.getCast())
				.director(dto.getDirector())
				.placeAndTimeOfProduction(dto.getPlaceAndTimeOfProduction())
				.genres(dto.getGenres())
				.originalLanguage(dto.getOriginalLanguage())
				.build();
	}
	
	public static MovieDto toDTO(Movie entity) {
		if (entity == null) {
			return new MovieDto();
		}
		
		return MovieDto.builder()
				.title(entity.getTitle())
				.description(entity.getDescription())
				.releaseDate(entity.getReleaseDate())
				.duration(entity.getDuration())
				.cast(entity.getMovieCast())
				.director(entity.getDirector())
				.placeAndTimeOfProduction(entity.getPlaceAndTimeOfProduction())
				.genres(entity.getGenres())
				.originalLanguage(entity.getOriginalLanguage())
				.build();
	}
}
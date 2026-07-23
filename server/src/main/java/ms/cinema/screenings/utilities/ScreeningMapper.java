package ms.cinema.screenings.utilities;

import ms.cinema.movies.models.entities.Movie;
import ms.cinema.rooms.models.entities.Room;
import ms.cinema.screenings.models.dtos.ScreeningDto;
import ms.cinema.screenings.models.entities.Screening;

public final class ScreeningMapper {
	
	public static Screening toEntity(ScreeningDto screeningDto, Movie movie, Room room) {
		if (screeningDto == null) {
			return new Screening();
		}
		
		return Screening.builder()
				.screeningDate(screeningDto.getScreeningDate())
				.screeningType(screeningDto.getScreeningType())
				.soundLanguage(screeningDto.getSoundLanguage())
				.subtitleLanguage(screeningDto.getSubtitleLanguage())
				.movie(movie)
				.room(room)
				.build();
	}
	
	public static ScreeningDto toDTO(Screening screening) {
		if (screening == null) {
			return new ScreeningDto();
		}
		
		return ScreeningDto.builder()
				.screeningDate(screening.getScreeningDate())
				.screeningType(screening.getScreeningType())
				.soundLanguage(screening.getSoundLanguage())
				.subtitleLanguage(screening.getSubtitleLanguage())
				.movieId(screening.getMovie().getId())
				.roomId(screening.getRoom().getId())
				.build();
	}
}

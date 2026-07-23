package ms.cinema.screenings;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import ms.cinema.exceptions.models.exceptions.NotFoundException;
import ms.cinema.movies.MovieRepository;
import ms.cinema.movies.models.entities.Movie;
import ms.cinema.rooms.RoomRepository;
import ms.cinema.rooms.models.entities.Room;
import ms.cinema.screenings.models.dtos.ScreeningDto;
import ms.cinema.screenings.models.entities.Screening;
import ms.cinema.screenings.utilities.ScreeningMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScreeningService {
	
	private final ScreeningRepository screeningRepository;
	private final MovieRepository movieRepository;
	private final RoomRepository roomRepository;
	
	public List<ScreeningDto> getScreenings(LocalDateTime screeningDate,
											Long movieId,
											String movieTitle) {
		List<Screening> screenings;
		
		if (screeningDate != null && movieId != null) {
			screenings = screeningRepository
					.findScreeningsByScreeningDateAndMovieId(screeningDate, movieId);
		} else if (screeningDate != null && movieTitle != null) {
			screenings = screeningRepository
					.findScreeningsByScreeningDateAndMovieTitle(screeningDate, movieTitle);
		} else if (screeningDate != null) {
			screenings = screeningRepository
					.findScreeningsByScreeningDate(screeningDate);
		} else if (movieId != null) {
			screenings = screeningRepository
					.findScreeningsBeMovieId(movieId);
		} else if (movieTitle != null) {
			screenings = screeningRepository
					.findScreeningsByMovieTitle(movieTitle);
		} else {
			screenings = screeningRepository
					.findAll();
		}
		
		return screenings.stream()
				.map(ScreeningMapper::toDTO)
				.toList();
	}

	public Screening getScreening(Long id) {
		 return screeningRepository
				 .findById(id)
				 .orElseThrow(() -> new NotFoundException("There is no screening for given id"));
	}
	
	@Transactional
	public ScreeningDto addScreeningToMovie(ScreeningDto screeningDto) {
		Movie movie = movieRepository
				.findById(screeningDto.getMovieId())
				.orElseThrow(() -> new NotFoundException("There is no movie with given id"));
		Room room = roomRepository
				.findById(screeningDto.getRoomId())
				.orElseThrow(() -> new NotFoundException("There is no room with given id"));
		
		Screening newScreening = ScreeningMapper.toEntity(screeningDto, movie, room);
		Screening savedScreening = screeningRepository.save(newScreening);
		
		return ScreeningMapper.toDTO(savedScreening);
	}
	
	@Transactional
	public void deleteScreening(Long id) {
		if (!screeningRepository.existsById(id)) {
			throw new NotFoundException("There is no screening with given id");
		}
		
		screeningRepository.deleteById(id);
	}
}
package ms.cinema.movies;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import ms.cinema.exceptions.models.exceptions.NotFoundException;
import ms.cinema.movies.models.dtos.MovieDto;
import ms.cinema.movies.models.entities.Movie;
import ms.cinema.movies.utilities.MovieMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MovieService {
	
	private final MovieRepository repository;
	
	public List<MovieDto> getAll() {
		return repository.findAll().stream()
				.map(MovieMapper::toDTO)
				.toList();
	}
	
	public Movie get(Long id) {
		return repository
				.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format("Could not find a movie with id: %d", id)));
	}
	
	public Movie get(String title) {
		if (title.isBlank()) {
			throw new IllegalArgumentException("Title cannot be empty when getting a movie");
		}
		
		return repository
				.findByTitle(title)
				.orElseThrow(() -> new NotFoundException(String.format("Could not find a movie with title: %s", title)));
	}
	
	@Transactional
	public MovieDto save(MovieDto movieDto) {
		if (repository.existsByTitle(movieDto.getTitle())) {
			throw new IllegalArgumentException(String.format("There already exists a movie with title %s", movieDto.getTitle()));
		}
		
		Movie movie = MovieMapper.toEntity(movieDto);
		Movie savedMovie = repository.save(movie);
		return MovieMapper.toDTO(savedMovie);
	}
	
	@Transactional
	public MovieDto update(Long id, MovieDto movieDto) {
		Movie foundMovie = repository
				.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format("There is no movie with id: %d", id)));
		
		if (!Objects.equals(foundMovie.getTitle(), movieDto.getTitle())) {
			if (repository.existsByTitle(movieDto.getTitle())) {
				throw new IllegalArgumentException(String.format("There already exists a movie with title: %s", movieDto.getTitle()));
			}
			
			foundMovie.setTitle(movieDto.getTitle());
		}
		
		foundMovie.setDescription(movieDto.getDescription());
		foundMovie.setReleaseDate(movieDto.getReleaseDate());
		foundMovie.setDuration(movieDto.getDuration());
		foundMovie.setMovieCast(movieDto.getCast());
		foundMovie.setDirector(movieDto.getDirector());
		foundMovie.setPlaceAndTimeOfProduction(movieDto.getPlaceAndTimeOfProduction());
		foundMovie.setGenres(movieDto.getGenres());
		foundMovie.setOriginalLanguage(movieDto.getOriginalLanguage());
		
		Movie savedMovie = repository.save(foundMovie);
		return MovieMapper.toDTO(savedMovie);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
}
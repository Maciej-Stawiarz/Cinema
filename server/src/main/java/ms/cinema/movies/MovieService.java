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
	
	public Movie get(String title) {
		if (title.isBlank()) {
			throw new IllegalArgumentException("Title cannot be empty when getting a movie");
		}
		
		return repository
				.findByTitle(title)
				.orElseThrow(() -> new NotFoundException("There is no movie with given title"));
	}
	
	public Movie get(Long id) {
		return repository
				.findById(id)
				.orElseThrow(() -> new NotFoundException("There is no movie with given id"));
	}
	
	@Transactional
	public MovieDto save(MovieDto movieDto) {
		if (repository.existsByTitle(movieDto.getTitle())) {
			throw new IllegalArgumentException("There already exists a movie with given title");
		}
		
		Movie movie = MovieMapper.toEntity(movieDto);
		Movie savedMovie = repository.save(movie);
		return MovieMapper.toDTO(savedMovie);
	}
	
	@Transactional
	public MovieDto update(Long id, MovieDto movieDto) {
		Movie foundMovie = repository
				.findById(id)
				.orElseThrow(() -> new NotFoundException("There is no movie with given id"));
		
		if (!Objects.equals(foundMovie.getTitle(), movieDto.getTitle())) {
			if (repository.existsByTitle(movieDto.getTitle())) {
				throw new IllegalArgumentException("There already exists a movie with given title");
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
		
		Movie movie = MovieMapper.toEntity(movieDto);
		Movie savedMovie = repository.save(movie);
		return MovieMapper.toDTO(savedMovie);
	}
	
	@Transactional
	public void delete(Long id) {
		if (!repository.existsById(id)) {
			throw new NotFoundException("There is no movie with given id");
		}
		
		repository.deleteById(id);
	}
}
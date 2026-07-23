package ms.cinema.screenings;

import ms.cinema.screenings.models.entities.Screening;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ScreeningRepository extends JpaRepository<Screening, Long> {
	
	@Query("Select s From Screening s Where s.screeningDate = :screeningDate")
	List<Screening> findScreeningsByScreeningDate(LocalDateTime screeningDate);
	
	@Query("Select s From Screening s JOIN s.movie m Where m.title = :movieTitle")
	List<Screening> findScreeningsByMovieTitle(String movieTitle);
	
	@Query("Select s From Screening s Join s.movie m Where m.id = :movieId")
	List<Screening> findScreeningsBeMovieId(Long movieId);
	
	@Query("Select s From Screening s Join s.movie m Where s.screeningDate = :screeningDate And m.title = :movieTitle")
	List<Screening> findScreeningsByScreeningDateAndMovieTitle(LocalDateTime screeningDate,
															   String movieTitle);
	
	@Query("Select s From Screening s Join s.movie m Where s.screeningDate = :screeningDate And m.id = :movieId")
	List<Screening> findScreeningsByScreeningDateAndMovieId(LocalDateTime screeningDate,
															Long movieId);
}

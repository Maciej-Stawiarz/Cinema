package ms.cinema.rooms;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import ms.cinema.exceptions.models.exceptions.NotFoundException;
import ms.cinema.rooms.models.dtos.RoomDto;
import ms.cinema.rooms.models.entities.Room;
import ms.cinema.rooms.utilities.RoomMapper;
import ms.cinema.seats.models.entities.Seat;
import ms.cinema.seats.utilities.SeatMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class RoomService {
	
	private final RoomRepository repository;
	
	public List<RoomDto> getAll() {
		return repository.findAll().stream()
				.map(RoomMapper::toDTO)
				.toList();
	}
	
	public Room get(Long id) {
		return repository
				.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format("Could not find a room with id: %d", id)));
	}
	
	public Room get(String name) {
		if (name.isBlank()) {
			throw new IllegalArgumentException("Name cannot be empty when getting a room");
		}
		
		return repository
				.findByName(name)
				.orElseThrow(() -> new NotFoundException(String.format("Could not find a room with name: %s", name)));
	}
	
	@Transactional
	public RoomDto save(RoomDto roomDto) {
		if (repository.existsByName(roomDto.getName())) {
			throw new IllegalArgumentException("There already exists a room with given name");
		}
		
		Room room = RoomMapper.toEntity(roomDto);
		Room savedRoom = repository.save(room);
		return RoomMapper.toDTO(savedRoom);
	}
	
	@Transactional
	public RoomDto update(Long id, RoomDto roomDto) {
		Room foundRoom = repository
				.findById(id)
				.orElseThrow(() -> new NotFoundException("There is no room with given id"));
		
		if (!Objects.equals(foundRoom.getName(), roomDto.getName())) {
			if (repository.existsByName(roomDto.getName())) {
				throw new IllegalArgumentException("There already exists a room with given name");
			}
			
			foundRoom.setName(roomDto.getName());
		}
		
		List<Seat> newSeats = roomDto.getSeats().stream()
						.map(SeatMapper::toEntity)
						.toList();
		foundRoom.setSeats(newSeats);

		Room savedRoom = repository.save(foundRoom);
		return RoomMapper.toDTO(savedRoom);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
}
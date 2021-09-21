package cinema.controllers;

import cinema.Cinema;
import cinema.Seat;
import cinema.Token;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CinemaController {
	private final Cinema cinema = new Cinema(9, 9);

	@GetMapping("/seats")
	public Cinema seats() {
		return cinema;
	}

	@PostMapping("/purchase")
	public ResponseEntity<?> purchase(@RequestBody Seat seat) {
		return cinema.purchaseTicket(seat);
	}

	@PostMapping("/return")
	public ResponseEntity<?> returnTicket(@RequestBody Token token) {
		return cinema.returnTicket(token);
	}

	@PostMapping("/stats")
	public ResponseEntity<?> stats(@RequestParam(required = false) String password) {
		return cinema.getStats(password);
	}
}

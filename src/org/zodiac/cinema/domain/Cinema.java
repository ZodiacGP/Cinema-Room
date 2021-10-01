package cinema;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Cinema {
	private final int totalRows;
	private final int totalColumns;
	private final List<Seat> availableSeats = new ArrayList<>();
	private final List<Ticket> reservedTickets = new ArrayList<>();
	private final String password;

	public Cinema(int totalRows, int totalColumns) {
		this.totalRows = totalRows;
		this.totalColumns = totalColumns;
		for (int i = 1; i <= totalRows; i++) {
			int price = i <= 4 ? 10 : 8;
			for (int j = 1; j <= totalColumns; j++) {
				availableSeats.add(new Seat(i, j, price));
			}
		}
		password = "super_secret";
	}

	public ResponseEntity<?> purchaseTicket(Seat seat) {
		int row = seat.getRow();
		int column = seat.getColumn();
		if (inputIsInvalid(row, column)) {
			return ResponseEntity.badRequest()
					.body(Map.of("error", "The number of a row or a column is out of bounds!"));
		}
		for (Seat s : getAvailableSeats()) {
			if (s.getRow() == row && s.getColumn() == column) {
				Ticket ticket = new Ticket(s);
				ticket.setStatus(TicketStatus.PURCHASED);
				availableSeats.remove(ticket.getSeat());
				reservedTickets.add(ticket);
				return ResponseEntity.ok(ticket);
			}
		}
		return ResponseEntity.badRequest()
				.body(Map.of("error", "The ticket has been already purchased!"));

	}

	public ResponseEntity<?> returnTicket(Token token) {
		for (Ticket t : getReservedTickets()) {
			if (t.getToken().equals(token.getToken())) {
				t.setStatus(TicketStatus.AVAILABLE);
				availableSeats.add(t.getSeat());
				reservedTickets.remove(t);
				return ResponseEntity.ok(new ReturnedTicket(t.getSeat()));
			}
		}
		return ResponseEntity.badRequest().body(Map.of("error", "Wrong token!"));
	}

	public ResponseEntity<?> getStats(String password) {
		if (password != null && password.equals(getPassword())) {
			return ResponseEntity.ok(new ManagerInfo(this));
		}
		return ResponseEntity.status(401)
				.body(Map.of("error", "The password is wrong!"));

	}

	public int getTotalRows() {
		return totalRows;
	}

	public int getTotalColumns() {
		return totalColumns;
	}

	public List<Seat> getAvailableSeats() {
		return availableSeats;
	}

	@JsonIgnore
	public String getPassword() {
		return password;
	}

	@JsonIgnore
	public List<Ticket> getReservedTickets() {
		return reservedTickets;
	}

	public boolean inputIsInvalid(int row, int column) {
		return row > getTotalRows() || row < 1 ||
				column < 1 || column > getTotalColumns();
	}

	@JsonIgnore
	public int getIncome() {
		return reservedTickets.stream()
				.map(Ticket::getSeat)
				.mapToInt(Seat::getPrice)
				.sum();
	}

}

package cinema;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class Ticket {
	private final String token;
	private final Seat seat;
	private TicketStatus status = TicketStatus.AVAILABLE;


	public Ticket(Seat seat) {
		token = UUID.randomUUID().toString();
		this.seat = seat;
	}

	public String getToken() {
		return token;
	}

	@JsonProperty("ticket")
	public Seat getSeat() {
		return seat;
	}

	public void setStatus(TicketStatus status) {
		this.status = status;
	}
}
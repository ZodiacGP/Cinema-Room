package cinema;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReturnedTicket {
	private final Seat seat;

	public ReturnedTicket(Seat seat) {
		this.seat = seat;
	}

	@JsonProperty("returned_ticket")
	public Seat getSeat() {
		return seat;
	}
}

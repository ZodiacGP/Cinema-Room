package cinema;

public class ManagerInfo {
	private final int currentIncome;
	private final int numberOfAvailableSeats;
	private final int numberOfPurchasedTickets;

	public ManagerInfo(Cinema cinema) {
		currentIncome = cinema.getIncome();
		numberOfAvailableSeats = cinema.getAvailableSeats().size();
		numberOfPurchasedTickets = cinema.getReservedTickets().size();
	}

	public int getCurrentIncome() {
		return currentIncome;
	}

	public int getNumberOfAvailableSeats() {
		return numberOfAvailableSeats;
	}

	public int getNumberOfPurchasedTickets() {
		return numberOfPurchasedTickets;
	}
}

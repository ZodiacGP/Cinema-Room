package cinema;


import java.util.UUID;

public class Token {
	private final String token;

	public Token() {
		token = UUID.randomUUID().toString();
	}

	public Token(String token) {
		this.token = token;
	}

	public String getToken() {
		return token;
	}
}

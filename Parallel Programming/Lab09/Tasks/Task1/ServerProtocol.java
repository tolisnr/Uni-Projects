
public class ServerProtocol {

	public String processRequest(String theInput) {
		System.out.println("Received message from client: " + theInput);
		String theOutput = returnMessage(theInput);
		System.out.println("Send message to client: " + theOutput);
		return theOutput;
	}

	private String returnMessage(String message) {
		StringBuilder result = new StringBuilder();
		String[] parts = message.split(",");
		int type = Integer.parseInt(parts[0]);
		int offset = 0;
		String msg = parts[1].trim();

		if(type == 4 || type == 5) {
			offset = Integer.parseInt(parts[2].trim());
		}

		switch (type) {
			case 1:
				result.append(msg);
				break;
			case 2:
				result.append(msg.toLowerCase());
				break;
			case 3:
				result.append(msg.toUpperCase());
				break;
			case 4:
				result.append(encode(msg, offset));
				break;
			case 5:
				result.append(encode(msg, (-1)*offset));
				break;
			default:
				System.out.println("Invalid option");
				System.exit(1);
		}
		return result.toString();
	}

	// Caesar cipher encoding
	// Also implements the decoding with negative offset
	private String encode(String message, int offset) {
		StringBuilder result = new StringBuilder();
		for (char c : message.toCharArray()) {
			if(c != ' ') {
				int originalAlphabetPosition = c - 'a';
				int newAlphabetPosition = (originalAlphabetPosition + offset) % 26;
				char newChar = (char) ('a' + newAlphabetPosition);
				result.append(newChar);
			} else {
				result.append(c);
			}
		}
		return result.toString();
	}
}
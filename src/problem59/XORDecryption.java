package problem59;

import java.util.List;

import utilities.ReadFile;

/**
 * Solution to Problem 59:
 * 
 * 
 * Each character on a computer is assigned a unique code and the preferred standard is ASCII (American
 * Standard Code for Information Interchange). For example, uppercase A = 65, asterisk (*) = 42, and lowercase k = 107.
 * 
 * A modern encryption method is to take a text file, convert the bytes to ASCII, then XOR each
 * byte with a given value, taken from a secret key. The advantage with the XOR function is that
 * using the same encryption key on the cipher text, restores the plain text;
 * for example, 65 XOR 42 = 107, then 107 XOR 42 = 65.
 * 
 * For unbreakable encryption, the key is the same length as the plain text message, and the key is
 *  made up of random bytes. The user would keep the encrypted message and the encryption key in different
 *  locations, and without both "halves", it is impossible to decrypt the message.
 * 
 * Unfortunately, this method is impractical for most users, so the modified method is to use a password
 * as a key. If the password is shorter than the message, which is likely, the key is repeated cyclically
 * throughout the message. The balance for this method is using a sufficiently long password key for security,
 * but short enough to be memorable.
 * 
 * Your task has been made easy, as the encryption key consists of three lower case characters.
 * Using cipher1.txt (right click and 'Save Link/Target As...'), a file containing the encrypted ASCII codes,
 * and the knowledge that the plain text must contain common English words, decrypt the message and find the
 * sum of the ASCII values in the original text.
 */
public class XORDecryption {

	public static void main(final String[] args) {
		final long startTime = System.currentTimeMillis();

		System.out.println("The answer is: " + calculateSolution());

		final long endTime = System.currentTimeMillis();
		System.out.println("The solution took: " + (endTime - startTime) + " milliseconds");
	}

	private static long calculateSolution() {
		final String allKeyCharacters = "abcdefghijklmnopqrstuvwxyz";
		String possibleKeyCharacters = "";
		final List<String> encryptedCodes = ReadFile.readAndSplit("src/problem59/cipher1.txt");

		//This loop removes any characters that when decrypting the entire string give us <=10 spaces.
		//In theory, the correct characters will encode around around 50 spaces each, so any that don't, we can ignore.
		for (int i = 0; i < allKeyCharacters.length(); i++) {
			String decryptedMessage = "";
			for (final String encryptedCode : encryptedCodes) {
				final int encryptedInteger = Integer.valueOf(encryptedCode);
				final int decryptedInteger = encryptedInteger ^ allKeyCharacters.charAt(i);
				decryptedMessage = decryptedMessage + (char) decryptedInteger;
			}

			final int spacesCount = decryptedMessage.length() - decryptedMessage.replace(" ", "").length();
			if( spacesCount > 10 ) {
				possibleKeyCharacters = possibleKeyCharacters + (""+allKeyCharacters.charAt(i));
			}
		}

		//Now try all the possible 3 character permutations of the remaining letters until we hit one that
		//contains the word "the" surrounded by spaces. This is, I assume, going to appear somewhere in
		//the encrypted text. (Given it contains common English words.)
		String overallDecryptedMessage = "";
		for( int i = 0; i < possibleKeyCharacters.length(); i++ ) {
			for (int j = i; j < possibleKeyCharacters.length(); j++) {
				for (int k = 0; k < possibleKeyCharacters.length(); k++) {

					final String key = "" + possibleKeyCharacters.charAt(i) + "" + possibleKeyCharacters.charAt(j) + "" + possibleKeyCharacters.charAt(k);

					String currentDecryptedMessage = "";

					for (int m = 0; m < encryptedCodes.size(); m++ ) {
						final int encryptedInteger = Integer.valueOf(encryptedCodes.get(m));

						final int decryptedInteger = encryptedInteger ^ key.charAt((m%3));

						currentDecryptedMessage = currentDecryptedMessage + (char) decryptedInteger;
					}

					if( currentDecryptedMessage.contains(" the ") ) {
						overallDecryptedMessage = currentDecryptedMessage;
						break;
					}
				}
				if( overallDecryptedMessage.length() > 0 ) {
					break;
				}
			}
			if( overallDecryptedMessage.length() > 0 ) {
				break;
			}
		}

		//Now just total up the ASCII character values of the decrypted message.
		long total = 0L;
		for (int i = 0; i < overallDecryptedMessage.length(); i++) {
			total += Integer.valueOf(overallDecryptedMessage.charAt(i));
		}

		return total;
	}
}
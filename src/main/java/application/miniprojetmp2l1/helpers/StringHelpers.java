package application.miniprojetmp2l1.helpers;

import java.util.Random;

public class StringHelpers {
	
	public static String generateRandomString(int targetStringLength) {
	    int leftLimit = 97; // letter 'a'
	    int rightLimit = 122; // letter 'z'
	    Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
	      .limit(targetStringLength)
	      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
	      .toString();
	}
	
}

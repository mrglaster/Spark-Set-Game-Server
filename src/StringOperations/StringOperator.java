package StringOperations;

import java.util.Random;

/**Class providing some operations with strings*/
public class StringOperator {

    /**Generates random string of preset length*/
    public static String generateRandomLine(int targetStringLength) {
        int leftLimit = 97;
        int rightLimit = 122;
        Random random = new Random();
        return random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}

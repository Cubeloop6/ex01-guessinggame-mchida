import java.math.BigInteger;

public class Guesser {
    /**
     * This method must return the number Chooser c has chosen. c can guess() any
     * number and tell you whether the number is "correct", "higher", or "lower".
     *
     * @param c The "chooser" that has chosen a number you must guess.
     * @return The number that the "chooser" has chosen
     */
    public static BigInteger findNumber(Chooser c) {
        // Tip: If you're not sure how to work with BigInteger numbers, we encourage
        // you to look up its Javadoc online.
        BigInteger guess = BigInteger.ONE;
        BigInteger guessLowerBound = BigInteger.ONE;
        String response = c.guess(guess);

        while(response.equals("higher")) {
            guessLowerBound = guess;
            guess = guess.multiply(BigInteger.TWO);
            response = c.guess(guess);
        }

        if(c.guess(guess).equals("correct")) {
            return guess;
        }

        guess = BinarySearch(guessLowerBound, guess, c);

        return guess;
    }

    public static BigInteger BinarySearch(BigInteger lowerBound, BigInteger upperBound, Chooser c) {

        BigInteger mid = (lowerBound.add(upperBound)).divide(BigInteger.TWO);

        if (upperBound.compareTo(lowerBound) < 0) {
            return BigInteger.valueOf(-1);
        }

        String response = c.guess(mid);

        if (response.equals("higher")) {
            lowerBound = mid.add(BigInteger.ONE);
            return BinarySearch(lowerBound, upperBound, c);
        } else if (response.equals("lower")) {
            upperBound = mid.subtract(BigInteger.ONE);
            return BinarySearch(lowerBound, upperBound, c);
        }

        return  mid;

    }
}

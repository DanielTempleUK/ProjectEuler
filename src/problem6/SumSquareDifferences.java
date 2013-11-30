package problem6;

public class SumSquareDifferences {

    public static void main(final String[] args) {

	long sum = 0;
	long squareSum = 0;

	for( int i = 1; i < 101; i++ ) {
	    sum = sum + i;
	    squareSum = squareSum + i * i;
	}

	System.out.println(sum);
	System.out.println(squareSum);


	final long difference = sum * sum - squareSum;

	System.out.println(difference);
    }
}
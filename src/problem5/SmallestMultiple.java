package problem5;

public class SmallestMultiple {

	public static void main(final String... args) {
		for( int i = 20; i >= 0; i++ ) {
			boolean dividesByAll1To20 = true;
			for( int j = 1; j < 21; j++ ) {
				if( i%j != 0 ) {
					dividesByAll1To20 = false;
					break;
				}
			}
			if (dividesByAll1To20) {
				System.out.println(i);
				break;
			}
		}
	}
}
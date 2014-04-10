package problem75;

public class PythagoreanTriple {

	private long a;
	private long b;
	private long c;

	public PythagoreanTriple(final long a, final long b, final long c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}

	public long getA() {
		return this.a;
	}

	public long getB() {
		return this.b;
	}

	public long getC() {
		return this.c;
	}

	@Override
	public boolean equals(final Object obj) {
		if( obj == this ) {
			return true;
		}

		if( !(obj instanceof PythagoreanTriple) ) {
			return false;
		}

		final PythagoreanTriple typedObj = (PythagoreanTriple) obj;

		if( this.a == typedObj.a && this.b == typedObj.b && this.c == typedObj.c ) {
			return true;
		}

		return super.equals(obj);
	}

	public long getTotalLength() {
		return ( this.a + this.b + this.c );
	}

	public boolean totalLengthLessThanOrEqualTo( final long i ) {
		return ( this.a + this.b + this.c ) <= i;
	}

	@Override
	public String toString() {
		return "" + this.a + " " + this.b + " " + this.c;
	}
}
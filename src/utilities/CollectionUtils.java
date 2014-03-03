package utilities;

import java.util.HashSet;
import java.util.Set;

public class CollectionUtils {

	public static final <T> Set<T> setOf(final T... elements) {
		final Set<T> set = new HashSet<T>();

		for (final T t : elements) {
			set.add(t);
		}

		return set;
	}
}

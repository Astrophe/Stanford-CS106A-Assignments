/*
 * File: NameSurferEntry.java
 * --------------------------
 * This class represents a single entry in the database.  Each
 * NameSurferEntry contains a name and a list giving the popularity
 * of that name for each decade stretching back to 1900.
 */

import acm.util.*;
import java.util.*;

public class NameSurferEntry implements NameSurferConstants {

/* Constructor: NameSurferEntry(line) */
/**
 * Creates a new NameSurferEntry from a data line as it appears
 * in the data file.  Each line begins with the name, which is
 * followed by integers giving the rank of that name for each
 * decade.
 */
	public NameSurferEntry(String line) {
		entry = line;
		name = entry.substring(0, entry.indexOf(" "));
		int lastIndex = entry.indexOf(" ") + 1;
		for (int i = 0; i < NDECADES-1; i++) {
			try {
				String ranking = entry.substring(lastIndex, entry.indexOf(" ", lastIndex));
				rankings[i] = Integer.parseInt(ranking);
				lastIndex = entry.indexOf(" ", lastIndex) + 1;
			}
			catch (NullPointerException ex) {
				break;
			}
		}
		 String ranking = entry.substring(lastIndex);
		 rankings[NDECADES-1] = Integer.parseInt(ranking);
	}

/* Method: getName() */
/**
 * Returns the name associated with this entry.
 */
	public String getName() {
		return name;
	}

/* Method: getRank(decade) */
/**
 * Returns the rank associated with an entry for a particular
 * decade.  The decade value is an integer indicating how many
 * decades have passed since the first year in the database,
 * which is given by the constant START_DECADE.  If a name does
 * not appear in a decade, the rank value is 0.
 */
	public int getRank(int decade) {
		return rankings[decade];
	}

/* Method: toString() */
/**
 * Returns a string that makes it easy to see the value of a
 * NameSurferEntry.
 */
	public String toString() {
		int lastIndex = entry.indexOf(" ") + 1;
		return name + " [" + entry.substring(lastIndex) + "]";
	}
	private String entry;
	private String name;
	private int[] rankings = new int[NDECADES];
}


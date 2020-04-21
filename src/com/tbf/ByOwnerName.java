package com.tbf;

import java.util.Comparator;

public class ByOwnerName implements Comparator<Portfolio>{

	/**
	 * Comparator that takes two portfolios as input and sorts by owner name
	 * 
	 * @param one
	 * @param two
	 * @return
	 */
	public static final int sortByOwnerName(Portfolio one, Portfolio two) {
		if (one.getOwner().getLastName().compareTo(two.getOwner().getLastName()) > 0) {
			return 1;
		} else if (one.getOwner().getLastName().compareTo(two.getOwner().getLastName()) < 0) {
			return -1;
		} else {
			if (one.getOwner().getFirstName().compareTo(two.getOwner().getFirstName()) > 0) {
				return 1;
			} else if (one.getOwner().getFirstName().compareTo(two.getOwner().getFirstName()) < 0) {
				return -1;
			} else {
				return 0;
			}
		}
	}

	/**
	 * Comparator that takes two portfolios as input and sorts by total value
	 * 
	 * @param one
	 * @param two
	 * @return
	 */

	public static final int sortByValue(Portfolio one, Portfolio two) {
		if (one.getTotalValue() > two.getTotalValue()) {
			return 1;
		} else if (one.getTotalValue() < two.getTotalValue()) {
			return -1;
		} else {
			return 0;
		}
	}

	/**
	 * Comparator that takes two portfolios as input and sorts by manager type
	 * first, and then by manager name
	 * 
	 * @param one
	 * @param two
	 * @return
	 */
	public static final int sortByManager(Portfolio one, Portfolio two) {
		if (one.getManager().getBrokerState() == "E" && two.getManager().getBrokerState() == "E") {
			return Comparator.sortByManagerName(one, two);
		} else if (one.getManager().getBrokerState() == "E" || two.getManager().getBrokerState() == "E") {
			if (one.getManager().getBrokerState() == "E") {
				return 1;
			} else {
				return -1;
			}
		} else if (one.getManager().getBrokerState() == "J" && two.getManager().getBrokerState() == "J") {
			return Comparator.sortByManagerName(one, two);
		} else if (one.getManager().getBrokerState() == "J" || two.getManager().getBrokerState() == "J") {
			if (one.getManager().getBrokerState() == "J") {
				return 1;
			} else {
				return -1;
			}
		} else {
			return Comparator.sortByManagerName(one, two);
		}
	}

	/**
	 * Private method to abstract sortByManager comparing by name
	 * 
	 * @param one
	 * @param two
	 * @return
	 */
	private static int sortByManagerName(Portfolio one, Portfolio two) {
		if (one.getManager().getLastName().compareTo(two.getManager().getLastName()) > 0) {
			return 1;
		} else if (one.getManager().getLastName().compareTo(two.getManager().getLastName()) < 0) {
			return -1;
		} else {
			if (one.getManager().getFirstName().compareTo(two.getManager().getFirstName()) > 0) {
				return 1;
			} else if (one.getManager().getFirstName().compareTo(two.getManager().getFirstName()) < 0) {
				return -1;
			} else {
				return 0;
			}
		}
	}

	@Override
	public int compare(Portfolio one, Portfolio two) {
		if (one.getOwner().getLastName().compareTo(two.getOwner().getLastName()) > 0) {
			return 1;
		} else if (one.getOwner().getLastName().compareTo(two.getOwner().getLastName()) < 0) {
			return -1;
		} else {
			if (one.getOwner().getFirstName().compareTo(two.getOwner().getFirstName()) > 0) {
				return 1;
			} else if (one.getOwner().getFirstName().compareTo(two.getOwner().getFirstName()) < 0) {
				return -1;
			} else {
				return 0;
			}
		}
	}
}

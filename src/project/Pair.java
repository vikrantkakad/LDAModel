/* Copyright (C) 2017 Vikrant Kakad

   This program is free software: you can redistribute it and/or modify
   it under the terms of the GNU General Public License as published by
   the Free Software Foundation, either version 3 of the License, or
   (at your option) any later version.

   This program is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   GNU General Public License for more details.

   You should have received a copy of the GNU General Public License
   along with this program.  If not, see https://www.gnu.org/licenses/gpl.html
*/
package project;

public class Pair implements Comparable<Pair> {
	public Object first;
	public Comparable second;
	public static boolean naturalOrder = false;

	public Pair(Object k, Comparable v) {
		first = k;
		second = v;
	}

	public Pair(Object k, Comparable v, boolean naturalOrder) {
		first = k;
		second = v;
		Pair.naturalOrder = naturalOrder;
	}

	public int compareTo(Pair p) {
		if (naturalOrder)
			return this.second.compareTo(p.second);
		else
			return -this.second.compareTo(p.second);
	}
}

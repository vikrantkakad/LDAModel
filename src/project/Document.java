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

import java.util.Vector;

public class Document {

	// ----------------------------------------------------
	// Instance Variables
	// ----------------------------------------------------
	public int[] words;
	public String rawStr;
	public int length;

	// ----------------------------------------------------
	// Constructors
	// ----------------------------------------------------
	public Document() {
		words = null;
		rawStr = "";
		length = 0;
	}

	public Document(int length) {
		this.length = length;
		rawStr = "";
		words = new int[length];
	}

	public Document(int length, int[] words) {
		this.length = length;
		rawStr = "";

		this.words = new int[length];
		for (int i = 0; i < length; ++i) {
			this.words[i] = words[i];
		}
	}

	public Document(int length, int[] words, String rawStr) {
		this.length = length;
		this.rawStr = rawStr;

		this.words = new int[length];
		for (int i = 0; i < length; ++i) {
			this.words[i] = words[i];
		}
	}

	public Document(Vector<Integer> doc) {
		this.length = doc.size();
		rawStr = "";
		this.words = new int[length];
		for (int i = 0; i < length; i++) {
			this.words[i] = doc.get(i);
		}
	}

	public Document(Vector<Integer> doc, String rawStr) {
		this.length = doc.size();
		this.rawStr = rawStr;
		this.words = new int[length];
		for (int i = 0; i < length; ++i) {
			this.words[i] = doc.get(i);
		}
	}
}

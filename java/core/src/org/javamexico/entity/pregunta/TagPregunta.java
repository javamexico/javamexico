/*
This file is part of JavaMexico.

JavaMexico is free software: you can redistribute it and/or modify it under the terms of the
GNU General Public License as published by the Free Software Foundation, either version 3
of the License, or (at your option) any later version.

JavaMexico is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with JavaMexico.
If not, see <http://www.gnu.org/licenses/>.
*/
package org.javamexico.entity.pregunta;

import java.util.Comparator;

import javax.persistence.Entity;
import javax.persistence.Id;

/** Representa un tag que el autor de una pregunta le pone, para indicar los temas
 * relacionados.
 * 
 * @author Enrique Zamudio
 */
@Entity(name="tag_pregunta")
public class TagPregunta implements Comparable<TagPregunta> {

	private int tid;
	private int count;
	private String tag;

	@Id
	public int getTid() {
		return tid;
	}
	public void setTid(int value) {
		tid = value;
	}

	public int getCount() {
		return count;
	}
	public void setCount(int value) {
		count = value;
	}

	public String getTag() {
		return tag;
	}
	public void setTag(String value) {
		tag = value;
	}

	/** Compara dos tags por su nombre. */
	public int compareTo(TagPregunta o) {
		if (o == null) {
			return 1;
		}
		if (tag == null) {
			return o.getTag() == null ? 0 : -1;
		}
		return tag.compareTo(o.getTag());
	}

	/** Este comparador evalua la cuenta de cada tag en vez del nombre.
	 * 
	 * @author Enrique Zamudio
	 */
	public static class CountComparator implements Comparator<TagPregunta> {

		public int compare(TagPregunta o1, TagPregunta o2) {
			if (o1 == null) {
				return o2 == null ? 0 : 1;
			} else if (o2 == null) {
				return -1;
			} else {
				if (o1.getCount() == o2.getCount()) {
					return 0;
				}
				return o1.getCount() > o2.getCount() ? -1 : 1;
			}
		}
		
	}

}

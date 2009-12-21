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
package org.javamexico.entity.foro;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/** Representa un tag que puede ponerse a varios
 * foros, para indicar los temas que trata.
 * 
 * @author Enrique Zamudio
 */
@Entity(name="tag_foro")
public class TagForo {

	private int tid;
	private int count;
	private Set<Foro> foros;
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

	@ManyToMany
	public Set<Foro> getForos() {
		return foros;
	}
	public void setForos(Set<Foro> value) {
		foros = value;
	}

	public String getTag() {
		return tag;
	}
	public void setTag(String value) {
		tag = value;
	}

}

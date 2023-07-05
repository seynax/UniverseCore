/**
 * Copyright 2021-2023 Onsiea Studio All rights reserved.<br>
 * <br>
 *
 * This file is part of Onsiea Engine project.
 * (https://github.com/OnsieaStudio/OnsieaEngine)<br>
 * <br>
 *
 * Onsiea Engine is [licensed]
 * (https://github.com/OnsieaStudio/OnsieaEngine/blob/main/LICENSE) under the terms of
 * the "GNU General Public Lesser License v2.1" (LGPL-2.1).
 * https://github.com/OnsieaStudio/OnsieaEngine/wiki/License#license-and-copyright<br>
 * <br>
 *
 * Onsiea Engine is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 2.1 of the License, or
 * (at your option) any later version.<br>
 * <br>
 *
 * Onsiea Engine is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.<br>
 * <br>
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Onsiea Engine. If not, see <https://www.gnu.org/licenses/>.<br>
 * <br>
 *
 * Neither the name "Onsiea Studio", "Onsiea Engine", or any derivative name or the
 * names of its authors / contributors may be used to endorse or promote
 * products derived from this software and even less to name another project or
 * other work without clear and precise permissions written in advance.<br>
 * <br>
 *
 * @Author : Seynax (https://github.com/seynax)<br>
 * @Organization : Onsiea Studio (https://github.com/OnsieaStudio)
 */
package fr.seynax.universecore.utils.json;

import java.util.Objects;

/**
 *
 */
public class JSONValue
{
	private final String value;

	public JSONValue(final String valueIn)
	{
		this.value = valueIn;
	}

	public String value()
	{
		return this.value;
	}

	@Override
	public String toString()
	{
		return this.value;
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(this.value);
	}

	@Override
	public boolean equals(final Object obj)
	{
		if (this == obj)
		{
			return true;
		}
		if (obj == null || this.getClass() != obj.getClass())
		{
			return false;
		}
		final var other = (JSONValue) obj;
		return Objects.equals(this.value, other.value);
	}

	public final static class Builder
	{
		private String	value;
		private boolean	isRemoved;

		public Builder()
		{
		}

		public Builder(final String valueIn)
		{
			this.value = valueIn;
		}

		public String value()
		{
			return this.value;
		}

		public Builder value(final String valueIn) throws Exception
		{
			if (this.isRemoved)
			{
				throw new Exception("[ERROR] Cannot set value of removed json value  !");
			}

			this.value = valueIn;

			return this;
		}

		public Builder remove()
		{
			this.value		= null;
			this.isRemoved	= true;

			return this;
		}

		public boolean isRemoved()
		{
			return this.isRemoved;
		}

		public JSONValue build() throws Exception
		{
			if (this.isRemoved)
			{
				throw new Exception("[ERROR] Cannot build removed json value !");
			}

			return new JSONValue(this.value);
		}
	}
}
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 *
 */
public class JSONArray implements IJSONObject
{
	private final JSONValue		key;
	private final IJSONObject[]	objects;

	public JSONArray(final JSONValue keyIn, final Collection<IJSONObject> objectsIn) throws Exception
	{
		if (keyIn == null)
		{
			throw new Exception("[ERROR] JSONObject : key cannot be null !");
		}

		this.key = keyIn;
		if (objectsIn != null)
		{
			this.objects = new IJSONObject[objectsIn.size()];
			var i = 0;
			for (final var object : objectsIn)
			{
				this.objects[i] = object;

				i++;
			}
		}
		else
		{
			this.objects = new IJSONObject[0];
		}
	}

	public int count()
	{
		return this.objects.length;
	}

	public IJSONObject get(final int indexIn)
	{
		if (indexIn < 0 || indexIn >= this.objects.length)
		{
			return null;
		}

		return this.objects[indexIn];
	}

	public IJSONObject get(final String keyIn)
	{
		for (final var jsonObject : this.objects)
		{
			if (jsonObject.key().value().contentEquals(keyIn))
			{
				return jsonObject;
			}
		}

		return null;
	}

	@Override
	public String toString()
	{
		final var	content	= new StringBuilder().append(this.key.value()).append(" :\r\n{");
		var			i		= 0;
		for (final var object : this.objects)
		{
			content.append("\t").append(object.toString());

			if (i + 1 < this.objects.length)
			{
				content.append("\r\n");
			}

			i++;
		}
		content.append("\r\n}");

		return content.toString();
	}

	@Override
	public int hashCode()
	{
		final var	prime	= 31;
		var			result	= 1;
		result = prime * result + Arrays.hashCode(this.objects);
		return prime * result + Objects.hash(this.key);
	}

	@Override
	public boolean equals(final Object obj)
	{
		if (this == obj)
		{
			return true;
		}
		if ((obj == null) || (this.getClass() != obj.getClass()))
		{
			return false;
		}
		final var other = (JSONArray) obj;
		return Objects.equals(this.key, other.key) && Arrays.equals(this.objects, other.objects);
	}

	@Override
	public boolean isArray()
	{
		return true;
	}

	@Override
	public JSONValue key()
	{
		return this.key;
	}

	public final static class Builder implements IJSONObjectBuilder
	{
		private final JSONValue.Builder			key;
		private final List<IJSONObject>			objects;
		private final List<IJSONObjectBuilder>	objectBuilders;

		public Builder(final JSONValue.Builder keyIn, final JSONObject... objectsIn)
		{
			this.key = keyIn;

			this.objects = new ArrayList<>();
			for (final var object : objectsIn)
			{
				if (object == null)
				{
					continue;
				}

				this.objects.add(object);
			}

			this.objectBuilders = new ArrayList<>();
		}

		public Builder add(final IJSONObject objectIn)
		{
			if (objectIn == null)
			{
				return this;
			}

			this.objects.add(objectIn);

			return this;
		}

		public final Builder addAll(final IJSONObject... objectsIn)
		{
			for (final var object : objectsIn)
			{
				if (object == null)
				{
					continue;
				}

				this.objects.add(object);
			}

			return this;
		}

		public Builder add(final IJSONObjectBuilder objectIn)
		{
			if (objectIn == null)
			{
				return this;
			}

			this.objectBuilders.add(objectIn);

			return this;
		}

		public final Builder addAll(final IJSONObjectBuilder... objectsIn)
		{
			for (final var object : objectsIn)
			{
				if (object == null)
				{
					continue;
				}

				this.objectBuilders.add(object);
			}

			return this;
		}

		@Override
		public IJSONObject build() throws Exception
		{
			for (final var objectBuilder : this.objectBuilders)
			{
				this.objects.add(objectBuilder.build());
			}
			this.objectBuilders.clear();

			return new JSONArray(this.key.build(), this.objects);
		}

		public JSONValue.Builder key()
		{
			return this.key;
		}
	}
}
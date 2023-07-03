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
package fr.seynax.universecore.utils.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import fr.seynax.universecore.utils.IIFunction;
import fr.seynax.universecore.utils.IIOFunction;

/**
 *
 */
public class FileUtils
{
	/**
	 * @author Seynax
	 * @param filePathIn
	 * @return string content of file from filePathIn
	 * @throws Exception
	 */
	public final static String content(final String filePathIn) throws Exception
	{
		return FileUtils.content(new File(filePathIn));
	}

	/**
	 * @author Seynax
	 * @param fileIn
	 * @return string content of fileIn
	 * @throws Exception
	 */
	public final static String content(final File fileIn) throws Exception
	{
		final var content = new StringBuilder();
		FileUtils.linesExecutions(fileIn, lineIn -> content.append(lineIn + "\r\n"));
		return content.toString();
	}

	/**
	 * @author Seynax
	 * @param filePathIn
	 * @return all lines in list of file from filePathIn
	 * @throws Exception
	 */
	public final static List<String> lines(final String filePathIn) throws Exception
	{
		return FileUtils.lines(new File(filePathIn));
	}

	/**
	 * @author Seynax
	 * @param fileIn
	 * @return all lines in list of fileIn
	 * @throws Exception
	 */
	public final static List<String> lines(final File fileIn) throws Exception
	{
		final List<String> lines = new ArrayList<>();
		FileUtils.linesExecutions(fileIn, lineIn -> lines.add(lineIn));
		return lines;
	}

	/**
	 * execute function for all lines of file from filePathIn
	 *
	 * @author Seynax
	 * @param filePathIn
	 * @param functionIn
	 * @throws Exception
	 */
	public final static void linesExecutions(final String filePathIn, final IIFunction<String> functionIn) throws Exception
	{
		FileUtils.linesExecutions(new File(filePathIn), functionIn);
	}

	/**
	 * execute function for all lines of fileIn
	 *
	 * @author Seynax
	 * @param fileIn
	 * @param functionIn
	 * @throws Exception
	 */
	public final static void linesExecutions(final File fileIn, final IIFunction<String> functionIn) throws Exception
	{
		if (!fileIn.exists())
		{
			throw new Exception("[ERROR] File : \"" + fileIn.getAbsolutePath() + "\" not exists !");
		}
		if (fileIn.isDirectory())
		{
			throw new Exception("[ERROR] File : \"" + fileIn.getAbsolutePath() + "\" cannot be readed because is directory !");
		}

		final var bufferedReader = new BufferedReader(new FileReader(fileIn));

		String line = null;
		while ((line = bufferedReader.readLine()) != null)
		{
			functionIn.execute(line);
		}

		bufferedReader.close();
	}

	/**
	 * execute function for all lines of file from filePathIn, add string output in list
	 *
	 * @author Seynax
	 * @param filePathIn
	 * @param functionIn
	 * @return all string output in list
	 * @throws Exception
	 */
	public final static List<String> linesExecutionsAndLines(final String filePathIn, final IIOFunction<String, String> functionIn) throws Exception
	{
		final List<String> lines = new ArrayList<>();
		FileUtils.linesExecutions(new File(filePathIn), lineIn -> lines.add(functionIn.execute(lineIn)));
		return lines;
	}

	/**
	 * execute function for all lines of fileIn, add string output in list
	 *
	 * @author Seynax
	 * @param fileIn
	 * @param functionIn
	 * @return all string output in list
	 * @throws Exception
	 */
	public final static List<String> linesExecutionsAndLines(final File fileIn, final IIOFunction<String, String> functionIn) throws Exception
	{
		final List<String> lines = new ArrayList<>();
		FileUtils.linesExecutions(fileIn, lineIn -> lines.add(functionIn.execute(lineIn)));
		return lines;
	}

	/**
	 * execute function for all lines of file from filePathIn, add string output on global string content<br>
	 * For all line, function receive "\r\n" at end of string line parameter, you can remove this or leave it in the exit
	 *
	 * @author Seynax
	 * @param filePathIn
	 * @param functionIn
	 * @return global string content
	 * @throws Exception
	 */
	public final static String linesExecutionsAndContent(final String filePathIn, final IIOFunction<String, String> functionIn) throws Exception
	{
		final var content = new StringBuilder();
		FileUtils.linesExecutions(new File(filePathIn), lineIn -> content.append(functionIn.execute(lineIn + "\r\n")));
		return content.toString();
	}

	/**
	 * execute function for all lines of fileIn, add string output on global string content<br>
	 * For all line, function receive "\r\n" at end of string line parameter, you can remove this or leave it in the exit
	 *
	 * @author Seynax
	 * @param fileIn
	 * @param functionIn
	 * @return global string content
	 * @throws Exception
	 */
	public final static String linesExecutionsAndContent(final File fileIn, final IIOFunction<String, String> functionIn) throws Exception
	{
		final var content = new StringBuilder();
		FileUtils.linesExecutions(fileIn, lineIn -> content.append(functionIn.execute(lineIn + "\r\n")));
		return content.toString();
	}

	/**
	 * Write contentIn in file from filePathIn (no append)
	 *
	 * @author Seynax
	 * @param filePathIn
	 * @param contentIn
	 * @throws Exception
	 */
	public final static void write(final String filePathIn, final String contentIn) throws Exception
	{
		FileUtils.write(new File(filePathIn), contentIn);
	}

	/**
	 * Write contentIn in fileIn (no append)
	 *
	 * @author Seynax
	 * @param fileIn
	 * @param contentIn
	 * @throws Exception
	 */
	public final static void write(final File fileIn, final String contentIn) throws Exception
	{
		if (!fileIn.getParentFile().exists() && !fileIn.getParentFile().mkdirs())
		{
			throw new Exception("[ERROR] Cannot create parent folder \"" + fileIn.getParent() + "\" of file \"" + fileIn.getAbsolutePath() + "\" to write \"" + contentIn + "\"");
		}
		if (!fileIn.exists() && !fileIn.createNewFile())
		{
			throw new Exception("[ERROR] Cannot create file \"" + fileIn.getAbsolutePath() + "\" to write \"" + contentIn + "\"");
		}
		if (fileIn.isDirectory())
		{
			throw new Exception("[ERROR] File : \"" + fileIn.getAbsolutePath() + "\" cannot be writed because is directory !");
		}

		final var bufferedWriter = new BufferedWriter(new FileWriter(fileIn));

		bufferedWriter.write(contentIn);

		bufferedWriter.close();
	}

	/**
	 * Write contentIn in file from filePathIn (append if exists)
	 *
	 * @author Seynax
	 * @param filePathIn
	 * @param contentIn
	 * @throws Exception
	 */
	public final static void append(final String filePathIn, final String contentIn) throws Exception
	{
		FileUtils.append(new File(filePathIn), contentIn);
	}

	/**
	 * Write contentIn in fileIn (append if exists)
	 *
	 * @author Seynax
	 * @param fileIn
	 * @param contentIn
	 * @throws Exception
	 */
	public final static void append(final File fileIn, final String contentIn) throws Exception
	{
		if (!fileIn.getParentFile().exists() && !fileIn.getParentFile().mkdirs())
		{
			throw new Exception("[ERROR] Cannot create parent folder \"" + fileIn.getParent() + "\" of file \"" + fileIn.getAbsolutePath() + "\" to write \"" + contentIn + "\"");
		}
		if (!fileIn.exists() && !fileIn.createNewFile())
		{
			throw new Exception("[ERROR] Cannot create file \"" + fileIn.getAbsolutePath() + "\" to write \"" + contentIn + "\"");
		}
		if (fileIn.isDirectory())
		{
			throw new Exception("[ERROR] File : \"" + fileIn.getAbsolutePath() + "\" cannot be writed because is directory !");
		}

		final var bufferedWriter = new BufferedWriter(new FileWriter(fileIn, true));

		bufferedWriter.write(contentIn);

		bufferedWriter.close();
	}
}
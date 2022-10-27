package it.dreamteam.ctf.first.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.StandardCopyOption;


public class OSIntf {
	public static File outputFile = new File("/tmp/output");
	public static File errorFile = new File("/tmp/error");

	public static int execute(String cmd, String input) throws IOException, InterruptedException {
		Process process = Runtime.getRuntime().exec(cmd);

		if (input != null) {
			process.getOutputStream().write(input.getBytes());
		}
		process.getOutputStream().close();
		process.waitFor();

		java.nio.file.Files.copy(
			      process.getInputStream(), 
			      outputFile.toPath(), 
			      StandardCopyOption.REPLACE_EXISTING);

		java.nio.file.Files.copy(
			      process.getErrorStream(), 
			      errorFile.toPath(), 
			      StandardCopyOption.REPLACE_EXISTING);

		return process.exitValue();
	}

	public static String getOutput() throws IOException {
		return java.nio.file.Files.readString(outputFile.toPath());
	}

	public static String getError() throws IOException {
		return java.nio.file.Files.readString(errorFile.toPath());
	}
}

package it.dreamteam.ctf.first.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


public class OSIntf {
	public static File outputFile = new File("/tmp/output");
	public static File errorFile = new File("/tmp/error");

	public static int execute(String cmd, String input) throws IOException, InterruptedException {
		Process process = Runtime.getRuntime().exec(cmd);

		if (input != null) {
			process.getOutputStream().write(input.getBytes());
			process.getOutputStream().close();
		}
		process.waitFor();

		try ( FileOutputStream out = new FileOutputStream(outputFile, false) ) {
			out.write(process.getInputStream().readAllBytes());
			out.close();
		}
		try ( FileOutputStream out = new FileOutputStream(errorFile, false) ) {
			out.write(process.getErrorStream().readAllBytes());
			out.close();
		}

		return process.exitValue();
	}

	public static String getOutput() throws IOException {
		return java.nio.file.Files.readString(outputFile.toPath());
	}

	public static String getError() throws IOException {
		return java.nio.file.Files.readString(errorFile.toPath());
	}
}

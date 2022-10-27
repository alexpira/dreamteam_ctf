package it.dreamteam.ctf.first.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
	@Value("classpath:static/index.html")
	Resource index;

	@GetMapping(value = "/", produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
	public String main() throws IOException {
		return Files.readString(Paths.get(index.getURI()));
	}
}

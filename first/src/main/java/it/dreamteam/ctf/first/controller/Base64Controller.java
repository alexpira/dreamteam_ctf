package it.dreamteam.ctf.first.controller;

import java.io.IOException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.dreamteam.ctf.first.utils.OSIntf;

@RestController
public class Base64Controller {
	@GetMapping("/api/b64/decode")
	public String b64decode(@RequestParam("value") String b64) throws IOException, InterruptedException {
		int rv = OSIntf.execute("base64 -di", b64);
		if (rv == 0) {
			return OSIntf.getOutput();
		} else {
			return OSIntf.getError();
		}
	}

	@GetMapping("/api/b64/encode")
	public String b64encode(@RequestParam("value") String b64) throws IOException, InterruptedException {
		int rv = OSIntf.execute("base64 -i", b64);
		if (rv == 0) {
			return OSIntf.getOutput();
		} else {
			return OSIntf.getError();
		}
	}
}

package it.dreamteam.ctf.first.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.dreamteam.ctf.first.utils.OSIntf;

public class SecretController {
	@GetMapping("/api/secret")
	public ResponseEntity<String> secret(@RequestParam("key") String key) throws IOException, InterruptedException {
		int rv = OSIntf.execute("/bin/bash -c 'dd if=/dev/urandom count=21 bs=1 2>/dev/null | base64'", key);
		if (rv != 0) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
		String correct = OSIntf.getOutput();
		if (!correct.equals(key)) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
		String flag = java.nio.file.Files.readString(new File("/usr/local/flag").toPath());
		return ResponseEntity.ok("${FLAG:" + flag + "}");
	}

}

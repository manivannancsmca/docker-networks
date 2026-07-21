package com.docker_networks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping("/api/system")
public class DockerNetworksApplication {

	public static void main(String[] args) {
		SpringApplication.run(DockerNetworksApplication.class, args);
	}

}

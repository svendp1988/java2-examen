package be.pxl.paj.museum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class MuseumApplication {

	public static void main(String[] args) {
		SpringApplication.run(MuseumApplication.class, args);
	}

}

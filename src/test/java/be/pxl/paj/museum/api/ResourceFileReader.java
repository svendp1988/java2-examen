package be.pxl.paj.museum.api;

import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import static java.nio.charset.StandardCharsets.UTF_8;

public final class ResourceFileReader {

	private ResourceFileReader() {
		// No need to instantiate.
	}

	public static String getAsString(String location) {
		try (InputStream inputStream = new ClassPathResource(location).getInputStream()) {
			return new Scanner(inputStream, UTF_8).useDelimiter("\\Z").next();
		} catch (IOException e) {
			throw new ClasspathReadingException(e);
		}
	}

	public static class ClasspathReadingException extends RuntimeException {

		public ClasspathReadingException(Throwable cause) {
			super(cause);
		}
	}
}

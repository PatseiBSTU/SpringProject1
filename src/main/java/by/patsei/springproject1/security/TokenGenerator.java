package by.patsei.springproject1.security;

import java.util.UUID;

public class TokenGenerator {
	
	public static String generate() {
		return UUID.randomUUID().toString();
	}
	
}

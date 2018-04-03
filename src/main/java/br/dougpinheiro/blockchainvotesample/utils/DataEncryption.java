package br.dougpinheiro.blockchainvotesample.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class DataEncryption {
	
	public static String encode(String data) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(data);
	}

}

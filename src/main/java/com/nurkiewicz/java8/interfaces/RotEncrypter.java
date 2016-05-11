package com.nurkiewicz.java8.interfaces;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.charset.Charset;

public class RotEncrypter implements Encrypter {

	@Override
	public byte[] encode(byte[] bytes) {
		final byte[] result = new byte[bytes.length];
		for (int i = 0; i < bytes.length; ++i) {
			result[i] = (byte) (bytes[i] + 13);
		}
		return result;
	}

	
}

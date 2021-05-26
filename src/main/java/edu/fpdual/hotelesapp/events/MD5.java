package edu.fpdual.hotelesapp.events;

import java.security.MessageDigest;
import org.apache.commons.codec.digest.DigestUtils;

public class MD5 {
	public static void main(String[] args) {
		System.out.println(MD5.toMD5("12345"));
	}
	
	public static String toMD5(String texto) {
		return DigestUtils.md5Hex(texto).toUpperCase();
	}

}

package edu.fpdual.hotelesapp.events;

import java.security.MessageDigest;
import org.apache.commons.codec.digest.DigestUtils;

public class MD5 {

	public static void main(String[] args) {
		
		String hash = "35454B055CC325EA1AF2126E27707052";
		String password = "ILoveJava";
		String md5Hex = DigestUtils.md5Hex(password).toUpperCase();
		System.out.println(md5Hex);

	}

}

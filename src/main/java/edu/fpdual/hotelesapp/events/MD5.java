package edu.fpdual.hotelesapp.events;

import java.security.MessageDigest;
import org.apache.commons.codec.digest.DigestUtils;

public class MD5 {

	public static String toMD5(String texto) {
		return DigestUtils.md5Hex(texto).toUpperCase();
	}

}

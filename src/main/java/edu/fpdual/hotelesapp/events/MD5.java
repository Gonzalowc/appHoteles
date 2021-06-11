package edu.fpdual.hotelesapp.events;

import java.security.MessageDigest;
import org.apache.commons.codec.digest.DigestUtils;
/**
 * Clase MD5
 * @author angela.bonilla.gomez
 *
 */
public class MD5 {

	/**
	 * Metodo que se usa para encriptar
	 * @param texto el valor introducido
	 * @return el valor como una cadena hexadecimal de 32 caracteres
	 */
	public static String toMD5(String texto) {
		return DigestUtils.md5Hex(texto).toUpperCase();
	}

}

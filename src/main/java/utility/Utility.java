package utility;

import java.security.MessageDigest;

import javax.xml.bind.DatatypeConverter;

public class Utility {
	/**
	 * MD5ダイジェスト取得
	 * @param bytes 元データ
	 * @return MD5ダイジェスト文字列（１６進数）
	 */
	public static String digest(String strKey) {
		try {
			byte[] bytes = strKey.getBytes();
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			byte[] digest = messageDigest.digest(bytes);
			String hashPassword = DatatypeConverter.printHexBinary(digest);
			return hashPassword;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}

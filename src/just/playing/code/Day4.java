package just.playing.code;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Day4 {

	private static final String STARTS_WITH = "000000";
	private static String INPUT = "iwrupvqb";
	public static void main(String[] args) {
		boolean found = false;
		int i = 0;
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			byte[] bytes;
			String hash;
			while(!found) {
				bytes = (INPUT + i).getBytes("UTF-8");
				md.update(bytes, 0, bytes.length);
				byte[] hashBytes = md.digest();
				hash = bytesToString(hashBytes);
				found = hash.startsWith(STARTS_WITH);
				i++;
				System.out.println(i + ": " + hash);
			}
			System.out.println(--i);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	private static String bytesToString(byte[] bytes) {
		StringBuffer sb = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
          sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
	}
}

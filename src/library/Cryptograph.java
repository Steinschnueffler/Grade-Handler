package library;

import java.util.Random;

public class Cryptograph {
	
	private Cryptograph() {}
	
	public static byte[] code(byte[] uncoded) {
		byte[] coded = new byte[uncoded.length];
		for(int i = 0; i < coded.length; i++) {
			if(uncoded[i] == Byte.MAX_VALUE) coded[i] = Byte.MIN_VALUE;
			else coded[i] = (byte) (uncoded[i] +1);
		}
		return coded;
	}
	
	public static byte[] deCode(byte[] coded) {
		byte[] uncoded = new byte[coded.length];
		for(int i = 0; i < uncoded.length; i++) {
			if(coded[i] == Byte.MIN_VALUE) uncoded[i] = Byte.MAX_VALUE;
			else uncoded[i] = (byte) (coded[i] - 1);
		}
		return uncoded;
	}
	
	public static String generateHash(String str) {
		int hash = str.hashCode();
		Random linear = new Random(hash);
		StringBuilder sb = new StringBuilder();
		for(Character c : str.toCharArray()) {
			Integer next = c.hashCode();
			do {
				next ^= (linear.nextInt());
			}while(next < 0);
			sb.append(next);
		}
		return sb.toString();
	}

}
